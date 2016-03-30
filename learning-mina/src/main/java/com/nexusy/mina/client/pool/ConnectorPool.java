package com.nexusy.mina.client.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.mina.core.session.IoSession;

/**
 * @author lan
 * @since 2016-03-30
 */
public class ConnectorPool {

    private static final GenericObjectPoolConfig cfg = new GenericObjectPoolConfig();

    static {
        cfg.setTestOnBorrow(true);
        cfg.setMaxTotal(2);
        cfg.setMaxIdle(2);
        cfg.setMinIdle(0);
        cfg.setMinEvictableIdleTimeMillis(5000);
        cfg.setTimeBetweenEvictionRunsMillis(5000);
        cfg.setTestWhileIdle(true);
    }

    private static final GenericObjectPool<IoSession> pool = new GenericObjectPool<>(new ConnectorPoolFactory(), cfg);

    private ConnectorPool() {
    }

    public static GenericObjectPool<IoSession> getInstance() {
        return pool;
    }
}
