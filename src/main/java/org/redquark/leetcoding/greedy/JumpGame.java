package org.redquark.leetcoding.greedy;

public class JumpGame {

    public boolean canJump(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return false;
        }
        final int n = nums.length;
        // Assume that I am standing at the last index of the array,
        // Now, I need to determine if I can reach the 0th index from here
        int currentPosition = n - 1;
        // Process array from right to left
        for (int i = n - 2; i >= 0; i--) {
            // Check if I can reach to current position from the current index
            if (nums[i] + i >= currentPosition) {
                currentPosition = i;
            }
        }
        return currentPosition == 0;
    }

    public static void main(String[] args) {
        final JumpGame jumpGame = new JumpGame();

        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(jumpGame.canJump(nums));

        nums = new int[]{3, 2, 1, 0, 4};
        System.out.println(jumpGame.canJump(nums));
    }
}
