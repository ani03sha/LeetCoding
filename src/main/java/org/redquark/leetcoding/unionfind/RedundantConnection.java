package org.redquark.leetcoding.unionfind;

import java.util.Arrays;

public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        // Special case
        if (edges == null || edges.length < 3) {
            throw new IllegalArgumentException("Invalid input!");
        }
        final UnionFind unionFind = new UnionFind(edges.length);
        // Process all the edges
        for (int[] edge : edges) {
            if (!unionFind.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[0];
    }

    static class UnionFind {
        private final int[] parents;

        UnionFind(int n) {
            this.parents = new int[n + 1];
            Arrays.setAll(this.parents, i -> i);
        }

        private boolean union(int a, int b) {
            final int rootA = find(a);
            final int rootB = find(b);
            if (rootA == rootB) {
                return false;
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
        final RedundantConnection redundantConnection = new RedundantConnection();

        int[][] edges = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        System.out.println(Arrays.toString(redundantConnection.findRedundantConnection(edges)));

        edges = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        System.out.println(Arrays.toString(redundantConnection.findRedundantConnection(edges)));
    }
}
