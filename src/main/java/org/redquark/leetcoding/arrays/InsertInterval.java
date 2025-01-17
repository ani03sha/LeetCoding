package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Special case
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        final int n = intervals.length;
        // List to store final intervals
        final List<int[]> finalIntervals = new ArrayList<>();
        // Index to keep track of current interval
        int index = 0;
        // Process intervals that are smaller than the new interval
        while (index < n && intervals[index][1] < newInterval[0]) {
            finalIntervals.add(intervals[index]);
            index++;
        }
        // Insert newInterval to the list
        while (index < n && intervals[index][0] <= newInterval[1]) {
            newInterval = new int[]{
                    Math.min(intervals[index][0], newInterval[0]),
                    Math.max(intervals[index][1], newInterval[1])
            };
            index++;
        }
        finalIntervals.add(newInterval);
        // Process remaining intervals
        while (index < n) {
            finalIntervals.add(intervals[index]);
            index++;
        }
        // Convert the list to 2D array for output
        return finalIntervals.toArray(new int[finalIntervals.size()][]);
    }

    public static void main(String[] args) {
        final InsertInterval insertInterval = new InsertInterval();

        int[][] intervals = new int[][]{{1, 3}, {6, 9}};
        int[] newInterval = new int[]{2, 5};
        System.out.println(Arrays.deepToString(insertInterval.insert(intervals, newInterval)));

        intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        newInterval = new int[]{4, 8};
        System.out.println(Arrays.deepToString(insertInterval.insert(intervals, newInterval)));
    }
}
