package org.redquark.leetcoding.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        // List to store the triplets
        final List<List<Integer>> triplets = new ArrayList<>();
        // Special case
        if (nums == null || nums.length == 0) {
            return triplets;
        }
        final int n = nums.length;
        // Sort the array
        Arrays.sort(nums);
        // Process the array
        for (int i = 0; i < n; i++) {
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // Left and right pointers for the remaining array
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // Triplet is found!
                if (sum == 0) {
                    triplets.add(List.of(nums[i], nums[j], nums[k]));
                    j++;
                    // Skip duplicates
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return triplets;
    }

    public static void main(String[] args) {
        final ThreeSum threeSum = new ThreeSum();

        int[] nums = new int[]{1, 0, 1, 2, -1, -4};
        System.out.println(threeSum.threeSum(nums));

        nums = new int[]{0, 1, 1};
        System.out.println(threeSum.threeSum(nums));

        nums = new int[]{0, 0, 0};
        System.out.println(threeSum.threeSum(nums));
    }
}
