package com.nexusy.zookeeper.queue;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.apache.zookeeper.Watcher.Event.EventType;
import static org.apache.zookeeper.ZooDefs.Ids;

/**
 * @author lan
 * @since 2016-05-11
 */
public class ZKQueue implements Watcher {

    private String root;
    private ZooKeeper zk;
    private static final String prefix = "q-";
    private final Integer mutex = -1;

    public ZKQueue(String address, String root) {
        this.root = root;
        try {
            zk = new ZooKeeper(address, 5000, this);
            createQueueNode(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == EventType.NodeChildrenChanged) {
            synchronized (mutex) {
                mutex.notify();
            }
        }
    }

    public boolean add(byte[] data) {
        return insert(data);
    }

    public boolean offer(byte[] data) {
        return insert(data);
    }

    public byte[] remove() {
        String name = getSmallestName();
        if (name != null) {
            byte[] data = getData(name);
            deleteNode(name);
            return data;
        } else {
            throw new NoSuchElementException();
        }
    }

    public byte[] poll() {
        String name = getSmallestName();
        if (name != null) {
            byte[] data = getData(name);
            deleteNode(name);
            return data;
        } else {
            return null;
        }
    }

    public byte[] element() {
        String name = getSmallestName();
        if (name != null) {
            return getData(name);
        } else {
            throw new NoSuchElementException();
        }
    }

    public byte[] peek() {
        String name = getSmallestName();
        if (name != null) {
            return getData(name);
        } else {
            return null;
        }
    }

    public byte[] take() throws InterruptedException {
        while (true) {
            synchronized (mutex) {
                String name = getSmallestName();
                if (name != null) {
                    byte[] data = getData(name);
                    deleteNode(name);
                    return data;
                } else {
                    mutex.wait();
                }
            }
        }
    }

    private void createQueueNode(String root) {
        try {
            Stat stat = zk.exists(root, false);
            if (stat == null) {
                zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean insert(byte[] data) {
        try {
            zk.create(root + "/" + prefix, data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            return true;
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getSmallestName() {
        try {
            List<String> childrenNames = zk.getChildren(root, true);
            SortedSet<ZNodeName> sortedNames = new TreeSet<>();
            for (String childrenName : childrenNames) {
                sortedNames.add(new ZNodeName(childrenName));
            }
            if (sortedNames.isEmpty()) {
                return null;
            } else {
                return sortedNames.first().getName();
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getData(String name) {
        try {
            return zk.getData(root + "/" + name, false, null);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void deleteNode(String name) {
        try {
            zk.delete(root + "/" + name, 0);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    class ZNodeName implements Comparable<ZNodeName> {
        private String name;
        private int sequence = -1;

        public ZNodeName(String name) {
            int idx = name.lastIndexOf("-");
            this.name = name;
            this.sequence = Integer.valueOf(name.substring(idx + 1));
        }

        public String getName() {
            return name;
        }

        public int getSequence() {
            return sequence;
        }

        @Override
        public int compareTo(ZNodeName o) {
            int result;
            int s1 = this.sequence;
            int s2 = o.getSequence();
            if (s1 == -1 && s2 == -1) {
                result = this.name.compareTo(o.getName());
            } else if (s1 == -1 || s2 == -1) {
                result = -1;
            } else {
                result = s1 - s2;
            }
            return result;
        }
    }

}
