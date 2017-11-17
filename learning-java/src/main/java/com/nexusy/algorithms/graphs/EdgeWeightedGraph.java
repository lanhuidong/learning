package com.nexusy.algorithms.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 加权无向图
 *
 * @author lanhuidong
 * @since 2017-11-17
 */
public class EdgeWeightedGraph {

    /**
     * 顶点数
     */
    private final int v;

    /**
     * 边数
     */
    private int e;

    private Bag<Edge>[] adj;

    public EdgeWeightedGraph() throws IOException {
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
            double weight = Double.valueOf(s[3]);
            Edge edge = new Edge(v1, v2, weight);
            addEdge(edge);
        }
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        e++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<Edge>();
        for (int v = 0; v < this.v; v++) {
            for (Edge edge : adj[v]) {
                if (edge.other(v) > v) {
                    b.add(edge);
                }
            }
        }
        return b;
    }

}
