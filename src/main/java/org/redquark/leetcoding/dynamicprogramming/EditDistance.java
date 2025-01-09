package org.redquark.leetcoding.dynamicprogramming;

public class EditDistance {

    public int minDistance(String word1, String word2) {
        // Base conditions
        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }
        final int m = word1.length();
        final int n = word2.length();
        // Lookup table to store ways to convert word1 to word2 until indices i and j
        final int[][] lookup = new int[m + 1][n + 1];
        // If word2 is empty
        for (int i = 1; i <= m; i++) {
            lookup[i][0] = i;
        }
        // If word1 is empty
        for (int j = 1; j <= n; j++) {
            lookup[0][j] = j;
        }
        // For remaining positions
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    lookup[i][j] = lookup[i - 1][j - 1];
                } else {
                    lookup[i][j] = Math.min(lookup[i - 1][j - 1], Math.min(lookup[i - 1][j], lookup[i][j - 1])) + 1;
                }
            }
        }
        return lookup[m][n];
    }

    public static void main(String[] args) {
        final EditDistance editDistance = new EditDistance();

        String word1 = "horse";
        String word2 = "ros";
        System.out.println(editDistance.minDistance(word1, word2));

        word1 = "intention";
        word2 = "execution";
        System.out.println(editDistance.minDistance(word1, word2));
    }
}
