package org.redquark.leetcoding.linesweep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TheSkylineProblem {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        // Tree set to store the x-coordinates in the sorted order
        final Set<Integer> positions = new TreeSet<>();
        // Map to store original positions to their indices
        final Map<Integer, Integer> positionIndexMap = new HashMap<>();
        // Collect all potential start and end points from the buildings
        for (int[] building : buildings) {
            positions.add(building[0]);
            positions.add(building[1]);
        }
        // Create a map from position to index
        int index = 0;
        for (int position : positions) {
            positionIndexMap.put(position, index);
            index++;
        }
        // Array to store maximum height at each position
        final int[] maxHeights = new int[positionIndexMap.size()];
        for (int[] building : buildings) {
            final int beginIndex = positionIndexMap.get(building[0]);
            final int endIndex = positionIndexMap.get(building[1]);
            // Set the maximum height within the range
            for (int i = beginIndex; i < endIndex; i++) {
                maxHeights[i] = Math.max(maxHeights[i], building[2]);
            }
        }
        // Final result of skyline
        final List<List<Integer>> skyline = new ArrayList<>();
        // Previous position
        Integer previousPosition = null;
        for (int position : positions) {
            final int mappedIndex = positionIndexMap.get(position);
            final int currentHeight = maxHeights[mappedIndex];
            // Add point to skyline if it's the start or the height changes
            if (previousPosition == null || currentHeight != maxHeights[positionIndexMap.get(previousPosition)]) {
                skyline.add(List.of(position, currentHeight));
                previousPosition = position;
            }
        }
        return skyline;
    }

    public static void main(String[] args) {
        final TheSkylineProblem theSkylineProblem = new TheSkylineProblem();

        int[][] buildings = new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        System.out.println(theSkylineProblem.getSkyline(buildings));

        buildings = new int[][]{{0, 2, 3}, {2, 5, 3}};
        System.out.println(theSkylineProblem.getSkyline(buildings));
    }
}
