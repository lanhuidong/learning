package com.nexusy.zookeeper.lock;

/**
 * @author lanhuidong
 * @since 2016-01-31
 */
public interface ZKCallback<T> {

    T doInLock();

}
