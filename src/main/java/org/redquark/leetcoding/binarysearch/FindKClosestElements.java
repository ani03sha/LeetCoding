package org.redquark.leetcoding.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] nums, int k, int x) {
        // List to store the final output
        final List<Integer> kClosestElements = new ArrayList<>();
        // Special case
        if (nums == null || nums.length < k) {
            return kClosestElements;
        }
        // Left and right pointers
        int left = 0;
        int right = nums.length - k;
        // Process the array
        while (left < right) {
            final int middle = left + (right - left) / 2;
            if (x - nums[middle] > nums[middle + k] - x) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        // Add k elements from left
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
