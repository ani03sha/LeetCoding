package org.redquark.leetcoding.binarysearch;

public class SplitArrayTargetSum {

    public int splitArray(int[] nums, int k) {
        // Left and right pointers for the range
        int left = Integer.MIN_VALUE;
        int right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }
        // If there's only single subarray
        if (k == 1) {
            return right;
        }
        // Perform binary search in this range
        while (left < right) {
            final int target = left + (right - left) / 2;
            // Check if we can reach this target in k subarrays
            if (getSubarrays(nums, target) <= k) {
                right = target;
            } else {
                left = target + 1;
            }
        }
        return left;
    }

    private int getSubarrays(int[] nums, int target) {
        // Total subarrays required
        int count = 1;
        // Total sum
        int sum = 0;
        // Process through the given array
        for (int num : nums) {
            if (sum + num > target) {
                sum = num;
                count++;
            } else {
                sum += num;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final SplitArrayTargetSum splitArrayTargetSum = new SplitArrayTargetSum();

        int[] nums = new int[]{7, 2, 5, 10, 8};
        int k = 2;
        System.out.println(splitArrayTargetSum.splitArray(nums, k));

        nums = new int[]{1, 2, 3, 4, 5};
        k = 2;
        System.out.println(splitArrayTargetSum.splitArray(nums, k));
    }
}
