package com.nexusy.redis.cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lanhuidong
 * @since 2015-11-29
 */
public class RedisClusterMain {

    public static void main(String[] args) {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.0.104", 7000));
        nodes.add(new HostAndPort("192.168.0.104", 7001));
        nodes.add(new HostAndPort("192.168.0.104", 7002));

        JedisCluster cluster = new JedisCluster(nodes);

        String action = "get";
        if ("set".equals(action)) {
            for (int i = 0; i < 10; i++) {
                System.out.println(cluster.set("foo" + i, "bar" + i));
            }
        } else if ("get".equals(action)) {
            for (int i = 0; i < 10; i++) {
                System.out.println(cluster.get("foo" + i));
            }
        }

    }
}
