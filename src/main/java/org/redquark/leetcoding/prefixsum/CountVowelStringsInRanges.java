package org.redquark.leetcoding.prefixsum;

import java.util.Arrays;
import java.util.Set;

public class CountVowelStringsInRanges {

    public int[] vowelStrings(String[] words, int[][] queries) {
        // Set of vowels
        final Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        final int n = words.length;
        // Prefix sum array to store the count of words that start and end with vowels
        int[] prefixSum = new int[n];
        prefixSum[0] = isVowelString(words[0], vowels) ? 1 : 0;
        // Calculate the prefix sum for remaining words
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + (isVowelString(words[i], vowels) ? 1 : 0);
        }
        final int[] answers = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            final int left = queries[i][0];
            final int right = queries[i][1];
            answers[i] = prefixSum[right] - (left > 0 ? prefixSum[left - 1] : 0);
        }
        return answers;
    }

    private boolean isVowelString(String word, Set<Character> vowels) {
        return vowels.contains(word.charAt(0)) && vowels.contains(word.charAt(word.length() - 1));
    }

    public static void main(String[] args) {
        final CountVowelStringsInRanges countVowelStringsInRanges = new CountVowelStringsInRanges();

        String[] words = {"aba", "bcb", "ece", "aa", "e"};
        int[][] queries = {{0, 2}, {1, 4}, {1, 1}};
        System.out.println(Arrays.toString(countVowelStringsInRanges.vowelStrings(words, queries)));

        words = new String[]{"a", "e", "i"};
        queries = new int[][]{{0, 2}, {0, 1}, {2, 2}};
        System.out.println(Arrays.toString(countVowelStringsInRanges.vowelStrings(words, queries)));
    }
}
