package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {

    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        final int n = nums.length;
        // List to store the missing ranges
        final List<List<Integer>> missingRanges = new ArrayList<>();
        // Special case - if the input array is empty
        if (n == 0) {
            missingRanges.add(List.of(lower, upper));
            return missingRanges;
        }
        // If the first element of the array is greater than
        // the lower
        if (nums[0] > lower) {
            missingRanges.add(List.of(lower, nums[0] - 1));
        }
        // For all elements in the array
        for (int i = 1; i < n; i++) {
            // If the difference between two consecutive elements is
            // greater than 1, it means there are a few missing numbers
            // in between them
            if (nums[i] - nums[i - 1] > 1) {
                missingRanges.add(List.of(nums[i - 1] + 1, nums[i] - 1));
            }
        }
        // If there are numbers after the last element of the array
        if (nums[n - 1] < upper) {
            missingRanges.add(List.of(nums[n - 1] + 1, upper));
        }
        return missingRanges;
    }

    public static void main(String[] args) {
        final MissingRanges missingRanges = new MissingRanges();

        int[] nums = new int[]{0, 1, 3, 50, 75};
        int lower = 0;
        int upper = 99;
        System.out.println(missingRanges.findMissingRanges(nums, lower, upper));

        nums = new int[]{-1};
        lower = -1;
        upper = -1;
        System.out.println(missingRanges.findMissingRanges(nums, lower, upper));
    }
}
