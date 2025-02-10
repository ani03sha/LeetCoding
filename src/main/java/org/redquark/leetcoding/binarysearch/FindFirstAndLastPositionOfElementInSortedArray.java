package org.redquark.leetcoding.binarysearch;

import java.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        // Array to store first and last index of the target in nums
        final int[] indices = {-1, -1};
        // Special case
        if (nums == null || nums.length == 0) {
            return indices;
        }
        // Find the first and last occurrence of the target in nums
        indices[0] = findFirstOccurrence(nums, target);
        indices[1] = findLastOccurrence(nums, target);
        return indices;
    }

    private int findFirstOccurrence(int[] nums, int target) {
        // Left and right pointers for searching
        int left = 0;
        int right = nums.length - 1;
        // Index of first occurrence
        int firstOccurrence = -1;
        // Perform binary search
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                firstOccurrence = middle;
                right = middle - 1;
            } else if (target < nums[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return firstOccurrence;
    }

    private int findLastOccurrence(int[] nums, int target) {
        // Left and right pointers for searching
        int left = 0;
        int right = nums.length - 1;
        // Index of the last occurrence
        int lastOccurrence = -1;
        // Perform binary search
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                lastOccurrence = middle;
                left = middle + 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return lastOccurrence;
    }

    public static void main(String[] args) {
        final FindFirstAndLastPositionOfElementInSortedArray findFirstAndLastPositionOfElementInSortedArray = new FindFirstAndLastPositionOfElementInSortedArray();

        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println(Arrays.toString(findFirstAndLastPositionOfElementInSortedArray.searchRange(nums, target)));

        nums = new int[]{5, 7, 7, 8, 8, 10};
        target = 6;
        System.out.println(Arrays.toString(findFirstAndLastPositionOfElementInSortedArray.searchRange(nums, target)));

        nums = new int[]{};
        target = 0;
        System.out.println(Arrays.toString(findFirstAndLastPositionOfElementInSortedArray.searchRange(nums, target)));
    }
}
