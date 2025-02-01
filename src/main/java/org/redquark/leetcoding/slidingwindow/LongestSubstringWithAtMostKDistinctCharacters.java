package org.redquark.leetcoding.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        final int n = s.length();
        // Map to keep track of frequencies of character
        final Map<Character, Integer> frequencies = new HashMap<>();
        // Left and right pointers for the sliding window
        int left = 0;
        int right = 0;
        // Longest length
        int longestLength = 0;
        // Process the string
        while (right < n) {
            // Get the character at the right of the window and add it
            final char rightCharacter = s.charAt(right);
            frequencies.put(rightCharacter, frequencies.getOrDefault(rightCharacter, 0) + 1);
            while (frequencies.size() > k) {
                // Get the character at the left of the window and remove it
                char leftCharacter = s.charAt(left);
                frequencies.put(leftCharacter, frequencies.get(leftCharacter) - 1);
                if (frequencies.get(leftCharacter) == 0) {
                    frequencies.remove(leftCharacter);
                }
                left++;
            }
            // Update longest length
            longestLength = Math.max(longestLength, right - left + 1);
            right++;
        }
        return longestLength;
    }

    public static void main(String[] args) {
        final LongestSubstringWithAtMostKDistinctCharacters longestSubstringWithAtMostKDistinctCharacters = new LongestSubstringWithAtMostKDistinctCharacters();

        String s = "eceba";
        int k = 2;
        System.out.println(longestSubstringWithAtMostKDistinctCharacters.lengthOfLongestSubstringKDistinct(s, k));

        s = "aa";
        k = 1;
        System.out.println(longestSubstringWithAtMostKDistinctCharacters.lengthOfLongestSubstringKDistinct(s, k));
    }
}
