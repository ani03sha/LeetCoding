package org.redquark.leetcoding.binarysearch;

public class Sqrtx {

    public int mySqrt(int x) {
        // Special case
        if (x < 2) {
            return x;
        }
        // Left and right pointers
        long left = 1;
        long right = x / 2;
        // Process array from both ends
        while (left <= right) {
            long middle = left + (right - left) / 2;
            long middleSquare = middle * middle;
            if (middleSquare == x) {
                return (int) middle;
            } else if (middleSquare < x) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return (int) right;
    }

    public static void main(String[] args) {
        final Sqrtx sqrtx = new Sqrtx();

        System.out.println(sqrtx.mySqrt(4));
        System.out.println(sqrtx.mySqrt(567));
        System.out.println(sqrtx.mySqrt(5441526));
    }
}
