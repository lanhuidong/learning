package com.nexusy.algorithms.graphs;

/**
 * 有向图深度优先搜索算法
 *
 * @author lanhuidong
 * @since 2017-10-19
 */
public class DirectedDFS {

    private boolean marked[];

    public DirectedDFS(Digraph dg, int s) {
        this.marked = new boolean[dg.getV()];
        dfs(dg, s);
    }

    public DirectedDFS(Digraph dg, Iterable<Integer> sources) {
        this.marked = new boolean[dg.getV()];
        for (int s : sources) {
            if (!marked[s]) {
                dfs(dg, s);
            }
        }
    }

    private void dfs(Digraph dg, int v) {
        marked[v] = true;
        for (Integer w : dg.adj(v)) {
            if (!marked[w]) {
                dfs(dg, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) throws Exception {
        Digraph g = new Digraph();
        Bag<Integer> sources = new Bag<>();
        sources.add(4);
        DirectedDFS reachable = new DirectedDFS(g, sources);
        for (int i = 0; i < g.getV(); i++) {
            if (reachable.marked(i)) {
                System.out.println(i + " ");
            }
        }
        System.out.println();
    }

}
