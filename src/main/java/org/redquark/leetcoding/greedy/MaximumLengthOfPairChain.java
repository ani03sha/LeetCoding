package org.redquark.leetcoding.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumLengthOfPairChain {

    public int findLongestChain(int[][] pairs) {
        // Special case
        if (pairs == null || pairs.length == 0) {
            return 0;
        }
        final int n = pairs.length;
        // Sort the pairs by their second element
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        // Length of the longest chain
        int longestChainLength = 0;
        // End of the current pair
        int currentEnd = Integer.MIN_VALUE;
        // Process all pairs
        for (int[] pair : pairs) {
            if (pair[0] > currentEnd) {
                longestChainLength++;
                currentEnd = pair[1];
            }
        }
        return longestChainLength;
    }

    public static void main(String[] args) {
        final MaximumLengthOfPairChain maximumLengthOfPairChain = new MaximumLengthOfPairChain();

        int[][] pairs = new int[][]{{1, 2}, {2, 3}, {3, 4}};
        System.out.println(maximumLengthOfPairChain.findLongestChain(pairs));

        pairs = new int[][]{{1, 2}, {7, 8}, {4, 5}};
        System.out.println(maximumLengthOfPairChain.findLongestChain(pairs));
    }
}
