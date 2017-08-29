package com.nexusy.algorithms.graphs;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author lanhuidong
 * @since 2017-08-29
 */
public class Graph {

    private final int verticesNum;  //顶点数
    private int edgesNum;        //边数
    private Bag<Integer>[] adj;  //邻接表

    /**
     * 创建一个长度为verticesNum的数组，每个数组元素是一个空链表
     *
     * @param verticesNum
     */
    public Graph(int verticesNum) {
        this.verticesNum = verticesNum;
        this.edgesNum = 0;
        this.adj = new Bag[verticesNum];
        for (int i = 0; i < verticesNum; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Graph(BufferedReader reader) throws IOException {
        this(Integer.valueOf(reader.readLine()));
        this.edgesNum = Integer.valueOf(reader.readLine());
        for (int i = 0; i < edgesNum; i++) {
            String[] s = reader.readLine().split(" ");
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
        edgesNum++;
    }

    public Iterable<Integer> adj(int vertice) {
        return adj[vertice];
    }

}
