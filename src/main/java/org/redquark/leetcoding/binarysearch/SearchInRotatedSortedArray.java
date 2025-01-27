package org.redquark.leetcoding.binarysearch;

public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        // Special case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // Left and right pointers for binary search
        int left = 0;
        int right = nums.length - 1;
        // Perform binary search within the range
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            // If we have found the target
            if (nums[middle] == target) {
                return middle;
            }
            // If the left half is properly sorted
            else if (nums[left] <= nums[middle]) {
                // See if the target is between left and middle
                if (nums[left] <= target && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
            // If the left half is not properly sorted
            else {
                // See if the target is between middle and right
                if (nums[middle] < target && target <= nums[right]) {
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
