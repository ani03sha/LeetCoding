package org.redquark.leetcoding.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostTwoDistinctCharacters {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        final int n = s.length();
        // Longest length
        int longestLength = 0;
        // Map to store the frequencies of characters. This is needed
        // because we will have to remove character that is out of the
        // sliding window
        final Map<Character, Integer> frequencies = new HashMap<>();
        // Left and right pointers
        int left = 0;
        int right = 0;
        // Process the string
        while (right < n) {
            // Add current character to the map
            final char rightCharacter = s.charAt(right);
            frequencies.put(rightCharacter, frequencies.getOrDefault(rightCharacter, 0) + 1);
            // Check if the size is greater than two
            while (frequencies.size() > 2) {
                final char leftCharacter = s.charAt(left);
                // We will remove elements from the left
                frequencies.put(leftCharacter, frequencies.get(leftCharacter) - 1);
                if (frequencies.get(leftCharacter) == 0) {
                    frequencies.remove(leftCharacter);
                }
                left++;
            }
            longestLength = Math.max(longestLength, right - left + 1);
            right++;
        }
        return longestLength;
    }

    public static void main(String[] args) {
        final LongestSubstringWithAtMostTwoDistinctCharacters longestSubstringWithAtMostTwoDistinctCharacters = new LongestSubstringWithAtMostTwoDistinctCharacters();

        String s = "eceba";
        System.out.println(longestSubstringWithAtMostTwoDistinctCharacters.lengthOfLongestSubstringTwoDistinct(s));

        s = "ccaabbb";
        System.out.println(longestSubstringWithAtMostTwoDistinctCharacters.lengthOfLongestSubstringTwoDistinct(s));

        s = "abc";
        System.out.println(longestSubstringWithAtMostTwoDistinctCharacters.lengthOfLongestSubstringTwoDistinct(s));
    }
}
