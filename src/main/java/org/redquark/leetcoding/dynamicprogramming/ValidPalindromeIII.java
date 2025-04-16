package org.redquark.leetcoding.dynamicprogramming;

public class ValidPalindromeIII {

    public boolean isValidPalindrome(String s, int k) {
        final int n = s.length();
        // Lookup table to store minimum characters to remove
        // from a string to make it palindrome
        final int[] lookup = new int[n];
        // Previously required values from lookup
        int previous;
        int current;
        // Generate all combinations of i and j in the correct order
        for (int i = n - 2; i >= 0; i--) {
            // previous stores the value at lookup[i + 2][j - 1]
            previous = 0;
            for (int j = i + 1; j < n; j++) {
                current = lookup[j];
                // If characters at i and j are matching, we don't have
                // to delete anything
                if (s.charAt(i) == s.charAt(j)) {
                    lookup[j] = previous;
                }
                // If characters don't match, either delete from left
                // or delete from right and add the cost to lookup
                else {
                    lookup[j] = 1 + Math.min(lookup[j], lookup[j - 1]);
                }
                previous = current;
            }
        }
        return lookup[n - 1] <= k;
    }

    public static void main(String[] args) {
        final ValidPalindromeIII validPalindromeIII = new ValidPalindromeIII();

        System.out.println(validPalindromeIII.isValidPalindrome("abcdeca", 2));
        System.out.println(validPalindromeIII.isValidPalindrome("abbababa", 1));
    }
}
