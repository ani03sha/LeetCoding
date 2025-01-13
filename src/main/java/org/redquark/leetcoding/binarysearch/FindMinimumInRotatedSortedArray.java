package org.redquark.leetcoding.binarysearch;

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        // Left and right pointers
        int left = 0;
        int right = nums.length - 1;
        // Process array from both ends
        while (left < right) {
            final int middle = left + (right - left) / 2;
            if (nums[middle] > nums[right]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return nums[right];
    }

    public static void main(String[] args) {
        final FindMinimumInRotatedSortedArray findMinimumInRotatedSortedArray = new FindMinimumInRotatedSortedArray();

        int[] nums = new int[]{3, 4, 5, 1, 2};
        System.out.println(findMinimumInRotatedSortedArray.findMin(nums));

        nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(findMinimumInRotatedSortedArray.findMin(nums));

        nums = new int[]{11, 13, 15, 17};
        System.out.println(findMinimumInRotatedSortedArray.findMin(nums));
    }
}
