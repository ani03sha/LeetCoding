package org.redquark.leetcoding.greedy;

public class JumpGameII {

    public int jump(int[] nums) {
        // Jumps required
        int jumps = 0;
        // Current position
        int currentPosition = 0;
        // Destination reached from the current index
        int destination = 0;
        // Process elements in the array
        for (int i = 0; i < nums.length - 1; i++) {
            // Maximum distance reached from the current position
            destination = Math.max(destination, i + nums[i]);
            // If we need to take jump
            if (currentPosition == i) {
                currentPosition = destination;
                jumps++;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        final JumpGameII jumpGameII = new JumpGameII();

        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(jumpGameII.jump(nums));

        nums = new int[]{2, 3, 0, 1, 4};
        System.out.println(jumpGameII.jump(nums));
    }
}
