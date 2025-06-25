package org.redquark.leetcoding.unionfind;

import java.util.Arrays;

public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        // Base cases
        if (n == 0) {
            return false;
        }
        if (edges.length != n - 1) {
            return false;
        }
        final UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            if (!unionFind.union(edge[0], edge[1])) {
                return false; // Cycle detected
            }
        }
        return true;
    }

    static class UnionFind {
        private final int[] parents;

        UnionFind(int n) {
            this.parents = new int[n];
            Arrays.setAll(this.parents, i -> i);
        }

        public boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false; // Cycle
            }
            this.parents[rootA] = rootB;
            return true;
        }

        private int find(int a) {
            if (this.parents[a] != a) {
                this.parents[a] = find(this.parents[a]);
            }
            return this.parents[a];
        }
    }

    public static void main(String[] args) {
        final GraphValidTree graphValidTree = new GraphValidTree();

        int n = 5;
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println(graphValidTree.validTree(n, edges));

        edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println(graphValidTree.validTree(n, edges));
    }
}
