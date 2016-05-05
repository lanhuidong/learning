package com.nexusy.spring.cache;

/**
 * @author lan
 * @since 2016-04-25
 */
public class Face {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
