package com.nexusy.zookeeper.watch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

/**
 * @author lan
 * @since 2015-11-27
 */
public class Master implements Watcher {

    private ZooKeeper zk;
    private String hostPort;
    private String serverId = Integer.toHexString(new Random().nextInt());
    private boolean isLeader = false;

    public Master(String hostPort) {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper(hostPort, 15000, this);
    }

    void stopZK() throws InterruptedException {
        zk.close();
    }

    //如果已存在master，则返回true
    boolean checkMaster() throws KeeperException, InterruptedException {
        while (true) {
            Stat stat = new Stat();
            try {
                byte[] data = zk.getData("/master", false, stat);
                isLeader = new String(data).equals(serverId);
                return true;
            } catch (KeeperException.NoNodeException e) {
                return false;
            } catch (KeeperException.ConnectionLossException ignored) {
            }
        }
    }

    void runForMaster() throws KeeperException, InterruptedException {
        while (true) {
            try {
                zk.create("/master", serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (KeeperException.NoNodeException e) {
                isLeader = false;
                break;
            } catch (KeeperException.ConnectionLossException ignored) {
            }
            if (checkMaster()) {
                break;
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    public static void main(String[] args) throws Exception {
        Master master = new Master("10.12.13.111:2181");
        master.startZK();
        master.runForMaster();
        if (master.isLeader) {
            System.out.println("I'm the leader.");
            Thread.sleep(60000);
        } else {
            System.out.println("Someone else is the leader.");
        }
        master.stopZK();
    }
}
