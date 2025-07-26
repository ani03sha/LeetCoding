package org.redquark.leetcoding.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] nums, int k, int x) {
        // List to store k-closest elements
        final List<Integer> kClosestElements = new ArrayList<>();
        // Edge case
        if (nums == null || nums.length < k) {
            return kClosestElements;
        }
        // Define our search space - we are searching for the best
        // starting index of our k-element window.
        // This window can start from 0 to n - k
        int left = 0;
        int right = nums.length - k;
        // Binary search for the best start index
        while (left < right) {
            final int middle = left + (right - left) / 2;
            // Compare the distance from x to the start of the window (nums[middle])
            // with the distance from x to the element just outside the window on the right (nums[middle + k])
            if (x - nums[middle] > nums[middle + k] - x) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        // Build the result
        for (int i = 0; i < k; i++) {
            kClosestElements.add(nums[i + left]);
        }
        return kClosestElements;
    }

    public static void main(String[] args) {
        final FindKClosestElements findKClosestElements = new FindKClosestElements();

        int[] nums = new int[]{1, 2, 3, 4, 5};
        int k = 4;
        int x = 3;
        System.out.println(findKClosestElements.findClosestElements(nums, k, x));

        nums = new int[]{1, 1, 2, 3, 4, 5};
        x = -1;
        System.out.println(findKClosestElements.findClosestElements(nums, k, x));
    }
}
