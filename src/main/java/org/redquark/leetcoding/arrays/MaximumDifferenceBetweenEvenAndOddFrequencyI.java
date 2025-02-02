package org.redquark.leetcoding.arrays;

public class MaximumDifferenceBetweenEvenAndOddFrequencyI {

    public int maxDifference(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        final int[] frequencies = new int[26];
        for (char c : s.toCharArray()) {
            frequencies[c - 'a']++;
        }
        int minEvenFrequency = Integer.MAX_VALUE;
        int maxOddFrequency = Integer.MIN_VALUE;
        for (int frequency : frequencies) {
            if (frequency > 0) {
                if (frequency % 2 == 0) {
                    minEvenFrequency = Math.min(minEvenFrequency, frequency);
                } else {
                    maxOddFrequency = Math.max(maxOddFrequency, frequency);
                }
            }
        }
        return maxOddFrequency - minEvenFrequency;
    }

    public static void main(String[] args) {
        final MaximumDifferenceBetweenEvenAndOddFrequencyI maximumDifferenceBetweenEvenAndOddFrequencyI = new MaximumDifferenceBetweenEvenAndOddFrequencyI();

        System.out.println(maximumDifferenceBetweenEvenAndOddFrequencyI.maxDifference("aaaaabbc"));
        System.out.println(maximumDifferenceBetweenEvenAndOddFrequencyI.maxDifference("abcabcab"));
    }
}
