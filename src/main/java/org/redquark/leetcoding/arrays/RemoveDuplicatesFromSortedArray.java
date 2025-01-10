package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        // Special cas
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // Index to keep track of unique elements
        int index = 0;
        // Process the array
        int i = 0;
        while (i < n) {
            nums[index] = nums[i];
            while (i < n - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
            index++;
            i++;
        }
        return index;
    }

    public static void main(String[] args) {
        final RemoveDuplicatesFromSortedArray removeDuplicatesFromSortedArray = new RemoveDuplicatesFromSortedArray();

        int[] nums = new int[]{1, 1, 2};
        System.out.println(removeDuplicatesFromSortedArray.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicatesFromSortedArray.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
