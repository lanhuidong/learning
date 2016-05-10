package com.nexusy.zookeeper.barrier;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static org.apache.zookeeper.KeeperException.NoNodeException;
import static org.apache.zookeeper.KeeperException.NodeExistsException;
import static org.apache.zookeeper.ZooDefs.Ids;

/**
 * ZKBarrier的改进版本，减少子节点改变时事件回调
 *
 * @author lan
 * @since 2016-05-10
 */
public class ZKBarrier2 implements Watcher {

    private ZooKeeper zk;
    private final Integer mutex = -1;
    private String root;
    private String prefix;
    private String name;
    private int parties;
    private String lowestName;

    public ZKBarrier2(String address, String root, int parties) {
        try {
            zk = new ZooKeeper(address, 3000, this);
            createBarrierNode(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.root = root;
        this.parties = parties;
        try {
            prefix = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void createBarrierNode(String root) {
        try {
            Stat stat = zk.exists(root, false);
            if (stat == null) {
                zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean enter() throws KeeperException, InterruptedException {
        String p = zk.create(root + "/" + prefix + "-", new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        name = p.substring(p.lastIndexOf('/') + 1);
        while (true) {
            Stat stat = zk.exists(root + "/ready", true);
            if (stat == null) {
                List<String> list = zk.getChildren(root, false);
                synchronized (mutex) {
                    if (list.size() == parties) {
                        try {
                            zk.create(root + "/ready", new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                        } catch (NodeExistsException ignored) {
                        }
                    } else {
                        mutex.wait();
                    }
                }
            } else {
                return true;
            }
        }
    }

    public boolean leave() throws KeeperException, InterruptedException {
        try {
            zk.delete(root + "/ready", 0);
        } catch (NoNodeException ignored) {
        }
        while (true) {
            List<String> list = zk.getChildren(root, false);
            getLowestNode(list);
            if (list.size() == 0) {
                //没有子节点，退出
                System.out.println(1 + "*****");
                return true;
            } else if (list.size() == 1 && list.get(0).equals(name)) {
                //只有一个子节点，并且是自己，删除并退出
                zk.delete(root + "/" + name, 0);
                System.out.println(2 + "*****");
            } else if (list.size() > 1 && lowestName.equals(name)) {
                //多个子节点，并且自己是最小节点，等待其他节点删除
                zk.getChildren(root, true);
                synchronized (mutex) {
                    mutex.wait();
                }
                System.out.println(3 + "*****");
            } else if (list.size() > 1 && !lowestName.equals(name)) {
                //多个子节点，并且自己不是最小节点，删除，并等待最小节点删除
                zk.exists(root + "/" + lowestName, true);
                zk.delete(root + "/" + name, 0);
                synchronized (mutex) {
                    mutex.wait();
                }
                System.out.println(4 + "*****");
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("--------" + event.getType());
        synchronized (mutex) {
            mutex.notify();
        }
    }

    /**
     * 获取最小的节点名称
     */
    public void getLowestNode(List<String> names) {
        int minSeq = -1;
        for (String s : names) {
            if (s.startsWith(prefix)) {
                int idx = s.lastIndexOf('-');
                int seq = Integer.valueOf(s.substring(idx + 1));
                if (minSeq == -1 || seq < minSeq) {
                    minSeq = seq;
                    lowestName = s;
                }
            }
        }
    }
}
