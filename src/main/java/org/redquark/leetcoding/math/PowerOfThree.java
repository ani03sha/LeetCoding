package org.redquark.leetcoding.math;

public class PowerOfThree {

    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        final PowerOfThree powerOfThree = new PowerOfThree();

        System.out.println(powerOfThree.isPowerOfThree(27));
        System.out.println(powerOfThree.isPowerOfThree(0));
        System.out.println(powerOfThree.isPowerOfThree(-1));
    }
}
