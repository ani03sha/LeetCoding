package org.redquark.leetcoding.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        // Set to store unique characters
        final Set<Character> uniques = new HashSet<>();
        // Left and right pointers
        int left = 0;
        int right = 0;
        // Longest length
        int longestLength = 0;
        // Process the string from left to right
        while (right < s.length()) {
            if (uniques.add(s.charAt(right))) {
                right++;
            } else {
                uniques.remove(s.charAt(left));
                left++;
            }
            longestLength = Math.max(longestLength, uniques.size());
        }
        return longestLength;
    }

    public static void main(String[] args) {
        final LongestSubstringWithoutRepeatingCharacters longestSubstringWithoutRepeatingCharacters = new LongestSubstringWithoutRepeatingCharacters();

        String s = "abcabcbb";
        System.out.println(longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s));

        s = "bbbbb";
        System.out.println(longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s));

        s = "pwwkew";
        System.out.println(longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s));
    }
}
