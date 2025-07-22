package org.redquark.leetcoding.strings;

import java.util.HashSet;
import java.util.Set;

public class LongestDuplicateSubstring {

    // Precomputed hashes and powers of the base
    private long[] powers;
    private long[] hashes;

    // Finds the longest duplicate substring in a given string
    public String longestDupSubstring(String s) {
        int base = 131; // Base value for polynomial hash calculation
        int n = s.length(); // Length of the input string
        this.powers = new long[n + 1];
        this.hashes = new long[n + 1];
        this.powers[0] = 1;
        // Precompute the powers and hashes
        for (int i = 0; i < n; ++i) {
            powers[i + 1] = this.powers[i] * base;
            hashes[i + 1] = this.hashes[i] * base + s.charAt(i);
        }
        String longestDuplicate = ""; // Store the longest duplicate substring
        int left = 1, right = n - 1; // Define search bounds
        // Perform binary search on substring length
        while (left <= right) {
            final int middle = left + (right - left) / 2; // Middle point (length of substring)
            final String duplicate = checkForDuplicate(s, middle);
            if (!duplicate.isEmpty()) {
                left = middle + 1; // If a duplicate is found, search in the upper half
                longestDuplicate = duplicate;
            } else {
                right = middle - 1; // Otherwise, search in the lower half
            }
        }
        return longestDuplicate; // Return the longest duplicate substring found
    }

    // Checks for a duplicate substring of a given length
    private String checkForDuplicate(String s, int length) {
        final int n = s.length();
        final Set<Long> seenHashes = new HashSet<>(); // Set to store previously encountered hashes
        // Iterate over each possible substring of the given length
        for (int i = 1; i + length - 1 <= n; ++i) {
            final int j = i + length - 1;
            // Compute the hash for the current substring
            final long currentHash = this.hashes[j] - this.hashes[i - 1] * this.powers[j - i + 1];
            if (seenHashes.contains(currentHash)) {
                // If the hash is already in the set, we found a duplicate
                return s.substring(i - 1, j);
            }
            // Add the current hash to the set
            seenHashes.add(currentHash);
        }
        // If no duplicate is found, return an empty string
        return "";
    }

    public static void main(String[] args) {
        final LongestDuplicateSubstring longestDuplicateSubstring = new LongestDuplicateSubstring();

        String s = "banana";
        String result = longestDuplicateSubstring.longestDupSubstring(s);
        System.out.println("Longest duplicate substring in '" + s + "': " + result);

        s = "abcd";
        result = longestDuplicateSubstring.longestDupSubstring(s);
        System.out.println("Longest duplicate substring in '" + s + "': " + result);

        s = "aabbcc";
        result = longestDuplicateSubstring.longestDupSubstring(s);
        System.out.println("Longest duplicate substring in '" + s + "': " + result);
    }
}
