package org.redquark.leetcoding.slidingwindow;

public class NumberOfSubarraysOfSizeKAndAverageGreaterThanOrEqualToThreshold {

    public int numOfSubarrays(int[] nums, int k, int threshold) {
        // Special case
        if (nums == null || nums.length < k) {
            return 0;
        }
        final int n = nums.length;
        // Left and right pointers
        int left = 0;
        int right = 0;
        // Sum of the elements in the subarray of size k
        int currentSum = 0;
        // Product of k and threshold
        threshold *= k;
        // Process first k elements
        while (right < k) {
            currentSum += nums[right];
            right++;
        }
        // Total number of subarrays with average greater than threshold
        int count = currentSum >= threshold ? 1 : 0;
        // Process remaining elements
        while (right < n) {
            currentSum -= nums[left];
            currentSum += nums[right];
            if (currentSum >= threshold) {
                count++;
            }
            left++;
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        final NumberOfSubarraysOfSizeKAndAverageGreaterThanOrEqualToThreshold numberOfSubarraysOfSizeKAndAverageGreaterThanOrEqualToThreshold
                = new NumberOfSubarraysOfSizeKAndAverageGreaterThanOrEqualToThreshold();

        int[] nums = new int[]{2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;
        System.out.println(numberOfSubarraysOfSizeKAndAverageGreaterThanOrEqualToThreshold.numOfSubarrays(nums, k, threshold));

        nums = new int[]{11, 13, 17, 23, 29, 31, 7, 5, 2, 3};
        threshold = 5;
        System.out.println(numberOfSubarraysOfSizeKAndAverageGreaterThanOrEqualToThreshold.numOfSubarrays(nums, k, threshold));
    }
}
