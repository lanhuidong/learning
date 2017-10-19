package com.nexusy.algorithms.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 有向图
 *
 * @author lanhuidong
 * @since 2017-10-18
 */
public class Digraph {

    /**
     * 顶点数
     */
    private final int v;

    /**
     * 边数
     */
    private int e;
    private Bag<Integer>[] adj;

    public Digraph(int v) {
        this.v = v;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[v] = new Bag<>();
        }
    }

    public Digraph() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        this.v = Integer.valueOf(br.readLine());
        this.adj = new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
        br = new BufferedReader(new InputStreamReader(System.in));
        int edges = Integer.valueOf(br.readLine());
        for (int i = 0; i < edges; i++) {
            br = new BufferedReader(new InputStreamReader(System.in));
            String[] s = br.readLine().split(" ");
            int v1 = Integer.valueOf(s[0]);
            int v2 = Integer.valueOf(s[1]);
            addEdge(v1, v2);
        }
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        e++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph digraph = new Digraph(v);
        for (int i = 0; i < v; i++) {
            for (int w : adj[v]) {
                digraph.addEdge(w, v);
            }
        }
        return digraph;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(v + " vertices, " + e + " edges\n");
        for (int i = 0; i < v; i++) {
            s.append(i).append(": ");
            for (int w : this.adj(i)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
