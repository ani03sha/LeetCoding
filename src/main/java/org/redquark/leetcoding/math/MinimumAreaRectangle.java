package org.redquark.leetcoding.math;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumAreaRectangle {

    public int minAreaRect(int[][] points) {
        // Map to store the y-coordinates corresponding to
        // an x coordinate
        final Map<Integer, Set<Integer>> mappings = new HashMap<>();
        for (int[] point : points) {
            mappings.putIfAbsent(point[0], new HashSet<>());
            mappings.get(point[0]).add(point[1]);
        }
        // Minimum area
        int minimumArea = Integer.MAX_VALUE;
        // Process every pair of points
        for (int[] p1 : points) {
            for (int[] p2 : points) {
                // Disregard if same x or same y
                if (p1[0] == p2[0] || p1[1] == p2[1]) {
                    continue;
                }
                if (mappings.get(p1[0]).contains(p2[1]) && mappings.get(p2[0]).contains(p1[1])) {
                    minimumArea = Math.min(minimumArea, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                }
            }
        }
        return minimumArea == Integer.MAX_VALUE ? 0 : minimumArea;
    }

    public static void main(String[] args) {
        final MinimumAreaRectangle minimumAreaRectangle = new MinimumAreaRectangle();

        int[][] points = new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}};
        System.out.println(minimumAreaRectangle.minAreaRect(points));

        points = new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}};
        System.out.println(minimumAreaRectangle.minAreaRect(points));
    }
}
