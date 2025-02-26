package org.redquark.leetcoding.arrays;

public class HeightChecker {

    public int heightChecker(int[] heights) {
        // Special case
        if (heights == null || heights.length == 0) {
            return 0;
        }
        final int n = heights.length;
        // Find the maximum elements from the array
        int max = heights[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, heights[i]);
        }
        // Create a count array of size max + 1
        final int[] counts = new int[max + 1];
        // Populate the 'counts' array with the frequencies of elements
        // in the original array
        for (int height : heights) {
            counts[height]++;
        }
        // Store the cumulative sum of the array
        for (int i = 1; i < max + 1; i++) {
            counts[i] += counts[i - 1];
        }
        // Array with correct height order
        final int[] expected = new int[n];
        // Find the index of each element of the original array in the count array, and
        // place the elements in the output array
        for (int i = n - 1; i >= 0; i--) {
            expected[counts[heights[i]] - 1] = heights[i];
            counts[heights[i]]--;
        }
        // Now compare expected with heights and increment the count
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (heights[i] != expected[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final HeightChecker heightChecker = new HeightChecker();

        int[] heights = new int[]{1, 1, 4, 2, 1, 3};
        System.out.println(heightChecker.heightChecker(heights));

        heights = new int[]{5, 1, 2, 3, 4};
        System.out.println(heightChecker.heightChecker(heights));

        heights = new int[]{1, 2, 3, 4, 5};
        System.out.println(heightChecker.heightChecker(heights));
    }
}
