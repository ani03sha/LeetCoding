package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class RotateArray {

    public void rotate(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length == 0 || k <= 0) {
            return;
        }
        final int n = nums.length;
        // Effective value of k
        k %= n;
        // Reverse entire array
        reverse(nums, 0, n - 1);
        // Reverse first k elements
        reverse(nums, 0, k - 1);
        // Reverse remaining elements
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            final int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        final RotateArray rotateArray = new RotateArray();

        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        rotateArray.rotate(nums, k);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{-1, -100, 3, 99};
        k = 2;
        rotateArray.rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
