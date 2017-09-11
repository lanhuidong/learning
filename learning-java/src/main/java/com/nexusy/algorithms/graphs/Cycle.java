package com.nexusy.algorithms.graphs;

/**
 * 判断图中是否有环
 *
 * @author lanhuidong
 * @since 2017-09-11
 */
public class Cycle {

    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph g) {
        marked = new boolean[g.getVertiesNum()];
        for (int s = 0; s < g.getVertiesNum(); s++) {
            if (!marked[s]) {
                dfs(g, s, s);
            }
        }
    }

    private void dfs(Graph g, int v, int u) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w, v);
            } else if (w != u) {
                hasCycle = true;
                break;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) throws Exception {
        Graph g = new Graph();
        Cycle c = new Cycle(g);
        System.out.println("Is it has cycle in graph: " + c.hasCycle());
    }

}
