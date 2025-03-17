package org.redquark.leetcoding.arrays;

public class MaximumProductOfThreeNumbers {

    public int maximumProduct(int[] nums) {
        int maximum = Integer.MIN_VALUE;
        int secondMaximum = Integer.MIN_VALUE;
        int thirdMaximum = Integer.MIN_VALUE;
        int minimum = Integer.MAX_VALUE;
        int secondMinimum = Integer.MAX_VALUE;
        // Process the array
        for (int num : nums) {
            if (num > maximum) {
                thirdMaximum = secondMaximum;
                secondMaximum = maximum;
                maximum = num;
            } else if (num > secondMaximum) {
                thirdMaximum = secondMaximum;
                secondMaximum = num;
            } else if (num > thirdMaximum) {
                thirdMaximum = num;
            }
            if (num < minimum) {
                secondMinimum = minimum;
                minimum = num;
            } else if (num < secondMinimum) {
                secondMinimum = num;
            }
        }
        return Math.max(maximum * secondMaximum * thirdMaximum, minimum * secondMinimum * maximum);
    }

    public static void main(String[] args) {
        final MaximumProductOfThreeNumbers maximumProductOfThreeNumbers = new MaximumProductOfThreeNumbers();

        int[] nums = new int[]{1, 2, 3};
        System.out.println(maximumProductOfThreeNumbers.maximumProduct(nums));

        nums = new int[]{1, 2, 3, 4};
        System.out.println(maximumProductOfThreeNumbers.maximumProduct(nums));

        nums = new int[]{-1, -2, -3};
        System.out.println(maximumProductOfThreeNumbers.maximumProduct(nums));
    }
}
