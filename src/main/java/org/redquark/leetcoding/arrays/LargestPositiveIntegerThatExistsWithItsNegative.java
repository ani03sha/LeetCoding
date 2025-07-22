package org.redquark.leetcoding.arrays;

import java.util.HashSet;
import java.util.Set;

public class LargestPositiveIntegerThatExistsWithItsNegative {

    public int findMaxK(int[] nums) {
        // Set to store all negative elements in the array
        final Set<Integer> negatives = new HashSet<>();
        // Add all negative elements in the array to the set
        for (int num : nums) {
            if (num < 0) {
                negatives.add(num);
            }
        }
        // Variable to keep track of maximum k
        int maxK = 0;
        for (int num : nums) {
            if (negatives.contains(-num)) {
                maxK = Math.max(maxK, num);
            }
        }
        return maxK == 0 ? -1 : maxK;
    }

    public static void main(String[] args) {
        final LargestPositiveIntegerThatExistsWithItsNegative largestPositiveIntegerThatExistsWithItsNegative = new LargestPositiveIntegerThatExistsWithItsNegative();

        int[] nums = {3, -2, 1, -3};
        System.out.println(largestPositiveIntegerThatExistsWithItsNegative.findMaxK(nums)); // 3

        nums = new int[]{-1, 1, -2, 2, -3, 3};
        System.out.println(largestPositiveIntegerThatExistsWithItsNegative.findMaxK(nums)); // 3

        nums = new int[]{-1, -2, -3};
        System.out.println(largestPositiveIntegerThatExistsWithItsNegative.findMaxK(nums)); // -1
    }
}
