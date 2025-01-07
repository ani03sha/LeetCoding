package org.redquark.leetcoding.dynamicprogramming;

public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // Count of palindromic substrings
        int count = 0;
        // Process the string
        for (int i = 0; i < s.length(); i++) {
            count += expandFromCenter(s, i, i); // Odd
            count += expandFromCenter(s, i, i + 1); // Even
        }
        return count;
    }

    private int expandFromCenter(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        final PalindromicSubstrings palindromicSubstrings = new PalindromicSubstrings();

        System.out.println(palindromicSubstrings.countSubstrings("abc"));
        System.out.println(palindromicSubstrings.countSubstrings("aaa"));
    }
}
