package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class SortArray {

    public int[] sortArray(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return nums;
        }
        // Perform merge sort
        divide(nums, 0, nums.length - 1);
        return nums;
    }

    private void divide(int[] nums, int left, int right) {
        if (left < right) {
            final int middle = left + (right - left) / 2;
            divide(nums, left, middle);
            divide(nums, middle + 1, right);
            conquer(nums, left, middle, right);
        }
    }

    private void conquer(int[] nums, int left, int middle, int right) {
        final int x = middle - left + 1;
        final int y = right - middle;
        // Create temp left and right arrays
        final int[] L = new int[x];
        final int[] R = new int[y];
        // Copy the elements in the array
        for (int i = 0; i < x; i++) {
            L[i] = nums[i + left];
        }
        for (int j = 0; j < y; j++) {
            R[j] = nums[middle + 1 + j];
        }
        // Merge both subarrays
        int i = 0;
        int j = 0;
        int index = left;
        while (i < x && j < y) {
            if (L[i] <= R[j]) {
                nums[index] = L[i];
                i++;
            } else {
                nums[index] = R[j];
                j++;
            }
            index++;
        }
        while (i < x) {
            nums[index] = L[i];
            i++;
            index++;
        }
        while (j < y) {
            nums[index] = R[j];
            j++;
            index++;
        }
    }

    public static void main(String[] args) {
        final SortArray sortArray = new SortArray();

        int[] nums = new int[]{5, 2, 3, 1};
        System.out.println(Arrays.toString(sortArray.sortArray(nums)));

        nums = new int[]{5, 1, 1, 2, 0, 0};
        System.out.println(Arrays.toString(sortArray.sortArray(nums)));
    }
}
