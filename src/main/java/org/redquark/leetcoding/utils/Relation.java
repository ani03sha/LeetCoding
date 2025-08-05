package org.redquark.leetcoding.utils;

public class Relation {

    private final int[][] graph;

    public Relation(int[][] graph) {
        this.graph = graph;
    }

    public boolean knows(int a, int b) {
        return this.graph[a][b] == 1;
    }
}
