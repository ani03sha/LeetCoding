package org.redquark.leetcoding.dynamicprogramming;

import org.redquark.leetcoding.trees.CousinsInBinaryTree;

public class CountNumberOfTeams {

    public int numTeamsBruteForce(int[] rating) {
        // Special case
        if (rating == null || rating.length == 0) {
            return 0;
        }
        final int n = rating.length;
        // Total count of teams
        int count = 0;
        // Process the ratings
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (rating[i] < rating[j] && rating[j] < rating[k]) {
                        count++;
                    }
                    if (rating[i] > rating[j] && rating[j] > rating[k]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int numTeamsOptimized(int[] rating) {
        // Special case
        if (rating == null || rating.length == 0) {
            return 0;
        }
        final int n = rating.length;
        // Lookup table to store counts for an increasing/decreasing sequence
        int[] lookup = new int[n];
        // Total count of teams
        int count = 0;
        // First, take the increasing sequence into consideration
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (rating[j] < rating[i]) {
                    lookup[i]++;
                    count += lookup[j];
                }
            }
        }
        // Reset lookup table
        lookup = new int[n];
        // Now, take the decreasing sequence into consideration
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (rating[j] > rating[i]) {
                    lookup[i]++;
                    count += lookup[j];
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final CountNumberOfTeams countNumberOfTeams = new CountNumberOfTeams();

        int[] rating = new int[]{2, 5, 3, 4, 1};
        System.out.println("Brute force: " + countNumberOfTeams.numTeamsBruteForce(rating));
        System.out.println("Optimized: " + countNumberOfTeams.numTeamsOptimized(rating));

        rating = new int[]{2, 1, 3};
        System.out.println("Brute force: " + countNumberOfTeams.numTeamsBruteForce(rating));
        System.out.println("Optimized: " + countNumberOfTeams.numTeamsOptimized(rating));

        rating = new int[]{1, 2, 3, 4};
        System.out.println("Brute force: " + countNumberOfTeams.numTeamsBruteForce(rating));
        System.out.println("Optimized: " + countNumberOfTeams.numTeamsOptimized(rating));
    }
}
