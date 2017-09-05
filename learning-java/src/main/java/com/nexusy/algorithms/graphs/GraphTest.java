package com.nexusy.algorithms.graphs;

/**
 * @author lanhuidong
 * @since 2017-09-04
 */
public class GraphTest {

    public static void main(String[] args) throws Exception {
        Graph g = new Graph();
        DepthFirstPaths paths = new DepthFirstPaths(g, 0);
        for (int i = 0; i < g.getVertiesNum(); i++) {
            Iterable<Integer> vs = paths.pathTo(i);
            System.out.print("vertex " + i + ": ");
            for (Integer v : vs) {
                System.out.print(" " + v);
            }
            System.out.println();
        }

        BreadthFirstPaths bfp = new BreadthFirstPaths(g, 0);
        for (int i = 0; i < g.getVertiesNum(); i++) {
            Iterable<Integer> vs = bfp.pathTo(i);
            System.out.print("vertex " + i + ": ");
            for (Integer v : vs) {
                System.out.print(" " + v);
            }
            System.out.println();
        }
    }
}
