package com.nexusy.zookeeper.barrier;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static org.apache.zookeeper.ZooDefs.Ids;

/**
 * @author lan
 * @since 2016-05-10
 */
public class ZKBarrier implements Watcher {

    private ZooKeeper zk;
    private final Integer mutex = -1;
    private String root;
    private String name;
    private int parties;

    public ZKBarrier(String address, String root, int parties) {
        try {
            zk = new ZooKeeper(address, 3000, this);
            createBarrierNode(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.root = root;
        this.parties = parties;
        try {
            name = InetAddress.getLocalHost().getCanonicalHostName();
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
        String p = zk.create(root + "/" + name, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        name = p.substring(p.lastIndexOf('/') + 1);
        while (true) {
            synchronized (mutex) {
                List<String> list = zk.getChildren(root, true);
                if (list.size() < parties) {
                    mutex.wait();
                } else {
                    return true;
                }
            }
        }
    }

    public boolean leave() throws KeeperException, InterruptedException {
        zk.delete(root + "/" + name, 0);
        while (true) {
            synchronized (mutex) {
                List<String> list = zk.getChildren(root, true);
                if (list.size() > 0) {
                    mutex.wait();
                } else {
                    return true;
                }
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        synchronized (mutex) {
            mutex.notify();
        }
    }
}
