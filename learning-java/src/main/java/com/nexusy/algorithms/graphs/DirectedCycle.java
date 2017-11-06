package com.nexusy.algorithms.graphs;

/**
 * 判断有向图中是否存在环
 *
 * @author lanhuidong
 * @since 2017-11-06
 */
public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph g) {
        onStack = new boolean[g.getV()];
        edgeTo = new int[g.getV()];
        marked = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) throws Exception {
        Digraph g = new Digraph();
        DirectedCycle directedCycle = new DirectedCycle(g);
        if (directedCycle.hasCycle()) {
            for (Integer i : directedCycle.cycle()) {
                System.out.println(i);
            }
        } else {
            System.out.println("no cycle.");
        }
    }
}
