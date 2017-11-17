package com.nexusy.algorithms.graphs;

/**
 * @author lanhuidong
 * @since 2017-11-17
 */
public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException("error vertex");
        }
    }

    @Override
    public int compareTo(Edge o) {
        if (weight < o.getWeight()) {
            return -1;
        } else if (weight > o.getWeight()) {
            return 1;
        }
        return 0;
    }

}
