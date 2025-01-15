package org.redquark.leetcoding.dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExtraCharactersInAString {

    public int minExtraChar(String s, String[] dictionary) {
        // Special case
        if (s == null || s.isEmpty() || dictionary == null || dictionary.length == 0) {
            return 0;
        }
        final int n = s.length();
        // Add all string to HashSet for O(1) lookup
        final Set<String> words = new HashSet<>(Arrays.asList(dictionary));
        // Lookup table
        final int[] lookup = new int[n + 1];
        Arrays.fill(lookup, n + 1);
        // For empty string
        lookup[0] = 0;
        // Process the string
        for (int i = 1; i <= n; i++) {
            lookup[i] = 1 + lookup[i - 1];
            for (int j = 1; j <= i; j++) {
                if (words.contains(s.substring(i - j, i))) {
                    lookup[i] = Math.min(lookup[i], lookup[i - j]);
                }
            }
        }
        return lookup[n];
    }

    public static void main(String[] args) {
        final ExtraCharactersInAString extraCharactersInAString = new ExtraCharactersInAString();

        String s = "leetscode";
        String[] dictionary = new String[]{"leet", "code", "leetcode"};
        System.out.println(extraCharactersInAString.minExtraChar(s, dictionary));

        s = "sayhelloworld";
        dictionary = new String[]{"hello", "world"};
        System.out.println(extraCharactersInAString.minExtraChar(s, dictionary));
    }
}
