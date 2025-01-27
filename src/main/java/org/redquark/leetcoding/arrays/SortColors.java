package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class SortColors {

    public void sortColors(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return;
        }
        // Pointers to keep track of zeroes, ones and twos respectively
        int start = 0;
        int middle = 0;
        int end = nums.length - 1;
        // Process the array
        while (middle <= end) {
            // If the current element is 0, we push it to the left
            if (nums[middle] == 0) {
                swap(nums, start, middle);
                start++;
                middle++;
            }
            // If the current element is 1, we keep it as is
            else if (nums[middle] == 1) {
                middle++;
            }
            // If the current element is 2, we push it to the right
            else {
                swap(nums, middle, end);
                end--;
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
