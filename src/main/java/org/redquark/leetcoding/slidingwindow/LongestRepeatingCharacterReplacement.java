package org.redquark.leetcoding.slidingwindow;

public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        // Special case
        if (s == null || s.isEmpty() || k < 0) {
            return 0;
        }
        final int n = s.length();
        // Left and right pointers
        int left = 0;
        int right = 0;
        // Longest length of the window
        int longestLength = 0;
        // Max count of the most popular character
        int maxCount = 0;
        // Array to store the frequencies of the characters
        final int[] frequencies = new int[26];
        // Process the string
        while (right < n) {
            int frequency = ++frequencies[s.charAt(right) - 'A'];
            maxCount = Math.max(maxCount, frequency);
            // If there are more than k characters that are not the same as
            // the most popular character, we shift the window
            if (right - left + 1 - maxCount > k) {
                frequencies[s.charAt(left) - 'A']--;
                left++;
            }
            longestLength = Math.max(longestLength, right - left + 1);
            right++;
        }
        return longestLength;
    }

    public static void main(String[] args) {
        final LongestRepeatingCharacterReplacement longestRepeatingCharacterReplacement = new LongestRepeatingCharacterReplacement();

        String s = "ABAB";
        int k = 2;
        System.out.println(longestRepeatingCharacterReplacement.characterReplacement(s, k));

        s = "AABABBA";
        k = 1;
        System.out.println(longestRepeatingCharacterReplacement.characterReplacement(s, k));
    }
}
