package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        // Special case
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        // Sort the array based on start time
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        // List to store the merged intervals
        final List<int[]> mergedIntervals = new ArrayList<>();
        int[] currentInterval = intervals[0];
        mergedIntervals.add(currentInterval);
        // Process all the intervals
        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];
            if (nextStart <= currentEnd) {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                currentInterval = interval;
                mergedIntervals.add(currentInterval);
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        final MergeIntervals mergeIntervals = new MergeIntervals();

        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(mergeIntervals.merge(intervals)));

        intervals = new int[][]{{1, 4}, {4, 5}};
        System.out.println(Arrays.deepToString(mergeIntervals.merge(intervals)));
    }
}
