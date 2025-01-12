package org.redquark.leetcoding.binarysearch;

public class BinarySearch {

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
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final BinarySearch binarySearch = new BinarySearch();

        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        int target = 9;
        System.out.println(binarySearch.search(nums, target));

        nums = new int[]{-1, 0, 3, 5, 9, 12};
        target = 2;
        System.out.println(binarySearch.search(nums, target));
    }
}
