package org.redquark.leetcoding.math;

import java.util.Arrays;

public class SortTransformedArray {

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        final int n = nums.length;
        // Left and right pointers
        int left = 0;
        int right = n - 1;
        // Array to store the transformed result
        final int[] transformedArray = new int[n];
        // Based on the value of 'a', we will have the sort index direction
        int sortIndex = a < 0 ? 0 : n - 1;
        // Process the array from both ends and fill the transformed array
        while (left <= right) {
            // Calculate transformed values
            final int leftValue = applyQuadraticFunction(nums[left], a, b, c);
            final int rightValue = applyQuadraticFunction(nums[right], a, b, c);
            // If 'a' is negative, we will sort in ascending order
            if (a < 0) {
                if (leftValue <= rightValue) {
                    transformedArray[sortIndex] = leftValue;
                    left++;
                } else {
                    transformedArray[sortIndex] = rightValue;
                    right--;
                }
                sortIndex++;
            }
            // If 'a' is positive, we will sort in descending order
            else {
                if (leftValue > rightValue) {
                    transformedArray[sortIndex] = leftValue;
                    left++;
                } else {
                    transformedArray[sortIndex] = rightValue;
                    right--;
                }
                sortIndex--;
            }
        }
        return transformedArray;
    }

    private int applyQuadraticFunction(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }

    public static void main(String[] args) {
        final SortTransformedArray solution = new SortTransformedArray();

        // Test case 1
        int[] nums1 = {-4, -2, 2, 4};
        int a1 = 1, b1 = 3, c1 = 5;
        int[] result1 = solution.sortTransformedArray(nums1, a1, b1, c1);
        System.out.println("Test Case 1: " + Arrays.toString(result1));

        // Test case 2
        int[] nums2 = {-4, -2, 2, 4};
        int a2 = -1, b2 = 3, c2 = 5;
        int[] result2 = solution.sortTransformedArray(nums2, a2, b2, c2);
        System.out.println("Test Case 2: " + Arrays.toString(result2));
    }
}
