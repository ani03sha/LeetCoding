package org.redquark.leetcoding.dynamicprogramming;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return s;
        }
        // Start and end pointers
        int start = 0;
        int end = 0;
        // Process the string
        for (int i = 0; i < s.length(); i++) {
            // Get longest palindromic strings for even and odd lengths
            int odd = expandFromCenter(s, i, i);
            int even = expandFromCenter(s, i, i + 1);
            // Maximum of the two
            int max = Math.max(odd, even);
            if (max > end - start) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandFromCenter(String s, int left, int right) {
        if (left > right) {
            return 0;
        }
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        final LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();

        System.out.println(longestPalindromicSubstring.longestPalindrome("babad"));
        System.out.println(longestPalindromicSubstring.longestPalindrome("cbbd"));
    }
}
