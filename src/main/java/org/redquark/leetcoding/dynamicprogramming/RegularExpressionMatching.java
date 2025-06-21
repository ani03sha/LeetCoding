package org.redquark.leetcoding.dynamicprogramming;

public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        // Lengths of two strings
        final int m = s.length();
        final int n = p.length();
        // Lookup table to store if s[0...i] matches p[0...j]
        final boolean[][] lookup = new boolean[m + 1][n + 1];
        // For empty strings
        lookup[0][0] = true;
        // For empty string s, p like a*, a*b* matching
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*' && lookup[0][j - 2]) {
                lookup[0][j] = true;
            }
        }
        // Build the remaining table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Current characters
                final char sc = s.charAt(i - 1);
                final char pc = p.charAt(j - 1);
                // Both characters are equal or a '.'
                if (pc == sc || pc == '.') {
                    lookup[i][j] = lookup[i - 1][j - 1];
                }
                // For pc = '*'
                else if (pc == '*') {
                    final char previous = p.charAt(j - 2);
                    // Case 1: Treat '*' and its preceding character as empty
                    lookup[i][j] = lookup[i][j - 2];
                    // Case 2: Use '*' to match one or more of previous char
                    if (previous == '.' || previous == sc) {
                        lookup[i][j] |= lookup[i - 1][j];
                    }
                }
            }
        }
        return lookup[m][n];
    }

    public static void main(String[] args) {
        final RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();

        String s = "aa";
        String p = "a";
        System.out.println(regularExpressionMatching.isMatch(s, p)); // false

        s = "aa";
        p = "a*";
        System.out.println(regularExpressionMatching.isMatch(s, p)); // true

        s = "ab";
        p = ".*";
        System.out.println(regularExpressionMatching.isMatch(s, p)); // true

        s = "aab";
        p = "c*a*b";
        System.out.println(regularExpressionMatching.isMatch(s, p)); // true

        s = "mississippi";
        p = "mis*is*p*.";
        System.out.println(regularExpressionMatching.isMatch(s, p)); // false
    }
}
