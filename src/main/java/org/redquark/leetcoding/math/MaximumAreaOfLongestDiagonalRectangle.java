package org.redquark.leetcoding.math;

public class MaximumAreaOfLongestDiagonalRectangle {

    public int areaOfMaxDiagonal(int[][] dimensions) {
        // Longest diagonal
        int longestDiagonal = 0;
        // Maximum area
        int maxArea = 0;
        // Process all rectangles
        for (int[] dimension : dimensions) {
            final int length = dimension[0];
            final int width = dimension[1];
            final int diagonalLength = length * length + width * width;
            final int area = length * width;
            if (diagonalLength > longestDiagonal || (diagonalLength == longestDiagonal && area > maxArea)) {
                longestDiagonal = diagonalLength;
                maxArea = area;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        final MaximumAreaOfLongestDiagonalRectangle maximumAreaOfLongestDiagonalRectangle = new MaximumAreaOfLongestDiagonalRectangle();

        int[][] dimensions = {{9, 3}, {8, 6}};
        System.out.println(maximumAreaOfLongestDiagonalRectangle.areaOfMaxDiagonal(dimensions)); // 48

        dimensions = new int[][]{{3, 4}, {4, 3}};
        System.out.println(maximumAreaOfLongestDiagonalRectangle.areaOfMaxDiagonal(dimensions)); // 12
    }
}
