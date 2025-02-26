package org.redquark.leetcoding.unionfind;

import java.util.Arrays;

public class NumberOfProvinces {

    public int findCircleNum(int[][] isConnected) {
        // Special case
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        final int n = isConnected.length;
        final UnionFind unionFind = new UnionFind(n);
        // Process each cell in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
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

        void union(int a, int b) {
            final int rootA = find(a);
            final int rootB = find(b);
            if (rootA == rootB) {
                return;
            }
            this.parents[rootB] = rootA;
            this.count--;
        }

        int find(int a) {
            if (this.parents[a] != a) {
                this.parents[a] = find(this.parents[a]);
            }
            return this.parents[a];
        }
    }

    public static void main(String[] args) {
        final NumberOfProvinces numberOfProvinces = new NumberOfProvinces();

        int[][] isConnected = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(numberOfProvinces.findCircleNum(isConnected));

        isConnected = new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(numberOfProvinces.findCircleNum(isConnected));
    }
}
