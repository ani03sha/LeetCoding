package org.redquark.leetcoding.unionfind;

import java.util.Arrays;

public class NumberOfConnectedComponentsInAnUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        // Special case
        if (n <= 0) {
            return 0;
        }
        final UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        return unionFind.count;
    }

    static class UnionFind {
        private final int[] parents;
        private int count;

        UnionFind(int n) {
            this.parents = new int[n];
            this.count = n;
            Arrays.setAll(this.parents, i -> i);
        }

        private void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return;
            }
            this.parents[rootA] = rootB;
            this.count--;
        }

        private int find(int a) {
            if (this.parents[a] != a) {
                this.parents[a] = this.parents[this.parents[a]];
            }
            return this.parents[a];
        }
    }

    public static void main(String[] args) {
        final NumberOfConnectedComponentsInAnUndirectedGraph numberOfConnectedComponentsInAnUndirectedGraph = new NumberOfConnectedComponentsInAnUndirectedGraph();

        int n = 5;
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {3, 4}};
        System.out.println(numberOfConnectedComponentsInAnUndirectedGraph.countComponents(n, edges));

        edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println(numberOfConnectedComponentsInAnUndirectedGraph.countComponents(n, edges));
    }
}
