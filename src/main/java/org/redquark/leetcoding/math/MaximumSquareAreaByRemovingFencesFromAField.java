package org.redquark.leetcoding.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaximumSquareAreaByRemovingFencesFromAField {

    private static final int MOD = 1_000_000_007;

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // Create arrays that include the field boundaries
        int[] horizontalPositions = createPositionsWithBoundaries(hFences, m);
        int[] verticalPositions = createPositionsWithBoundaries(vFences, n);
        // Sort the positions to make difference calculation easier
        Arrays.sort(horizontalPositions);
        Arrays.sort(verticalPositions);
        // Get all possible horizontal distances
        Set<Integer> horizontalDistances = getAllDistances(horizontalPositions);
        // Find the maximum square area by checking vertical distances
        long maxSquareArea = -1;
        for (int i = 0; i < verticalPositions.length; i++) {
            for (int j = i + 1; j < verticalPositions.length; j++) {
                final int verticalDistance = verticalPositions[j] - verticalPositions[i];
                // If this vertical distance also exists as a horizontal distance,
                // we can form a square
                if (horizontalDistances.contains(verticalDistance)) {
                    final long squareArea = (long) verticalDistance * verticalDistance;
                    maxSquareArea = Math.max(maxSquareArea, squareArea);
                }
            }
        }
        return maxSquareArea == -1 ? -1 : (int) (maxSquareArea % MOD);
    }

    private int[] createPositionsWithBoundaries(int[] fences, int end) {
        int[] positions = new int[fences.length + 2];
        positions[0] = 1;
        positions[positions.length - 1] = end;
        for (int i = 0; i < fences.length; i++) {
            positions[i + 1] = fences[i];
        }
        return positions;
    }

    private Set<Integer> getAllDistances(int[] positions) {
        Set<Integer> distances = new HashSet<>();
        for (int i = 0; i < positions.length; i++) {
            for (int j = i + 1; j < positions.length; j++) {
                distances.add(positions[j] - positions[i]);
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        final MaximumSquareAreaByRemovingFencesFromAField maximumSquareAreaByRemovingFencesFromAField =
                new MaximumSquareAreaByRemovingFencesFromAField();

        int m = 4;
        int n = 3;
        int[] hFences = new int[]{2, 3};
        int[] vFences = new int[]{2};
        System.out.println(maximumSquareAreaByRemovingFencesFromAField.maximizeSquareArea(m, n, hFences, vFences)); // 4

        m = 6;
        n = 7;
        hFences = new int[]{2};
        vFences = new int[]{4};
        System.out.println(maximumSquareAreaByRemovingFencesFromAField.maximizeSquareArea(m, n, hFences, vFences)); // -1
    }
}
