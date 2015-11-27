package com.nexusy.zookeeper.watch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * @author lan
 * @since 2015-11-27
 */
public class ServiceConsumer implements Watcher {

    private static final String ZNODE = "/app/intelvision/storage";
    private ZooKeeper zk;

    void subscribe() {
        try {
            List<String> children = zk.getChildren(ZNODE, true);
            System.out.println(children);
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
        ServiceConsumer consumer = new ServiceConsumer();
        consumer.startZK("10.12.13.111:2181");
        consumer.subscribe();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer.stopZK();
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
        if (event.getType() == Event.EventType.NodeChildrenChanged) {
            subscribe();
        }
    }
}
