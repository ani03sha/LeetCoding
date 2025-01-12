package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        // Special case
        if (asteroids == null || asteroids.length == 0) {
            return asteroids;
        }
        // Stack to store all the asteroids with their directions
        final Deque<Integer> stack = new ArrayDeque<>();
        // Process all asteroids in the array
        for (int asteroid : asteroids) {
            // If the current asteroid is positive
            if (asteroid > 0) {
                stack.push(asteroid);
            }
            // If the asteroid is negative, then we have many cases
            else {
                // If there are asteroids in the stack and the top of
                // the stack has absolute value smaller than the current
                // asteroid, we destroy the top of the stack
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroid)) {
                    stack.pop();
                }
                // If there are asteroids in the stack and the top of
                // the stack is also negative, then both asteroids are
                // moving in the same direction
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteroid);
                }
                // If both asteroids are equal, destroy both
                else if (stack.peek() == Math.abs(asteroid)) {
                    stack.pop();
                }
            }
        }
        // Array to store the final output
        final int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        final AsteroidCollision asteroidCollision = new AsteroidCollision();

        int[] asteroids = new int[]{5, 10, -5};
        System.out.println(Arrays.toString(asteroidCollision.asteroidCollision(asteroids)));

        asteroids = new int[]{8, -8};
        System.out.println(Arrays.toString(asteroidCollision.asteroidCollision(asteroids)));

        asteroids = new int[]{10, 2, -5};
        System.out.println(Arrays.toString(asteroidCollision.asteroidCollision(asteroids)));
    }
}
