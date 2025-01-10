package org.redquark.leetcoding.twopointers;

public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        // Special case
        if (height == null || height.length == 0) {
            return 0;
        }
        // Left and right pointers
        int left = 0;
        int right = height.length - 1;
        // Max area
        int maxArea = 0;
        // Process all elements
        while (left < right) {
            final int h = Math.min(height[left], height[right]);
            final int w = right - left;
            maxArea = Math.max(maxArea, h * w);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        final ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();

        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(containerWithMostWater.maxArea(height));

        height = new int[]{1, 1};
        System.out.println(containerWithMostWater.maxArea(height));
    }
}
