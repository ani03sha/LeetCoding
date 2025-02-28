package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return;
        }
        final int n = nums.length;
        // Index to keep track of non-zero elements of the array
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        // Add zeroes in remaining positions
        for (int i = index; i < n; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        final MoveZeroes moveZeroes = new MoveZeroes();

        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0};
        moveZeroes.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
