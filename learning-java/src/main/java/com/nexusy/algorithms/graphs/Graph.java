package com.nexusy.algorithms.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lanhuidong
 * @since 2017-08-29
 */
public class Graph {

    private final int verticesNum;  //顶点数
    private int edgesNum;        //边数
    private Bag<Integer>[] adj;  //邻接表

    public Graph() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        this.verticesNum = Integer.valueOf(br.readLine());
        this.adj = new Bag[verticesNum];
        for (int i = 0; i < verticesNum; i++) {
            adj[i] = new Bag<>();
        }
        br = new BufferedReader(new InputStreamReader(System.in));
        this.edgesNum = Integer.valueOf(br.readLine());
        for (int i = 0; i < edgesNum; i++) {
            br = new BufferedReader(new InputStreamReader(System.in));
            String[] s = br.readLine().split(" ");
            int v1 = Integer.valueOf(s[0]);
            int v2 = Integer.valueOf(s[1]);
            addEdge(v1, v2);
        }
    }

    public int getVertiesNum() {
        return verticesNum;
    }

    public int getEdgesNum() {
        return edgesNum;
    }

    public void addEdge(int v1, int v2) {
        adj[v1].add(v2);
        adj[v2].add(v1);
    }

    public Iterable<Integer> adj(int vertice) {
        return adj[vertice];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(verticesNum + " vertices, " + edgesNum + " edges\n");
        for (int i = 0; i < verticesNum; i++) {
            s.append(i).append(": ");
            for (int w : this.adj(i)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
