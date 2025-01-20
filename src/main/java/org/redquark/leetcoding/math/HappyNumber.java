package org.redquark.leetcoding.math;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public boolean isHappySuboptimal(int n) {
        // Special case
        if (n == 1) {
            return true;
        }
        // Set to store already encountered values
        final Set<Integer> values = new HashSet<>();
        while (n != 1 && values.add(n)) {
            n = getSquareOfDigits(n);
        }
        return n == 1;
    }

    public boolean isHappyOptimized(int n) {
        // Special case
        if (n == 1) {
            return true;
        }
        // Slow and fast pointers
        int slow = n;
        int fast = n;
        do {
            slow = getSquareOfDigits(slow);
            fast = getSquareOfDigits(getSquareOfDigits(fast));
        } while (slow != fast);
        return slow == 1;

    }

    private int getSquareOfDigits(int n) {
        int square = 0;
        while (n > 0) {
            int digit = n % 10;
            square += digit * digit;
            n /= 10;
        }
        return square;
    }

    public static void main(String[] args) {
        final HappyNumber happyNumber = new HappyNumber();

        System.out.println(happyNumber.isHappySuboptimal(19));
        System.out.println(happyNumber.isHappyOptimized(19));

        System.out.println(happyNumber.isHappySuboptimal(2));
        System.out.println(happyNumber.isHappyOptimized(2));
    }
}
