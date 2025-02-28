package org.redquark.leetcoding.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {

    public List<Integer> getRow(int rowIndex) {
        // List to store the elements in the given row
        final List<Integer> elements = new ArrayList<>();
        elements.add(1);
        // Previous value
        long previous = 1;
        // Process until the row index
        for (int i = 1; i <= rowIndex; i++) {
            final long next = previous * (rowIndex - i + 1) / i;
            elements.add((int) next);
            previous = next;
        }
        return elements;
    }

    public static void main(String[] args) {
        final PascalsTriangleII pascalsTriangleII = new PascalsTriangleII();

        System.out.println(pascalsTriangleII.getRow(3));
        System.out.println(pascalsTriangleII.getRow(0));
        System.out.println(pascalsTriangleII.getRow(1));
    }
}
