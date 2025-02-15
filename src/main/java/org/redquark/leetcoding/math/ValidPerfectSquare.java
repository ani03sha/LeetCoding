package org.redquark.leetcoding.math;

public class ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        // A perfect square is represented by sum of consecutive
        // "odd" numbers, i.e., 1 + 3 + 5 + 7 + ...
        int index = 1;
        while (num > 0) {
            num -= index;
            if (num == 0) {
                return true;
            }
            index += 2;
        }
        return false;
    }

    public static void main(String[] args) {
        final ValidPerfectSquare validPerfectSquare = new ValidPerfectSquare();

        System.out.println(validPerfectSquare.isPerfectSquare(16));
        System.out.println(validPerfectSquare.isPerfectSquare(14));
    }
}
