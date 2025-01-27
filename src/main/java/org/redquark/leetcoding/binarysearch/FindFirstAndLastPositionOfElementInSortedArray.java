package org.redquark.leetcoding.binarysearch;

import java.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        // Array to store the final output
        final int[] output = new int[]{-1, -1};
        // Special case
        if (nums == null || nums.length == 0) {
            return output;
        }
        output[0] = findFirstOccurrence(nums, target);
        output[1] = findLastOccurrence(nums, target);
        return output;
    }

    private int findFirstOccurrence(int[] nums, int target) {
        // Left and right pointers
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
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return firstOccurrence;
    }

    private int findLastOccurrence(int[] nums, int target) {
        // Left and right pointers
        int left = 0;
        int right = nums.length - 1;
        // Index of last occurrence
        int lastOccurrence = -1;
        // Perform binary search
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                lastOccurrence = middle;
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
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
