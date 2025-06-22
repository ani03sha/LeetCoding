package org.redquark.leetcoding.dynamicprogramming;

public class WildcardMatching {

    public boolean isMatchDP(String s, String p) {
        final int m = s.length();
        final int n = p.length();
        // Lookup table to store if the first i characters in s
        // match with the first j characters in p
        final boolean[][] lookup = new boolean[m + 1][n + 1];
        // Empty string and empty patterns are a match
        lookup[0][0] = true;
        // For the first row of the cases where the pattern contains *
        // as they can match the empty string
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                lookup[0][j] = lookup[0][j - 1];
            }
        }
        // Build the lookup table bottom up
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If the current characters match, or we have '?', we can
                // propagate the previous match
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    lookup[i][j] = lookup[i - 1][j - 1];
                }
                // If the pattern contains, '*', it can either match 0 characters
                // in the string or it can match one character in the string and
                // continue matching
                else if (p.charAt(j - 1) == '*') {
                    lookup[i][j] = lookup[i - 1][j] || lookup[i][j - 1];
                }
            }
        }
        return lookup[m][n];
    }

    public boolean isMatchBacktracking(String s, String p) {
        final int m = s.length();
        final int n = p.length();
        // Pointers to traverse s and p, respectively
        int i = 0;
        int j = 0;
        // Most recent '*' in pattern p
        int starIndex = -1;
        // Position in s when last '*' was encountered
        int tempIndex = -1;
        // Process both strings
        while (i < m) {
            // Case 1 - if both characters match of there's a '?' in p
            if (j < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            }
            // Case 2 - if '*' occurs in p, we can match 0 or more characters
            else if (j < n && p.charAt(j) == '*') {
                starIndex = j;
                tempIndex = i;
                j++;
            }
            // Case 3 - characters mismatch and there's no '*' to fallback on
            else if (starIndex == -1) {
                return false;
            }
            // Case 4 - characters mismatch but we have seen a '*'
            else {
                j = starIndex + 1;
                tempIndex++;
                i = tempIndex;
            }
        }
        // Final check - leftover p characters must all be '*'
        while (j < n && p.charAt(j) == '*') {
            j++;
        }
        return j == n;
    }

    public static void main(String[] args) {
        final WildcardMatching wildcardMatching = new WildcardMatching();

        String s = "aa";
        String p = "a";
        System.out.println(wildcardMatching.isMatchDP(s, p));
        System.out.println(wildcardMatching.isMatchBacktracking(s, p));

        s = "aa";
        p = "*";
        System.out.println(wildcardMatching.isMatchDP(s, p));
        System.out.println(wildcardMatching.isMatchBacktracking(s, p));

        s = "cb";
        p = "?a";
        System.out.println(wildcardMatching.isMatchDP(s, p));
        System.out.println(wildcardMatching.isMatchBacktracking(s, p));
    }
}
