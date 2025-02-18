package org.redquark.leetcoding.dynamicprogramming;

public class CountDifferentPalindromicSubsequences {

    public int countPalindromicSubsequences(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        final int MODULO = 1_000_000_007;
        final int n = s.length();
        // Lookup table to represent k type of substrings starting
        // at index i and ending at index j. The value of k is 4 in
        // this case ('a', 'b', 'c', 'd')
        final long[][][] lookup = new long[n][n][4];
        // For single character strings
        for (int i = 0; i < n; i++) {
            lookup[i][i][s.charAt(i) - 'a'] = 1;
        }
        // Traverse all possible substrings
        for (int len = 2; len <= n; ++len) {
            // Iterate over all possible starting points for substring
            for (int start = 0; start + len <= n; ++start) {
                int end = start + len - 1; // Calculate end index of substring
                // Try for each character a, b, c, d
                for (char c = 'a'; c <= 'd'; ++c) {
                    int charIndex = c - 'a'; // Convert char to index (0 to 3)
                    // Case 1: Characters at both ends match the current character
                    if (s.charAt(start) == c && s.charAt(end) == c) {
                        // Count is sum of inner substring counts + 2 (for the two characters added)
                        lookup[start][end][charIndex] = 2 + lookup[start + 1][end - 1][0]
                                + lookup[start + 1][end - 1][1] + lookup[start + 1][end - 1][2]
                                + lookup[start + 1][end - 1][3];
                        lookup[start][end][charIndex] %= MODULO; // Ensure mod operation
                    }
                    // Case 2: Only the start character matches
                    else if (s.charAt(start) == c) {
                        lookup[start][end][charIndex] = lookup[start][end - 1][charIndex];
                    }
                    // Case 3: Only the end character matches
                    else if (s.charAt(end) == c) {
                        lookup[start][end][charIndex] = lookup[start + 1][end][charIndex];
                    }
                    // Case 4: Neither end matches the character
                    else {
                        lookup[start][end][charIndex] = lookup[start + 1][end - 1][charIndex];
                    }
                }
            }
        }
        // Summation of counts of 'a', 'b', 'c', and 'd'
        long count = 0;
        for (int k = 0; k < 4; k++) {
            count += lookup[0][n - 1][k];
        }
        return (int) (count % MODULO);
    }

    public static void main(String[] args) {
        final CountDifferentPalindromicSubsequences countDifferentPalindromicSubsequences = new CountDifferentPalindromicSubsequences();

        System.out.println(countDifferentPalindromicSubsequences.countPalindromicSubsequences("bccb"));
        System.out.println(countDifferentPalindromicSubsequences.countPalindromicSubsequences("abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"));
    }
}
