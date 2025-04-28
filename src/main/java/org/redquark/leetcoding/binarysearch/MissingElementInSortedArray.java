package org.redquark.leetcoding.binarysearch;

public class MissingElementInSortedArray {

    public int missingElement(int[] nums, int k) {
        // Two pointers
        int left = 0;
        int right = nums.length - 1;
        // Perform binary search
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            // Missing element
            final int missing = nums[middle] - nums[0] - middle;
            if (missing < k) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return nums[0] + k + right;
    }

    public static void main(String[] args) {
        final MissingElementInSortedArray missingElementInSortedArray = new MissingElementInSortedArray();

        int[] nums = new int[]{4, 7, 9, 10};
        int k = 1;
        System.out.println(missingElementInSortedArray.missingElement(nums, k));

        nums = new int[]{4, 7, 9, 10};
        k = 3;
        System.out.println(missingElementInSortedArray.missingElement(nums, k));

        nums = new int[]{1, 2, 4};
        k = 3;
        System.out.println(missingElementInSortedArray.missingElement(nums, k));
    }
}
