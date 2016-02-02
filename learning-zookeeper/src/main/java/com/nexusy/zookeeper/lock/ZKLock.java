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
    private T value;
    private final AtomicBoolean isDone = new AtomicBoolean(false);

    public ZKLock(ZooKeeper zookeeper, ZKCallback<T> callback) {
        this.zookeeper = zookeeper;
        this.callback = callback;
    }

    public void tryLock(String path, String prefix) throws KeeperException, InterruptedException {
        String name = zookeeper.create(path + "/" + prefix + "-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        ZNodeName lockName = new ZNodeName(name);
        do {
            List<String> childrenNames = zookeeper.getChildren(path, false);
            SortedSet<ZNodeName> sortedNames = new TreeSet<>();
            for (String childName : childrenNames) {
                sortedNames.add(new ZNodeName(path + "/" + childName));
            }

            String ownerId = sortedNames.first().getName();
            SortedSet<ZNodeName> lessThanMe = sortedNames.headSet(lockName);
            if (!lessThanMe.isEmpty()) {
                String lastChildId = lessThanMe.last().getName();
                Stat stat = zookeeper.exists(lastChildId, new LockWatcher(name));
                if (stat != null) {
                    break;
                }
            } else {
                //当前节点是最小的节点, 表示已获得锁, 开始执行互斥操作
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

        private String id;

        public LockWatcher(String id) {
            this.id = id;
        }

        @Override
        public void process(WatchedEvent event) {
            /**
             * 如果监听的节点被删除, 则表示获得了锁, 开始执行互斥操作
             */
            if (event.getType() == Event.EventType.NodeDeleted) {
                value = callback.doInLock();
                try {
                    zookeeper.delete(id, -1);
                } catch (InterruptedException | KeeperException e) {
                    e.printStackTrace();
                }
                synchronized (isDone) {
                    isDone.compareAndSet(false, true);
                    isDone.notifyAll();
                }
            }
        }
    }
}
