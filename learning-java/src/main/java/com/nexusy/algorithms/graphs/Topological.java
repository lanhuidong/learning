package com.nexusy.algorithms.graphs;

import java.io.IOException;

/**
 * 有向无环图的深度优先搜索顺序
 *
 * @author lanhuidong
 * @since 2017-11-07
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph g) {
        DirectedCycle cycleFinder = new DirectedCycle(g);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(g);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) throws IOException {
        Digraph g = new Digraph();
        Topological top = new Topological(g);
        if (top.isDAG()) {
            for (int v : top.order()) {
                System.out.println(v);
            }
        }
    }

}
