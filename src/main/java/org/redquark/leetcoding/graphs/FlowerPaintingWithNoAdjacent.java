package org.redquark.leetcoding.graphs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FlowerPaintingWithNoAdjacent {

    public int[] gardenNoAdj(int n, int[][] paths) {
        // Create graph based on paths
        final Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] path : paths) {
            graph.get(path[0] - 1).add(path[1] - 1);
            graph.get(path[1] - 1).add(path[0] - 1);
        }
        // Array to keep track of answers
        final int[] answers = new int[n];
        // Process all gardens one by one
        for (int i = 0; i < n; i++) {
            // Since we have four options - 1, 2, 3, 4, we create a
            // color array of size 5 => 1 (not filled) + 4 (filled);
            final int[] colors = new int[5];
            // Process every neighbor of the current garden
            for (int neighbor : graph.get(i)) {
                // Mark every neighbor filled
                colors[answers[neighbor]] = 1;
            }
            // Try to fit either of 1, 2, 3, or 4 in answers array
            for (int j = 4; j >= 1; j--) {
                if (colors[j] == 0) {
                    answers[i] = j;
                }
            }
        }
        return answers;
    }

    public static void main(String[] args) {
        final FlowerPaintingWithNoAdjacent flowerPaintingWithNoAdjacent = new FlowerPaintingWithNoAdjacent();

        int n = 3;
        int[][] paths = new int[][]{{1, 2}, {2, 3}, {3, 1}};
        System.out.println(Arrays.toString(flowerPaintingWithNoAdjacent.gardenNoAdj(n, paths)));

        n = 4;
        paths = new int[][]{{1, 2}, {3, 4}};
        System.out.println(Arrays.toString(flowerPaintingWithNoAdjacent.gardenNoAdj(n, paths)));

        n = 4;
        paths = new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 3}, {2, 4}};
        System.out.println(Arrays.toString(flowerPaintingWithNoAdjacent.gardenNoAdj(n, paths)));
    }
}
