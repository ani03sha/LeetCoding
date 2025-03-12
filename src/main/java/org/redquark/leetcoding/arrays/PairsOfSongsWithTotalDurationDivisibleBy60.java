package org.redquark.leetcoding.arrays;

import java.util.HashMap;
import java.util.Map;

public class PairsOfSongsWithTotalDurationDivisibleBy60 {

    public int numPairsDivisibleBy60(int[] time) {
        // Special case
        if (time == null || time.length == 0) {
            return 0;
        }
        // Map to store the frequencies of the remainder of elements of the array
        // when divided by 60
        final Map<Integer, Integer> frequencies = new HashMap<>();
        // Count of pairs
        int count = 0;
        // Process all elements in the array
        for (int t : time) {
            // Get parts of the time that is divisible by 60
            final int firstPart = t % 60;
            final int secondPart = firstPart == 0 ? 0 : 60 - firstPart;
            // Update count
            count += frequencies.getOrDefault(secondPart, 0);
            frequencies.put(firstPart, frequencies.getOrDefault(firstPart, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        final PairsOfSongsWithTotalDurationDivisibleBy60 pairsOfSongsWithTotalDurationDivisibleBy60 = new PairsOfSongsWithTotalDurationDivisibleBy60();

        int[] time = new int[]{30, 20, 150, 100, 40};
        System.out.println(pairsOfSongsWithTotalDurationDivisibleBy60.numPairsDivisibleBy60(time));

        time = new int[]{60, 60, 60};
        System.out.println(pairsOfSongsWithTotalDurationDivisibleBy60.numPairsDivisibleBy60(time));
    }
}
