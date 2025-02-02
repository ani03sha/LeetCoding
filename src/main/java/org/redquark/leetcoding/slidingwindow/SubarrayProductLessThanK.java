package org.redquark.leetcoding.slidingwindow;

public class SubarrayProductLessThanK {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length == 0 || k <= 1) {
            return 0;
        }
        // Total count of subarrays
        int count = 0;
        // Product of subarrays until the current index
        int product = 1;
        // Left and right pointers
        int left = 0;
        int right = 0;
        // Process the array
        while (right < nums.length) {
            product *= nums[right];
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            count += (right - left + 1);
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        final SubarrayProductLessThanK subarrayProductLessThanK = new SubarrayProductLessThanK();

        int[] nums = new int[]{10, 5, 2, 6};
        int k = 100;
        System.out.println(subarrayProductLessThanK.numSubarrayProductLessThanK(nums, k));

        nums = new int[]{1, 2, 3};
        k = 0;
        System.out.println(subarrayProductLessThanK.numSubarrayProductLessThanK(nums, k));
    }
}
