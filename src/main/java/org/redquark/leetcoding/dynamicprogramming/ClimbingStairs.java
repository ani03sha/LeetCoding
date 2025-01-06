package org.redquark.leetcoding.dynamicprogramming;

public class ClimbingStairs {

    public int climbStairs(int n) {
        // Special case
        if (n <= 2) {
            return n;
        }
        int a = 1;
        int b = 2;
        int c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        final ClimbingStairs climbingStairs = new ClimbingStairs();

        System.out.println(climbingStairs.climbStairs(1));
        System.out.println(climbingStairs.climbStairs(2));
        System.out.println(climbingStairs.climbStairs(10));
        System.out.println(climbingStairs.climbStairs(45));
    }
}
