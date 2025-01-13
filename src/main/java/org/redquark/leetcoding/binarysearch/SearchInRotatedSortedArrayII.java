package org.redquark.leetcoding.binarysearch;

public class SearchInRotatedSortedArrayII {

    public boolean search(int[] nums, int target) {
        // Special case
        if (nums == null || nums.length == 0) {
            return false;
        }
        // Left and right pointers
        int left = 0;
        int right = nums.length - 1;
        // Process array from both ends
        while (left <= right) {
            // Middle index
            final int middle = left + (right - left) / 2;
            // If the target is present at the middle index, we return true
            if (nums[middle] == target) {
                return true;
            }
            // If the left half is correctly sorted
            else if (nums[left] < nums[middle]) {
                // If the target is present in the left half
                if (nums[left] <= target && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
            // If the left half is not correctly sorted
            else if (nums[left] > nums[middle]) {
                // If the target is present in the right half
                if (nums[middle] < target && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
            // Handle duplicates
            else {
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final SearchInRotatedSortedArrayII searchInRotatedSortedArrayII = new SearchInRotatedSortedArrayII();

        int[] nums = new int[]{2, 5, 6, 0, 0, 1, 2};
        int target = 6;
        System.out.println(searchInRotatedSortedArrayII.search(nums, target));

        nums = new int[]{2, 5, 6, 0, 0, 1, 2};
        target = 3;
        System.out.println(searchInRotatedSortedArrayII.search(nums, target));
    }
}
