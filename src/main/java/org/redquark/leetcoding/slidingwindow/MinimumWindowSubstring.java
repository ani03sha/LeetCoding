package org.redquark.leetcoding.slidingwindow;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        // Special case
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
            return "";
        }
        // Array to store the frequencies of the characters in t
        final int[] tFrequencies = new int[128];
        for (char c : t.toCharArray()) {
            tFrequencies[c]++;
        }
        // The characters required to be matched in s
        int required = 0;
        for (int tFrequency : tFrequencies) {
            if (tFrequency > 0) {
                required++;
            }
        }
        // Array to store the frequencies of characters in the
        // current window
        final int[] windowFrequencies = new int[128];
        // Number of characters currently satisfying their frequencies
        int formed = 0;
        // Left and right pointers of the sliding window
        int left = 0;
        int right = 0;
        // Start pointer of the minimum window substring
        int start = 0;
        // Minimum length of the window
        int minLength = Integer.MAX_VALUE;
        // Traverse the string s
        while (right < s.length()) {
            final char rightCharacter = s.charAt(right);
            windowFrequencies[rightCharacter]++;
            // Check if all frequencies of c are matched, a character
            // is formed
            if (tFrequencies[rightCharacter] > 0 && windowFrequencies[rightCharacter] == tFrequencies[rightCharacter]) {
                formed++;
            }
            // Now, try to shrink the window
            while (formed == required) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }
                char leftCharacter = s.charAt(left);
                windowFrequencies[leftCharacter]--;
                if (tFrequencies[leftCharacter] > 0 && windowFrequencies[leftCharacter] < tFrequencies[leftCharacter]) {
                    formed--;
                }
                left++;
            }
            right++;
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }

    public static void main(String[] args) {
        final MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();

        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minimumWindowSubstring.minWindow(s, t));

        s = "a";
        t = "a";
        System.out.println(minimumWindowSubstring.minWindow(s, t));

        s = "a";
        t = "aa";
        System.out.println(minimumWindowSubstring.minWindow(s, t));
    }
}
