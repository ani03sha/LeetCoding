package org.redquark.leetcoding.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalListIntersections {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // List to store the intersection of intervals
        final List<int[]> intersection = new ArrayList<>();
        // Pointers to keep track of both lists
        int i = 0;
        int j = 0;
        // Process both lists together
        while (i < firstList.length && j < secondList.length) {
            // Find the intersected part of the current intervals
            final int start = Math.max(firstList[i][0], secondList[j][0]);
            final int end = Math.min(firstList[i][1], secondList[j][1]);
            // Add a new interval to the list
            if (start <= end) {
                intersection.add(new int[]{start, end});
            }
            // Move pointer as required
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return intersection.toArray(new int[intersection.size()][]);
    }

    public static void main(String[] args) {
        final IntervalListIntersections intervalListIntersections = new IntervalListIntersections();

        int[][] firstList = new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        System.out.println(Arrays.deepToString(intervalListIntersections.intervalIntersection(firstList, secondList)));

        firstList = new int[][]{{1, 3}, {5, 9}};
        secondList = new int[][]{};
        System.out.println(Arrays.deepToString(intervalListIntersections.intervalIntersection(firstList, secondList)));
    }
}
