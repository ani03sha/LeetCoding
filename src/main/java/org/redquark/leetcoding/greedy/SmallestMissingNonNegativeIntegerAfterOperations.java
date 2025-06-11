package org.redquark.leetcoding.greedy;

public class SmallestMissingNonNegativeIntegerAfterOperations {

    public int findSmallestInteger(int[] nums, int value) {
        // Array to store the counts of every modulo
        final int[] counts = new int[value];
        // Populate this array
        for (int num : nums) {
            counts[(num % value + value) % value]++;
        }
        // Start from 0 until we find missing the smallest non-negative number
        int i = 0;
        while (true) {
            if (counts[i % value] == 0) {
                return i;
            }
            counts[i % value]--;
            i++;
        }
    }

    public static void main(String[] args) {
        final SmallestMissingNonNegativeIntegerAfterOperations smallestMissingNonNegativeIntegerAfterOperations =
                new SmallestMissingNonNegativeIntegerAfterOperations();

        int[] nums = {1, -10, 7, 13, 6, 8};
        int value = 5;
        int result = smallestMissingNonNegativeIntegerAfterOperations.findSmallestInteger(nums, value);
        System.out.println("Smallest missing non-negative integer: " + result); // Expected output: 4

        nums = new int[]{1, -10, 7, 13, 6, 8};
        value = 7;
        result = smallestMissingNonNegativeIntegerAfterOperations.findSmallestInteger(nums, value);
        System.out.println("Smallest missing non-negative integer: " + result); // Expected output: 2

    }
}
