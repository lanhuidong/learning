package com.nexusy.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author lanhuidong
 * @since 2016-01-31
 */
public class ZKLock<T> implements Future<T> {

    private ZooKeeper zookeeper;
    private ZKCallback<T> callback;
    private String path;
    private String name;
    private ZNodeName lockName;
    private T value;
    private final AtomicBoolean isDone = new AtomicBoolean(false);

    public ZKLock(ZooKeeper zookeeper, ZKCallback<T> callback) {
        this.zookeeper = zookeeper;
        this.callback = callback;
    }

    private void createChild(String path, String prefix) throws KeeperException, InterruptedException {
        name = zookeeper.create(path + "/" + prefix + "-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        lockName = new ZNodeName(name);
    }

    private void lock() throws KeeperException, InterruptedException {
        do {
            //2.获取所有lock节点下的子节点，不要设置watch标志
            List<String> childrenNames = zookeeper.getChildren(path, false);
            SortedSet<ZNodeName> sortedNames = new TreeSet<>();
            for (String childName : childrenNames) {
                sortedNames.add(new ZNodeName(path + "/" + childName));
            }

            String ownerId = sortedNames.first().getName();
            SortedSet<ZNodeName> lessThanMe = sortedNames.headSet(lockName);
            if (!lessThanMe.isEmpty()) {
                //4.否则客户端在比它小的最大节点上调用exists()方法，并watch该节点的状态
                String lastChildId = lessThanMe.last().getName();
                Stat stat = zookeeper.exists(lastChildId, new LockWatcher());
                if (stat != null) {
                    break;
                }
                //5.如果exists()返回false，则回到第2步；否则等待监听的节点被删除，然后再回到步骤2
            } else {
                //3.如果步骤1创建的节点是所有子节点中最小的节点，则该客户端获得锁，退出协议
                if (ownerId != null && name != null && ownerId.equals(name)) {
                    value = callback.doInLock();
                    zookeeper.delete(ownerId, -1);
                    synchronized (isDone) {
                        isDone.compareAndSet(false, true);
                        isDone.notifyAll();
                    }
                    break;
                }
            }
        } while (true);
    }

    public void tryLock(String path, String prefix) throws KeeperException, InterruptedException {
        //1.首先在lock节点下创建一个ephemeral_sequence的节点
        this.path = path;
        createChild(path, prefix);
        lock();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return isDone.get();
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        if (!isDone.get()) {
            synchronized (isDone) {
                isDone.wait();
            }
        }
        return value;
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return get();
    }

    private class LockWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            /**
             * 如果监听的节点被删除, 则表示获得了锁, 开始执行互斥操作
             */
            if (event.getType() == Event.EventType.NodeDeleted) {
                try {
                    lock();
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
