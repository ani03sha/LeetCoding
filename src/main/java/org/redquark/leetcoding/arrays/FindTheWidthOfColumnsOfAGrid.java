package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class FindTheWidthOfColumnsOfAGrid {

    public int[] findColumnWidth(int[][] grid) {
        // Grid dimensions
        final int m = grid.length;
        final int n = grid[0].length;
        // Array to store the final output
        final int[] result = new int[n];
        // Process every element in an array
        for (int i = 0; i < n; i++) {
            int maxLength = 0;
            for (int j = 0; j < m; j++) {
                int currentLength = getLength(grid[j][i]);
                maxLength = Math.max(maxLength, currentLength);
            }
            result[i] = maxLength;
        }
        return result;
    }

    private int getLength(int num) {
        if (num == 0) {
            return 1;
        }
        int length = 0;
        if (num < 0) {
            length++;
            num = -num;
        }
        while (num > 0) {
            num /= 10;
            length++;
        }
        return length;
    }

    public static void main(String[] args) {
        final FindTheWidthOfColumnsOfAGrid findTheWidthOfColumnsOfAGrid = new FindTheWidthOfColumnsOfAGrid();

        int[][] grid = {
                {1, 2, 3},
                {1234, 56, 7},
                {8, 9, 10}
        };
        int[] result = findTheWidthOfColumnsOfAGrid.findColumnWidth(grid);
        System.out.println(Arrays.toString(result));

        grid = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        result = findTheWidthOfColumnsOfAGrid.findColumnWidth(grid);
        System.out.println(Arrays.toString(result));
    }
}
