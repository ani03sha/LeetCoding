package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class SortColors {

    public void sortColors(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return;
        }
        // Pointers to track left, middle and right
        int i = 0;
        int j = 0;
        int k = nums.length - 1;
        // Process the array
        while (j <= k) {
            if (nums[j] == 0) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[j] == 1) {
                j++;
            } else {
                swap(nums, j, k);
                k--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        final SortColors sortColors = new SortColors();

        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        sortColors.sortColors(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{2, 0, 1};
        sortColors.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
