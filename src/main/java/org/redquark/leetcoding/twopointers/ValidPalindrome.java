package org.redquark.leetcoding.twopointers;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return true;
        }
        // Left and right pointers
        int left = 0;
        int right = s.length() - 1;
        // Process string from both ends
        while (left <= right) {
            if (isNotAlphanumeric(s.charAt(left))) {
                left++;
                continue;
            }
            if (isNotAlphanumeric(s.charAt(right))) {
                right--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isNotAlphanumeric(char c) {
        return (c < 'a' || c > 'z')
                && (c < '0' || c > '9')
                && (c < 'A' || c > 'Z');
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
