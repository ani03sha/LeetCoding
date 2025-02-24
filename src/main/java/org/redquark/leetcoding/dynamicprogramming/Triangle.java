package org.redquark.leetcoding.dynamicprogramming;

import java.util.List;

public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        final int n = triangle.size();
        // Lookup table to tore the minimum possible cumulative sum
        // lookup[i] => Minimum cumulative total until the i-th column
        final int[] lookup = new int[n];
        // Start from the bottom row
        for (int i = 0; i < n; i++) {
            lookup[i] = triangle.get(n - 1).get(i);
        }
        // Fill the remaining table by choosing the path with
        // minimum cost
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                lookup[j] = Math.min(lookup[j], lookup[j + 1]) + triangle.get(i).get(j);
            }
        }
        return lookup[0];
    }

    public static void main(String[] args) {
        final Triangle triangle = new Triangle();

        List<List<Integer>> triangleList = List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3));
        System.out.println(triangle.minimumTotal(triangleList));

        triangleList = List.of(List.of(-10));
        System.out.println(triangle.minimumTotal(triangleList));
    }
}
