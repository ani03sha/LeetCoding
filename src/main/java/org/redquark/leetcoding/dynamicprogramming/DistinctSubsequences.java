package org.redquark.leetcoding.dynamicprogramming;

public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        final int m = s.length();
        final int n = t.length();
        // Lookup table to store number of subsequences until the
        // i-th character of s and j-th character of t
        final int[][] lookup = new int[m + 1][n + 1];
        // For empty t string
        for (int i = 0; i < m; i++) {
            lookup[i][0] = 1;
        }
        // For the remaining positions
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    lookup[i][j] = lookup[i - 1][j] + lookup[i - 1][j - 1];
                } else {
                    lookup[i][j] = lookup[i - 1][j];
                }
            }
        }
        return lookup[m][n];
    }

    public static void main(String[] args) {
        final DistinctSubsequences distinctSubsequences = new DistinctSubsequences();

        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(distinctSubsequences.numDistinct(s, t));

        s = "babgbag";
        t = "bag";
        System.out.println(distinctSubsequences.numDistinct(s, t));
    }
}
