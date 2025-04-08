package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class BuildingsWithAnOceanView {

    public int[] findBuildings(int[] heights) {
        final int n = heights.length;
        // List to store the list of indices of buildings with ocean view
        final List<Integer> oceanViewIndices = new ArrayList<>();
        // Maximum height to the right
        int max = -1;
        // Process the array from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (heights[i] > max) {
                oceanViewIndices.add(i);
                max = heights[i];
            }
        }
        // Array to store the final output
        final int[] result = new int[oceanViewIndices.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = oceanViewIndices.get(result.length - 1 - i);
        }
        return result;
    }

    public int[] findBuildingsWithMonotonicStack(int[] heights) {
        // Monotonic stack to store the next smaller element for a
        // current element in the array traversing from right
        // to left
        final Deque<Integer> stack = new ArrayDeque<>();
        // Traverse the array from right to left
        for (int i = heights.length - 1; i >= 0; i--) {
            if (stack.isEmpty() || heights[stack.peek()] < heights[i]) {
                stack.push(i);
            }
        }
        // Prepare the final output
        final int[] result = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()) {
            result[index] = stack.pop();
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        final BuildingsWithAnOceanView buildingsWithAnOceanView = new BuildingsWithAnOceanView();

        int[] heights = new int[]{4, 2, 3, 1};
        System.out.println(Arrays.toString(buildingsWithAnOceanView.findBuildings(heights)));
        System.out.println(Arrays.toString(buildingsWithAnOceanView.findBuildingsWithMonotonicStack(heights)));

        heights = new int[]{4, 3, 2, 1};
        System.out.println(Arrays.toString(buildingsWithAnOceanView.findBuildings(heights)));
        System.out.println(Arrays.toString(buildingsWithAnOceanView.findBuildingsWithMonotonicStack(heights)));

        heights = new int[]{1, 3, 2, 4};
        System.out.println(Arrays.toString(buildingsWithAnOceanView.findBuildings(heights)));
        System.out.println(Arrays.toString(buildingsWithAnOceanView.findBuildingsWithMonotonicStack(heights)));
    }
}
