package org.redquark.leetcoding.binarysearch;

public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Left and right pointers
        int left = 0;
        int right = nums.length - 1;
        // Process array from both ends
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        final SearchInsertPosition searchInsertPosition = new SearchInsertPosition();

        int[] nums = new int[]{1, 3, 5, 6};
        int target = 5;
        System.out.println(searchInsertPosition.searchInsert(nums, target));

        nums = new int[]{1, 3, 5, 6};
        target = 2;
        System.out.println(searchInsertPosition.searchInsert(nums, target));

        nums = new int[]{1, 3, 5, 6};
        target = 7;
        System.out.println(searchInsertPosition.searchInsert(nums, target));
    }
}
