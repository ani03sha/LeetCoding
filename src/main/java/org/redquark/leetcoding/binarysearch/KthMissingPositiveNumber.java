package org.redquark.leetcoding.binarysearch;

public class KthMissingPositiveNumber {

    public int findKthPositive(int[] nums, int k) {
        // Ideally, the array should be [1,2,3,4,5,6,...,n]
        // If the first element is greater than k, then
        // k will be the k-th missing number (think about this calmly)
        if (nums[0] > k) {
            return k;
        }
        // Since the array is sorted, we can use binary search
        int left = 0;
        int right = nums.length;
        while (left < right) {
            final int middle = left + (right - left) / 2;
            // Assume that there are no missing elements in the array,
            // then nums[i] will be i + 1.
            // Now, assume there are few missing elements, then there
            // will be nums[i] - i - 1 missing numbers
            if (nums[middle] - middle - 1 < k) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left + k;
    }

    public static void main(String[] args) {
        final KthMissingPositiveNumber kthMissingPositiveNumber = new KthMissingPositiveNumber();

        int[] nums = new int[]{2, 3, 4, 7, 11};
        int k = 5;
        System.out.println(kthMissingPositiveNumber.findKthPositive(nums, k));

        nums = new int[]{1, 2, 3, 4};
        k = 2;
        System.out.println(kthMissingPositiveNumber.findKthPositive(nums, k));
    }
}
