package org.redquark.leetcoding.prefixsum;

public class FindPivotIndex {

    public int pivotIndex(int[] nums) {
        final int n = nums.length;
        // Total sum of all elements in the array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        // Now, we need to process the array and perform two steps
        // 1. Subtract the current element from totalSum
        // 2. Check if the resultant sum is equal to the sum of the elements from the left
        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum -= nums[i];
            if (leftSum == totalSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        final FindPivotIndex findPivotIndex = new FindPivotIndex();

        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
        System.out.println(findPivotIndex.pivotIndex(nums));

        nums = new int[]{1, 2, 3};
        System.out.println(findPivotIndex.pivotIndex(nums));

        nums = new int[]{2, 1, -1};
        System.out.println(findPivotIndex.pivotIndex(nums));
    }
}
