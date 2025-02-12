package org.redquark.leetcoding.randomized;

import java.util.Random;

public class ImplementRand10UsingRand7 {

    public int rand10() {
        // Keep on iterating until we find a suitable number
        // in the range 1-10 generated uniformly
        while (true) {
            // Generate a number between 0 and 6
            final int x = rand7() - 1;
            // Generate another number between 1 and 7
            final int y = rand7();
            // Calculate a number using x and y
            final int value = x * 7 + y;
            // If the number is less than 40, we scale it in 1-10
            if (value <= 40) {
                return value % 10 + 1;
            }
            // Else retry to generate another number
        }
    }

    private int rand7() {
        return new Random().nextInt(1, 7);
    }

    public static void main(String[] args) {
        final ImplementRand10UsingRand7 implementRand10UsingRand7 = new ImplementRand10UsingRand7();

        System.out.println(implementRand10UsingRand7.rand10());
        System.out.println(implementRand10UsingRand7.rand10());
    }
}
