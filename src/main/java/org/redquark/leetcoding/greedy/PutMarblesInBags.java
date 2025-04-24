package org.redquark.leetcoding.greedy;

import java.util.Arrays;

public class PutMarblesInBags {

    public long putMarbles(int[] weights, int k) {
        final int n = weights.length;
        // Create an array to store a pair wise sum of adjacent elements
        // i.e., weights[0] + weights[1], weights[1] + weights[2], ...
        final int[] pairwiseSum = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            pairwiseSum[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(pairwiseSum);
        // Minimum cost
        long minCost = 0;
        // Maximum cost
        long maxCost = 0;
        for (int i = 0; i < k - 1; i++) {
            minCost += pairwiseSum[i];
            maxCost += pairwiseSum[n - 2 - i];
        }
        return maxCost - minCost;
    }

    public static void main(String[] args) {
        final PutMarblesInBags putMarblesInBags = new PutMarblesInBags();

        int[] weights = new int[]{1, 3, 5, 1};
        int k = 2;
        System.out.println(putMarblesInBags.putMarbles(weights, k));

        weights = new int[]{1, 3};
        k = 2;
        System.out.println(putMarblesInBags.putMarbles(weights, k));
    }
}
