package org.redquark.leetcoding.slidingwindow;

import java.util.Arrays;

public class MinimumDifferenceBetweenHighestAndLowestOfKScores {

    public int minimumDifference(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length < k) {
            return 0;
        }
        // Sort the array
        Arrays.sort(nums);
        // Left and right pointers
        int left = 0;
        int right = k - 1;
        // Minimum difference
        int minDifference = Integer.MAX_VALUE;
        // Process remaining elements
        while (right < nums.length) {
            minDifference = Math.min(minDifference, nums[right] - nums[left]);
            left++;
            right++;
        }
        return minDifference;
    }

    public static void main(String[] args) {
        final MinimumDifferenceBetweenHighestAndLowestOfKScores minimumDifferenceBetweenHighestAndLowestOfKScores =
                new MinimumDifferenceBetweenHighestAndLowestOfKScores();

        int[] nums = new int[]{90};
        int k = 1;
        System.out.println(minimumDifferenceBetweenHighestAndLowestOfKScores.minimumDifference(nums, k));

        nums = new int[]{9, 4, 1, 7};
        k = 2;
        System.out.println(minimumDifferenceBetweenHighestAndLowestOfKScores.minimumDifference(nums, k));
    }
}
