package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {

    public int leastBricks(List<List<Integer>> wall) {
        // Special case
        if (wall == null || wall.isEmpty()) {
            return 0;
        }
        // Map to store the end indices of bricks and their frequencies
        final Map<Integer, Integer> endIndexFrequencies = new HashMap<>();
        // Count of end index that occurs the most
        int countOfMostOccurringEndIndex = 0;
        // Process all the rows in the wall
        for (List<Integer> bricks : wall) {
            // End indices for the current row
            int endIndex = 0;
            // Process all bricks except for last one
            for (int i = 0; i < bricks.size() - 1; i++) {
                endIndex += bricks.get(i);
                endIndexFrequencies.put(endIndex, endIndexFrequencies.getOrDefault(endIndex, 0) + 1);
                countOfMostOccurringEndIndex = Math.max(countOfMostOccurringEndIndex, endIndexFrequencies.get(endIndex));
            }
        }
        return wall.size() - countOfMostOccurringEndIndex;
    }

    public static void main(String[] args) {
        final BrickWall brickWall = new BrickWall();

        List<List<Integer>> wall = new ArrayList<>();
        wall.add(List.of(1, 2, 2, 1));
        wall.add(List.of(3, 1, 2));
        wall.add(List.of(1, 3, 2));
        wall.add(List.of(2, 4));
        wall.add(List.of(3, 1, 2));
        wall.add(List.of(1, 3, 1, 1));
        System.out.println(brickWall.leastBricks(wall));

        wall = new ArrayList<>();
        wall.add(List.of(1));
        wall.add(List.of(1));
        wall.add(List.of(1));
        System.out.println(brickWall.leastBricks(wall));
    }
}
