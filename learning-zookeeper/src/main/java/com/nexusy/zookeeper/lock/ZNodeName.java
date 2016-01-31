package com.nexusy.zookeeper.lock;

/**
 * @author lanhuidong
 * @since 2016-01-31
 */
public class ZNodeName implements Comparable<ZNodeName> {

    private final String name;
    private String prefix;
    private int sequence = -1;

    public ZNodeName(String name) {
        if (name == null) {
            throw new NullPointerException("lock id can not be null.");
        }
        this.name = name;
        int idx = name.lastIndexOf('-');
        if (idx >= 0) {
            this.prefix = name.substring(0, idx);
            this.sequence = Integer.parseInt(name.substring(idx + 1));
        } else {
            this.prefix = name;
        }
    }

    @Override
    public int compareTo(ZNodeName o) {
        int result = this.prefix.compareTo(o.getPrefix());
        if (result == 0) {
            int s1 = this.sequence;
            int s2 = o.getSequence();
            if (s1 == -1 && s2 == -1) {
                result = this.name.compareTo(o.getName());
            } else if (s1 == -1 || s2 == -1) {
                result = -1;
            } else {
                result = s1 - s2;
            }
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getSequence() {
        return sequence;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZNodeName zNodeName = (ZNodeName) o;

        return name != null ? name.equals(zNodeName.name) : zNodeName.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
