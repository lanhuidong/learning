package com.nexusy.zookeeper.watch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

/**
 * @author lan
 * @since 2015-11-29
 */
public class MasterAsync implements Watcher {

    private ZooKeeper zk;
    private String hostPort;
    private String serverId = Integer.toHexString(new Random().nextInt());
    private boolean isLeader = false;

    public MasterAsync(String hostPort) {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper(hostPort, 15000, this);
    }

    void stopZK() throws InterruptedException {
        zk.close();
    }

    AsyncCallback.StringCallback masterCreateCallback = (rc, path, ctx, name) -> {
        switch (KeeperException.Code.get(rc)) {
            case CONNECTIONLOSS:
                checkMaster();
                return;
            case OK:
                isLeader = true;
                break;
            default:
                isLeader = false;
        }
        System.out.println("I'm" + (isLeader ? "" : "not") + " the leader.");
    };

    AsyncCallback.DataCallback masterCheckCallback = (rc, path, ctx, data, stat) -> {
        switch (KeeperException.Code.get(rc)) {
            case CONNECTIONLOSS:
                checkMaster();
                return;
            case NONODE:
                runForMaster();
                return;
        }
    };

    void checkMaster() {
        zk.getData("/master", false, masterCheckCallback, null);
    }

    void runForMaster() {
        zk.create("/master", serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                masterCreateCallback, null);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    public static void main(String[] args) throws Exception {
        MasterAsync master = new MasterAsync("10.12.13.111:2181");
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
