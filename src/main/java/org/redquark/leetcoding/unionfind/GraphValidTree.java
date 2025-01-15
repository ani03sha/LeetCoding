package org.redquark.leetcoding.unionfind;

import java.util.Arrays;

public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        // Base case
        if (n <= 0 || edges == null || edges.length == 0) {
            return false;
        }
        final UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            if (!unionFind.union(edge[0], edge[1])) {
                return false;
            }
        }
        return unionFind.count == 1;
    }

    static class UnionFind {
        private final int[] parents;
        private int count;

        UnionFind(int n) {
            this.parents = new int[n];
            Arrays.setAll(this.parents, i -> i);
            this.count = n;
        }

        public boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }
            this.parents[a] = rootB;
            count--;
            return true;
        }

        private int find(int a) {
            if (this.parents[a] != a) {
                this.parents[a] = this.parents[this.parents[a]];
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
