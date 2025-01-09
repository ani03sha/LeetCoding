package org.redquark.leetcoding.twopointers;

public class ValidPalindromeII {

    public boolean validPalindrome(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return true;
        }
        // Left and right pointers
        int left = 0;
        int right = s.length() - 1;
        // Process string from both ends
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        final ValidPalindromeII validPalindromeII = new ValidPalindromeII();

        String s = "aba";
        System.out.println(validPalindromeII.validPalindrome(s));

        s = "abca";
        System.out.println(validPalindromeII.validPalindrome(s));

        s = "abc";
        System.out.println(validPalindromeII.validPalindrome(s));
    }
}
