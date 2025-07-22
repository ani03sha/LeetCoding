package org.redquark.leetcoding.arrays;

public class IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
        // Special case
        if (nums == null || nums.length < 3) {
            return false;
        }
        // Variables to keep track of the minimum and the second minimum
        int minimum = Integer.MAX_VALUE;
        int secondMinimum = Integer.MAX_VALUE;
        // Process all elements in the array
        for (int num : nums) {
            if (num <= minimum) {
                minimum = num;
            } else if (num <= secondMinimum) {
                secondMinimum = num;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final IncreasingTripletSubsequence increasingTripletSubsequence = new IncreasingTripletSubsequence();

        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(increasingTripletSubsequence.increasingTriplet(nums)); // true

        nums = new int[]{5, 4, 3, 2, 1};
        System.out.println(increasingTripletSubsequence.increasingTriplet(nums)); // false

        nums = new int[]{2, 1, 5, 0, 4, 6};
        System.out.println(increasingTripletSubsequence.increasingTriplet(nums)); // true
    }
}
