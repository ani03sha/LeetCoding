package org.redquark.leetcoding.binarysearch;

import java.util.Arrays;

public class ValidTriangleNumber {

    public int triangleNumber(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Sort the array
        Arrays.sort(nums);
        // Total count of valid triangles
        int count = 0;
        // The outer loop will take every element as side of
        // a triangle
        for (int i = 2; i < nums.length; i++) {
            // Since one side is fixed, let's find other two
            // sides before this index
            int j = 0;
            int k = i - 1;
            // Process every element between j and k
            while (j < k) {
                // Check for the validity of triangle's sides
                if (nums[i] < nums[j] + nums[k]) {
                    count += k - j;
                    // Try for smaller range
                    k--;
                }
                // Try for bigger range
                else {
                    j++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final ValidTriangleNumber validTriangleNumber = new ValidTriangleNumber();

        int[] nums = new int[]{2, 2, 3, 4};
        System.out.println(validTriangleNumber.triangleNumber(nums));

        nums = new int[]{4, 2, 3, 4};
        System.out.println(validTriangleNumber.triangleNumber(nums));
    }
}
