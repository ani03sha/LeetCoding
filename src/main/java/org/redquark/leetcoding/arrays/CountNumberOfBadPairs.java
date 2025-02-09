package org.redquark.leetcoding.arrays;

import java.util.HashMap;
import java.util.Map;

public class CountNumberOfBadPairs {

    public long countBadPairs(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // we have nums[j] - nums[i] != j - i
        // that gives us nums[j] - j != nums[i] - i
        // Map to store counts of nums[i] - i
        final Map<Integer, Long> counts = new HashMap<>();
        // Total pairs
        long totalPairs = (long) n * (n - 1) / 2;
        for (int i = 0; i < n; i++) {
            final int differenceOfI = nums[i] - i;
            // Subtract bad pairs from the totalPairs
            totalPairs -= counts.getOrDefault(differenceOfI, 0L);
            counts.put(differenceOfI, counts.getOrDefault(differenceOfI, 0L) + 1);
        }
        return totalPairs;
    }

    public static void main(String[] args) {
        final CountNumberOfBadPairs countNumberOfBadPairs = new CountNumberOfBadPairs();

        int[] nums = new int[]{4, 1, 3, 3};
        System.out.println(countNumberOfBadPairs.countBadPairs(nums));

        nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(countNumberOfBadPairs.countBadPairs(nums));
    }
}
