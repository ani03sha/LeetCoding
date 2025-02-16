package org.redquark.leetcoding.backtracking;

import java.util.Arrays;

public class ConstructTheLexicographicallyLargestValidSequence {

    public int[] constructDistancedSequence(int n) {
        // Array to store the final sequence
        final int[] result = new int[2 * n - 1];
        // Track visited integers
        boolean[] visited = new boolean[n + 1];
        // Perform backtracking
        backtrack(result, visited, n, 0);
        return result;
    }

    private boolean backtrack(int[] result, boolean[] visited, int n, int index) {
        // Base case: if index reaches the end of the array, a valid sequence is found
        if (index == result.length) {
            return true;
        }
        // If current position is already filled, move to the next index
        if (result[index] != 0) {
            return backtrack(result, visited, n, index + 1);
        }
        // Try placing numbers from n down to 1 to get lexicographically largest
        // sequence
        for (int i = n; i >= 1; i--) {
            if (visited[i]) {
                continue; // Skip if number already used
            }
            visited[i] = true; // Mark current number as used
            result[index] = i; // Place the number at current index
            if (i == 1) {
                // If the number is 1, it only occurs once
                if (backtrack(result, visited, n, index + 1))
                    return true;
            }
            // For numbers greater than 1, place the second occurrence at index + i
            else if (index + i < result.length && result[index + i] == 0) {
                result[index + i] = i;
                // Recursively check for the next position
                if (backtrack(result, visited, n, index + 1)) {
                    return true;
                }
                // Backtrack: reset the second occurrence if no solution found
                result[index + i] = 0;
            }
            // Backtrack: reset the current position and mark the number as unused
            result[index] = 0;
            visited[i] = false;
        }
        // No valid number could be placed at current index
        return false;
    }

    public static void main(String[] args) {
        final ConstructTheLexicographicallyLargestValidSequence constructTheLexicographicallyLargestValidSequence
                = new ConstructTheLexicographicallyLargestValidSequence();

        System.out.println(Arrays.toString(constructTheLexicographicallyLargestValidSequence.constructDistancedSequence(3)));
        System.out.println(Arrays.toString(constructTheLexicographicallyLargestValidSequence.constructDistancedSequence(5)));
        System.out.println(Arrays.toString(constructTheLexicographicallyLargestValidSequence.constructDistancedSequence(17)));
    }
}
