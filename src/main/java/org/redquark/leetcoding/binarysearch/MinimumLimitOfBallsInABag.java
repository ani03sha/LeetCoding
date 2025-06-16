package org.redquark.leetcoding.binarysearch;

public class MinimumLimitOfBallsInABag {

    public int minimumSize(int[] nums, int maxOperations) {
        // Minimum and maximum number of balls in a bag
        int left = 1;
        int right = 0;
        for (int num : nums) {
            right = Math.max(right, num);
        }
        // Perform binary search on this range
        while (left < right) {
            final int middle = left + (right - left) / 2;
            // Check if it is possible to have a valid distribution with
            // the current middle value
            if (isPossible(middle, nums, maxOperations)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    private boolean isPossible(int maxBalls, int[] nums, int maxOperations) {
        int totalOperations = 0;
        for (int num : nums) {
            totalOperations += (int) Math.ceil(num * 1.0 / maxBalls) - 1;
            if (totalOperations > maxOperations) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final MinimumLimitOfBallsInABag minimumLimitOfBallsInABag = new MinimumLimitOfBallsInABag();

        int[] nums = new int[]{9};
        int maxOperations = 2;
        System.out.println(minimumLimitOfBallsInABag.minimumSize(nums, maxOperations));

        nums = new int[]{2,4,8,2};
        maxOperations = 4;
        System.out.println(minimumLimitOfBallsInABag.minimumSize(nums, maxOperations));
    }
}
