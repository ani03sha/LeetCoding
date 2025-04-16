package org.redquark.leetcoding.twopointers;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return true;
        }
        // Two pointers
        int left = 0;
        int right = s.length() - 1;
        // Process the string from both ends
        while (left < right) {
            // Check for non-alphanumeric characters from left and right.
            // If there are any, ignore them
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // Check if characters at both ends match
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        final ValidPalindrome validPalindrome = new ValidPalindrome();

        String s = "A man, a plan, a canal: Panama";
        System.out.println(validPalindrome.isPalindrome(s));

        s = "race a car";
        System.out.println(validPalindrome.isPalindrome(s));

        s = " ";
        System.out.println(validPalindrome.isPalindrome(s));
    }
}
