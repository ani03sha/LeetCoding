package org.redquark.leetcoding.slidingwindow;

public class MaximumAverageSubarrayI {

    public double findMaxAverage(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length < k) {
            throw new IllegalArgumentException("Invalid input!");
        }
        // Sum of k elements
        int sum = 0;
        // Max sum of k elements
        int maxSum;
        // Calculate the sum of first k elements
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        maxSum = sum;
        // Process remaining k elements
        for (int i = k; i < nums.length; i++) {
            // Remove the element from the left from the sum
            sum -= nums[i - k];
            // Add the current element to the sum
            sum += nums[i];
            // Update max sum, if required
            maxSum = Math.max(maxSum, sum);
        }
        // Calculate and return the average
        return maxSum * 1.0 / k;
    }

    public static void main(String[] args) {
        final MaximumAverageSubarrayI maximumAverageSubarrayI = new MaximumAverageSubarrayI();

        int[] nums = new int[]{1, 12, -5, -6, 50, 3};
        int k = 4;
        System.out.println(maximumAverageSubarrayI.findMaxAverage(nums, k));

        nums = new int[]{5};
        k = 1;
        System.out.println(maximumAverageSubarrayI.findMaxAverage(nums, k));
    }
}
