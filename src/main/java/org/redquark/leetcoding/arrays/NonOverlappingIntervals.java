package org.redquark.leetcoding.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        // Special case
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        final int n = intervals.length;
        // Sort the array based on their end time
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1]));
        // Count of non-overlapping intervals
        int nonOverlappingIntervals = 1;
        // End of the current interval
        int currentEnd = intervals[0][1];
        // Process all remaining intervals
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= currentEnd) {
                nonOverlappingIntervals++;
                currentEnd = intervals[i][1];
            }
        }
        return n - nonOverlappingIntervals;
    }

    public static void main(String[] args) {
        final NonOverlappingIntervals nonOverlappingIntervals = new NonOverlappingIntervals();

        int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(nonOverlappingIntervals.eraseOverlapIntervals(intervals));

        intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}};
        System.out.println(nonOverlappingIntervals.eraseOverlapIntervals(intervals));

        intervals = new int[][]{{1, 2}, {2, 3}};
        System.out.println(nonOverlappingIntervals.eraseOverlapIntervals(intervals));
    }
}
