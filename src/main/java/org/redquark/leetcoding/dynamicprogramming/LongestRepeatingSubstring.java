package org.redquark.leetcoding.dynamicprogramming;

public class LongestRepeatingSubstring {

    public int longestRepeatingSubstring(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        final int n = s.length();
        // Lookup table to store longest repeating substring in the range i...j
        final int[][] lookup = new int[n][n];
        // Length of longest repeating character
        int longestLength = 0;
        // Process all the characters in the string
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    lookup[i][j] = i > 0 ? lookup[i - 1][j - 1] + 1 : 1;
                    longestLength = Math.max(longestLength, lookup[i][j]);
                }
            }
        }
        return longestLength;
    }

    public static void main(String[] args) {
        final LongestRepeatingSubstring longestRepeatingSubstring = new LongestRepeatingSubstring();

        System.out.println(longestRepeatingSubstring.longestRepeatingSubstring("abcd"));
        System.out.println(longestRepeatingSubstring.longestRepeatingSubstring("abbaba"));
        System.out.println(longestRepeatingSubstring.longestRepeatingSubstring("aabcaabdaab"));
    }
}
