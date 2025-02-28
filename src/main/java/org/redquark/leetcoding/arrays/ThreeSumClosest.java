package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        final int n = nums.length;
        // Sort the given array
        Arrays.sort(nums);
        // Closest sum
        int closest = nums[0] + nums[1] + nums[n - 1];
        // Process every element in the array
        for (int i = 0; i < n - 2; i++) {
            // Left and right pointers
            int j = i + 1;
            int k = n - 1;
            // Loop for other pairs
            while (j < k) {
                final int sum = nums[i] + nums[j] + nums[k];
                if (sum <= target) {
                    j++;
                } else {
                    k--;
                }
                // Update closest, if required
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        final ThreeSumClosest threeSumClosest = new ThreeSumClosest();

        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;
        System.out.println(threeSumClosest.threeSumClosest(nums, target));

        nums = new int[]{0, 0, 0};
        target = 1;
        System.out.println(threeSumClosest.threeSumClosest(nums, target));
    }
}
