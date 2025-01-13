package org.redquark.leetcoding.binarysearch;

public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        // Special case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // Left and right pointers
        int left = 0;
        int right = nums.length - 1;
        // Process array from both ends
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[left] <= nums[middle]) {
                if (target >= nums[left] && target <= nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                if (nums[middle] <= target && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final SearchInRotatedSortedArray searchInRotatedSortedArray = new SearchInRotatedSortedArray();

        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println(searchInRotatedSortedArray.search(nums, target));

        nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        target = 3;
        System.out.println(searchInRotatedSortedArray.search(nums, target));

        nums = new int[]{1};
        target = 0;
        System.out.println(searchInRotatedSortedArray.search(nums, target));
    }
}
