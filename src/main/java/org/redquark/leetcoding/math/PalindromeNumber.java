package org.redquark.leetcoding.math;

public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int tempX = x;
        long reverseX = 0;
        while (x > 0) {
            final int remainder = x % 10;
            x /= 10;
            reverseX = reverseX * 10 + remainder;
        }
        return tempX == reverseX;
    }

    public static void main(String[] args) {
        final PalindromeNumber palindromeNumber = new PalindromeNumber();

        System.out.println(palindromeNumber.isPalindrome(121));
        System.out.println(palindromeNumber.isPalindrome(-121));
        System.out.println(palindromeNumber.isPalindrome(10));
    }
}
