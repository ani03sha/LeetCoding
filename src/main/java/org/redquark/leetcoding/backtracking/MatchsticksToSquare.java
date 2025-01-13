package org.redquark.leetcoding.backtracking;

import java.util.Arrays;

public class MatchsticksToSquare {

    public boolean makeSquare(int[] matchsticks) {
        // Special case
        if (matchsticks == null || matchsticks.length < 4) {
            return false;
        }
        // Sum of lengths of all matchsticks
        int sum = 0;
        for (int matchstick : matchsticks) {
            sum += matchstick;
        }
        // If the sum is not divisible by 4
        if (sum % 4 != 0) {
            return false;
        }
        // Sort the array
        Arrays.sort(matchsticks);
        // Reverse the array
        int left = 0;
        int right = matchsticks.length - 1;
        while (left <= right) {
            final int temp = matchsticks[left];
            matchsticks[left] = matchsticks[right];
            matchsticks[right] = temp;
            left++;
            right--;
        }
        // Target sum to reach
        int target = sum / 4;
        // Array to mark visited matchsticks
        final boolean[] visited = new boolean[matchsticks.length];
        // Perform backtracking
        return backtrack(matchsticks, 0, 0, target, visited, 4);
    }

    private boolean backtrack(int[] matchsticks, int index, int currentSum, int target, boolean[] visited, int k) {
        if (k == 0) {
            return true;
        }
        if (currentSum == target) {
            return backtrack(matchsticks, 0, 0, target, visited, k - 1);
        }
        for (int i = index; i < matchsticks.length; i++) {
            if (visited[i] || currentSum + matchsticks[i] > target) {
                continue;
            }
            visited[i] = true;
            if (backtrack(matchsticks, i + 1, currentSum + matchsticks[i], target, visited, k)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        final MatchsticksToSquare matchsticksToSquare = new MatchsticksToSquare();

        int[] matchsticks = new int[]{1, 1, 2, 2, 2};
        System.out.println(matchsticksToSquare.makeSquare(matchsticks));

        matchsticks = new int[]{3, 3, 3, 3, 4};
        System.out.println(matchsticksToSquare.makeSquare(matchsticks));
    }
}
