package org.redquark.leetcoding.twopointers;

import java.util.Arrays;

public class NextPermutation {

    public void nextPermutation(int[] nums) {
        final int n = nums.length;
        // Find the first element that is smaller than the one
        // at its right
        int index = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        // If array is sorted in reverse order, we just reverse it
        if (index == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        // Find the first element that is greater than the element
        // found above
        int j = n - 1;
        for (int i = n - 1; i >= index + 1; i--) {
            if (nums[i] > nums[index]) {
                j = i;
                break;
            }
        }
        // Swap the elements at index and j
        swap(nums, index, j);
        // Reverse the elements from index + 1 to the n
        reverse(nums, index + 1, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        final NextPermutation nextPermutation = new NextPermutation();

        int[] nums = new int[]{1, 2, 3};
        nextPermutation.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{3, 2, 1};
        nextPermutation.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 1, 5};
        nextPermutation.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
