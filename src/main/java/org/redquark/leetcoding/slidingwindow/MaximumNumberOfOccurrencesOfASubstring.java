package org.redquark.leetcoding.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaximumNumberOfOccurrencesOfASubstring {

    public int maxFreq(String s, int maxLetters, int minSize) {
        // Maximum occurrences
        int maxOccurrences = 0;
        // Map to store count of substrings that follow rules
        final Map<String, Integer> counts = new HashMap<>();
        // Process the string for minSize window
        for (int i = 0; i <= s.length() - minSize; i++) {
            // Get the substring of length minSize
            final String current = s.substring(i, i + minSize);
            // Set to store unique characters in above substring
            final Set<Character> uniques = new HashSet<>();
            for (char c : current.toCharArray()) {
                uniques.add(c);
            }
            if (uniques.size() <= maxLetters) {
                counts.put(current, counts.getOrDefault(current, 0) + 1);
                maxOccurrences = Math.max(maxOccurrences, counts.get(current));
            }
        }
        return maxOccurrences;
    }

    public int maxFreqOptimized(String s, int maxLetters, int minSize) {
        // Map to store counts of substrings
        final Map<String, Integer> counts = new HashMap<>();
        // Frequency of characters in a sliding window
        final int[] frequencies = new int[26];
        // Maximum occurrences of a substring
        int maxOccurrences = 0;
        // Unique character count
        int uniques = 0;
        // Start and end pointers
        int start = 0;
        int end = 0;
        // Process the string
        while (end < s.length()) {
            final char c = s.charAt(end);
            frequencies[c - 'a']++;
            if (frequencies[c - 'a'] == 1) {
                uniques++;
            }
            // Maintain window of size 'minSize'
            if (end - start + 1 > minSize) {
                char ch = s.charAt(start);
                frequencies[ch - 'a']--;
                if (frequencies[ch - 'a'] == 0) {
                    uniques--;
                }
                start++;
            }
            // For the window of size 'minSize' and uniques is less than
            // or equal to maxLetters
            if (end - start + 1 == minSize && uniques <= maxLetters) {
                // Current substring
                final String current = s.substring(start, end + 1);
                counts.put(current, counts.getOrDefault(current, 0) + 1);
                maxOccurrences = Math.max(maxOccurrences, counts.get(current));
            }
            end++;
        }
        return maxOccurrences;
    }

    public static void main(String[] args) {
        final MaximumNumberOfOccurrencesOfASubstring maximumNumberOfOccurrencesOfASubstring = new MaximumNumberOfOccurrencesOfASubstring();

        System.out.println(maximumNumberOfOccurrencesOfASubstring.maxFreq("aababcaab", 2, 3));
        System.out.println(maximumNumberOfOccurrencesOfASubstring.maxFreqOptimized("abcabcab", 2, 3));
        System.out.println(maximumNumberOfOccurrencesOfASubstring.maxFreq("aaaa", 1, 3));
        System.out.println(maximumNumberOfOccurrencesOfASubstring.maxFreqOptimized("aaaa", 1, 3));
    }
}
