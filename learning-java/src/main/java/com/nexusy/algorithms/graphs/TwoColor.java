package com.nexusy.algorithms.graphs;

/**
 * 判断一个图是否为二分图
 *
 * @author lanhuidong
 * @since 2017-09-11
 */
public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph g) {
        marked = new boolean[g.getVertiesNum()];
        color = new boolean[g.getVertiesNum()];
        for (int s = 0; s < g.getVertiesNum(); s++) {
            if (!marked[s]) {
                dfs(g, s);
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
            } else if (color[w] == color[v]) {
                isTwoColorable = false;
                break;
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }

    public static void main(String[] args) throws Exception {
        Graph g = new Graph();
        TwoColor twoColor = new TwoColor(g);
        System.out.println("Is a bipartite graph: " + twoColor.isBipartite());
    }

}
