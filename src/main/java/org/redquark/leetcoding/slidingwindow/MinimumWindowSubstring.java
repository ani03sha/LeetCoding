package org.redquark.leetcoding.slidingwindow;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        // Array to store frequencies of characters in t
        final int[] frequencies = new int[128];
        for (char c : t.toCharArray()) {
            frequencies[c]++;
        }
        final int n = s.length();
        // Left and right pointers of the sliding window
        int left = 0;
        int right = 0;
        // Length of the minimum window
        int minLength = Integer.MAX_VALUE;
        // Count of characters in t that needs to be matched
        int count = t.length();
        // Start index of the window
        int start = 0;
        // Process the string s
        while (right < n) {
            // If the current character in s is a part of characters in t,
            // it means we have matched one character
            if (frequencies[s.charAt(right)] > 0) {
                count--;
            }
            frequencies[s.charAt(right)]--;
            right++;
            // Check if the count is 0
            while (count == 0) {
                if (right - left < minLength) {
                    minLength = right - left;
                    start = left;
                }
                // Check if the character at left exists in the substring
                if (frequencies[s.charAt(left)] == 0) {
                    count++;
                }
                frequencies[s.charAt(left)]++;
                left++;
            }
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
