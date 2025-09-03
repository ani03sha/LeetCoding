package org.redquark.extras.solutions.prefixsum;

import java.util.ArrayList;
import java.util.List;

public class BoothExperience {

    public List<Long> computeExperienceFactors(String booths, int[][] queries) {
        final int n = booths.length();
        // Build prefix sums
        final int[] prefixSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + (booths.charAt(i) - '0');
        }
        // List to store the result of each query
        final List<Long> result = new ArrayList<>();
        for (int[] query : queries) {
            final int left = query[0];
            final int right = query[1];
            // Count of drones
            final int drones = prefixSums[right] - prefixSums[left - 1];
            // Count of robots
            final int robots = (right - left + 1) - drones;
            // Experience factor
            result.add((long) drones * robots);
        }
        return result;
    }

    public static void main(String[] args) {
        final BoothExperience boothExperience = new BoothExperience();

        String booths = "10101";
        int[][] queries = {
                {1, 3},
                {2, 5},
                {1, 5}
        };
        System.out.println(boothExperience.computeExperienceFactors(booths, queries));

        booths = "11100";
        queries = new int[][]{
                {2, 3},
                {1, 5}
        };
        System.out.println(boothExperience.computeExperienceFactors(booths, queries));
    }
}
