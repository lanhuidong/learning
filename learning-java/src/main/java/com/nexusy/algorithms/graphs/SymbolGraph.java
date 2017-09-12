package com.nexusy.algorithms.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author lanhuidong
 * @since 2017-09-12
 */
public class SymbolGraph {

    private Map<String, Integer> map;
    private String[] keys;
    private Graph g;

    public SymbolGraph() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] vertices;
        do {
            String edge = br.readLine();
            vertices = edge.split(" ");
            for (String vertex : vertices) {
                if(!map.containsKey(vertex)){
                    map.put(vertex, map.size());
                }
            }
        } while (vertices.length == 2);
        keys = new String[map.size()];
        for (String s : map.keySet()) {
            keys[map.get(s)] = s;
        }
        g = new Graph();
    }

    public boolean contains(String s) {
        return map.containsKey(s);
    }

    public int index(String s) {
        return map.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph getGraph() {
        return g;
    }

}
