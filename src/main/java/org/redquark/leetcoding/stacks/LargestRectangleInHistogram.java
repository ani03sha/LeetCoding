package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        // Special case
        if (heights == null || heights.length == 0) {
            return 0;
        }
        final int n = heights.length;
        // Monotonic stack to store indices
        final Deque<Integer> indices = new ArrayDeque<>();
        // Maximum area
        int maxArea = 0;
        // Process all the heights
        for (int i = 0; i <= n; i++) {
            final int height = i == n ? 0 : heights[i];
            while (!indices.isEmpty() && height < heights[indices.peek()]) {
                int currentHeight = heights[indices.pop()];
                int currentWidth = indices.isEmpty() ? i : i - indices.peek() - 1;
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }
            indices.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        final LargestRectangleInHistogram largestRectangleInHistogram = new LargestRectangleInHistogram();

        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleInHistogram.largestRectangleArea(heights));

        heights = new int[]{2, 4};
        System.out.println(largestRectangleInHistogram.largestRectangleArea(heights));
    }
}
