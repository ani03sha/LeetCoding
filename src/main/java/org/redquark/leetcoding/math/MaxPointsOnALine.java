package org.redquark.leetcoding.math;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {

    public int maxPoints(int[][] points) {
        final int n = points.length;
        // There will always be one point on the line
        int maxCount = 1;
        // Process all points
        for (int i = 0; i < n; i++) {
            // Co-ordinates
            final int x1 = points[i][0];
            final int y1 = points[i][1];
            // Map to store all the points that are on the same line
            // as the current point
            final Map<String, Integer> pointsOnSameLine = new HashMap<>();
            // Process other points
            for (int j = i + 1; j < n; j++) {
                // Co-ordinates
                final int x2 = points[j][0];
                final int y2 = points[j][1];
                // Deltas between both coordinates
                final int deltaX = x2 - x1;
                final int deltaY = y2 - y1;
                // Find GCD of both deltaX and  deltaY to normalize them
                final int gcd = calculateGCD(deltaX, deltaY);
                final String key = (deltaX / gcd) + "." + (deltaY / gcd);
                // Update the count in the map
                pointsOnSameLine.put(key, pointsOnSameLine.getOrDefault(key, 0) + 1);
                // Update maxCount, if needed
                maxCount = Math.max(maxCount, pointsOnSameLine.get(key) + 1);
            }
        }
        return maxCount;
    }

    private int calculateGCD(int a, int b) {
        return b == 0 ? a : calculateGCD(b, a % b);
    }

    public static void main(String[] args) {
        final MaxPointsOnALine maxPointsOnALine = new MaxPointsOnALine();

        int[][] points = new int[][]{{1, 1}, {2, 2}, {3, 3}};
        System.out.println(maxPointsOnALine.maxPoints(points));

        points = new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        System.out.println(maxPointsOnALine.maxPoints(points));
    }
}
