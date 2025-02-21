package org.redquark.leetcoding.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HighFive {

    public int[][] highFive(int[][] items) {
        // Map to store ids and their top 5 scores
        // Key ==> id, value ==> Max heap of scores
        final Map<Integer, PriorityQueue<Integer>> idScoreMappings = new HashMap<>();
        // Process all items in the array
        for (int[] item : items) {
            // The id of the current pair
            final int id = item[0];
            final int score = item[1];
            idScoreMappings.putIfAbsent(id, new PriorityQueue<>(Collections.reverseOrder()));
            idScoreMappings.get(id).offer(score);
        }
        // Prepare the output array
        final List<int[]> averageScores = new ArrayList<>();
        // Calculate the average scores
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : idScoreMappings.entrySet()) {
            final int id = entry.getKey();
            int totalScore = 0;
            for (int i = 0; i < 5; i++) {
                totalScore += entry.getValue().remove();
            }
            averageScores.add(new int[]{id, totalScore / 5});
        }
        return averageScores.toArray(new int[][]{});
    }

    public static void main(String[] args) {
        final HighFive highFive = new HighFive();

        int[][] items = new int[][]{{1, 91}, {1, 92}, {2, 93}, {2, 97}, {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100}, {2, 100}, {2, 76}};
        System.out.println(Arrays.deepToString(highFive.highFive(items)));

        items = new int[][]{{1, 100}, {7, 100}, {1, 100}, {7, 100}, {1, 100}, {7, 100}, {1, 100}, {7, 100}, {1, 100}, {7, 100}};
        System.out.println(Arrays.deepToString(highFive.highFive(items)));
    }
}
