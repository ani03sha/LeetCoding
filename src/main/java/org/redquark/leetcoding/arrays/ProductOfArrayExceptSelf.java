package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return nums;
        }
        final int n = nums.length;
        // Array to store the products
        final int[] products = new int[n];
        // Variable to keep track of cumulative product
        int cumulativeProduct = 1;
        // Process the array from left to right
        for (int i = 0; i < n; i++) {
            products[i] = cumulativeProduct;
            cumulativeProduct *= nums[i];
        }
        // Reset cumulativeProduct
        cumulativeProduct = 1;
        // Process the array from right to left except nums[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            products[i] *= cumulativeProduct;
            cumulativeProduct *= nums[i];
        }
        return products;
    }

    public static void main(String[] args) {
        final ProductOfArrayExceptSelf productOfArrayExceptSelf = new ProductOfArrayExceptSelf();

        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(productOfArrayExceptSelf.productExceptSelf(nums)));

        nums = new int[]{-1, 1, 0, -3, 3};
        System.out.println(Arrays.toString(productOfArrayExceptSelf.productExceptSelf(nums)));
    }
}
