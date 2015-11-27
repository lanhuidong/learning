package com.nexusy.zookeeper.watch;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author lan
 * @since 2015-11-27
 */
public class ServiceProvider implements Watcher {

    private static final String ZNODE = "/app/intelvision/storage";
    private String serverId;
    private ZooKeeper zk;

    public ServiceProvider(String serverId) {
        this.serverId = serverId;
    }

    private void publish() {
        try {
            zk.create(ZNODE + "/" + serverId, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startZK(String hostPort) {
        try {
            zk = new ZooKeeper(hostPort, 5000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopZK() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServiceProvider provider = new ServiceProvider("server2");
        provider.startZK("10.12.13.111:2181");
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        provider.stopZK();
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            publish();
        }
    }
}
