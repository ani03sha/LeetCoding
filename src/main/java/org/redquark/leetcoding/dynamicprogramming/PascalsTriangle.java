package org.redquark.leetcoding.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        // List to store Pascal's triangle
        final List<List<Integer>> triangle = new ArrayList<>();
        // Special case
        if (numRows <= 0) {
            return triangle;
        }
        // Create the first row
        triangle.add(List.of(1));
        // Process remaining rows
        for (int i = 1; i < numRows; i++) {
            // Get the previous row
            final List<Integer> previousRow = triangle.get(i - 1);
            // Current row
            final List<Integer> currentRow = new ArrayList<>();
            // Add the first element
            currentRow.add(1);
            // Loop for remaining elements in the current rows
            for (int j = 1; j < i; j++) {
                currentRow.add(previousRow.get(j - 1) + previousRow.get(j));
            }
            // Add the last elements
            currentRow.add(1);
            triangle.add(currentRow);
        }
        return triangle;
    }

    public static void main(String[] args) {
        final PascalsTriangle pascalsTriangle = new PascalsTriangle();

        System.out.println(pascalsTriangle.generate(5));
        System.out.println(pascalsTriangle.generate(1));
    }
}
