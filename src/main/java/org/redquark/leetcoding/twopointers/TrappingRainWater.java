package org.redquark.leetcoding.twopointers;

public class TrappingRainWater {

    public int trap(int[] height) {
        // Special case
        if (height == null || height.length == 0) {
            return 0;
        }
        // Left and right pointers
        int left = 0;
        int right = height.length - 1;
        // Water trapped
        int water = 0;
        // Left max and right max until an index i
        int leftMax = 0;
        int rightMax = 0;
        // Process the array from both ends
        while (left <= right) {
            if (height[left] <= height[right]) {
                if (leftMax < height[left]) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (rightMax < height[right]) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        final TrappingRainWater trappingRainWater = new TrappingRainWater();

        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trappingRainWater.trap(height));

        height = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(trappingRainWater.trap(height));
    }
}
