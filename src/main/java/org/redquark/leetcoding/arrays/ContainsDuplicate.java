package org.redquark.leetcoding.arrays;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return false;
        }
        // Set to store elements of the array
        final Set<Integer> elements = new HashSet<>();
        // Process each element in the array
        for (int num : nums) {
            if (!elements.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final ContainsDuplicate containsDuplicate = new ContainsDuplicate();

        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(containsDuplicate.containsDuplicate(nums));

        nums = new int[]{1, 2, 3, 4};
        System.out.println(containsDuplicate.containsDuplicate(nums));

        nums = new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println(containsDuplicate.containsDuplicate(nums));
    }
}
