package org.redquark.leetcoding.dynamicprogramming;

public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        final int m = text1.length();
        final int n = text2.length();
        // Lookup table to store LCS until the current indices of text1 and text2
        final int[][] lookup = new int[m + 1][n + 1];
        // Process for both strings
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If current characters are same
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    lookup[i][j] = 1 + lookup[i - 1][j - 1];
                } else {
                    lookup[i][j] = Math.max(lookup[i - 1][j], lookup[i][j - 1]);
                }
            }
        }
        return lookup[m][n];
    }

    public static void main(String[] args) {
        final LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();

        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(longestCommonSubsequence.longestCommonSubsequence(text1, text2));

        text1 = "abc";
        text2 = "abc";
        System.out.println(longestCommonSubsequence.longestCommonSubsequence(text1, text2));

        text1 = "abc";
        text2 = "def";
        System.out.println(longestCommonSubsequence.longestCommonSubsequence(text1, text2));
    }
}
