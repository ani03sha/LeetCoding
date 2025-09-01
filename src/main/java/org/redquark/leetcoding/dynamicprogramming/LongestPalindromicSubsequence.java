package org.redquark.leetcoding.dynamicprogramming;

public class LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        final int n = s.length();
        // Lookup table to define the longest length of palindrome between
        // indices i and j
        final int[][] lookup = new int[n][n];
        // Process the strings from both ends - left and right
        for (int i = n - 1; i >= 0; i--) {
            // Every character is a palindrome of length 1
            lookup[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                // If both characters are same, then we need to count these
                // characters plus all characters between i and j
                if (s.charAt(i) == s.charAt(j)) {
                    lookup[i][j] = 2 + lookup[i + 1][j - 1];
                }
                // If the characters are not equal, we need to take maximum
                // of previous computation
                else {
                    lookup[i][j] = Math.max(lookup[i + 1][j], lookup[i][j - 1]);
                }
            }
        }
        return lookup[0][n - 1];
    }

    public static void main(String[] args) {
        final LongestPalindromicSubsequence longestPalindromicSubsequence = new LongestPalindromicSubsequence();

        String s = "bbbab";
        System.out.println(longestPalindromicSubsequence.longestPalindromeSubseq(s));

        s = "cbbd";
        System.out.println(longestPalindromicSubsequence.longestPalindromeSubseq(s));
    }
}
