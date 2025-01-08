package org.redquark.leetcoding.dynamicprogramming;

public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        // Lengths of both strings
        final int m = s1.length();
        final int n = s2.length();
        // Special case
        if (m + n != s3.length()) {
            return false;
        }
        // Lookup table to store if strings until current combination is interleaving or not
        final boolean[][] lookup = new boolean[m + 1][n + 1];
        // If both strings are empty
        lookup[0][0] = true;
        // For s1 not empty but s2 empty
        for (int i = 1; i <= m; i++) {
            lookup[i][0] = lookup[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        // For s2 not empty but s1 empty
        for (int j = 1; j <= n; j++) {
            lookup[0][j] = lookup[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        // For all other combinations
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                lookup[i][j] = (lookup[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (lookup[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return lookup[m][n];
    }

    public static void main(String[] args) {
        final InterleavingString interleavingString = new InterleavingString();

        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(interleavingString.isInterleave(s1, s2, s3));

        s1 = "aabcc";
        s2 = "dbbca";
        s3 = "aadbbbaccc";
        System.out.println(interleavingString.isInterleave(s1, s2, s3));

        s1 = "";
        s2 = "";
        s3 = "";
        System.out.println(interleavingString.isInterleave(s1, s2, s3));
    }
}
