package org.redquark.leetcoding.math;

public class ReverseInteger {

    public int reverse(int x) {
        int reversedValue = 0;
        // Process the given integer x
        while (x != 0) {
            // First, find the remainder
            int remainder = x % 10;
            // Then, divide the number by 10
            x /= 10;
            // Now, we need to check for out of bounds for integer value
            if (reversedValue > Integer.MAX_VALUE / 10 || (reversedValue == Integer.MAX_VALUE && remainder > 7)) {
                return 0;
            }
            if (reversedValue < Integer.MIN_VALUE / 10 || (reversedValue == Integer.MIN_VALUE && remainder < -8)) {
                return 0;
            }
            // Perform calculation as usual
            reversedValue = reversedValue * 10 + remainder;
        }
        return reversedValue;
    }

    public static void main(String[] args) {
        final ReverseInteger reverseInteger = new ReverseInteger();

        System.out.println(reverseInteger.reverse(123));
        System.out.println(reverseInteger.reverse(-123));
        System.out.println(reverseInteger.reverse(120));
    }
}
