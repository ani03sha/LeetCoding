package org.redquark.leetcoding.greedy;

import java.util.Arrays;

public class SetIntersectionSizeAtLeastTwo {

    public int intersectionSizeTwo(int[][] intervals) {
        // Sort the array by their end times, if end times are
        // equal, sort them by their start times.
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        // Total size of intersection
        int output = 0;
        // Start and end pointers
        int start = -1;
        int end = -1;
        // Traverse through every interval
        for (int[] interval : intervals) {
            // If the current interval's start is less than the current
            // start, it means we have already covered this interval
            if (interval[0] <= start) {
                continue;
            }
            // If the current interval's start is greater than the current
            // end, we need to add two points to cover this interval
            if (interval[0] > end) {
                output += 2;
                // Update start and end
                start = interval[1] - 1;
                end = interval[1];
            }
            // If the current interval's start is within the range, we only
            // need to add one more point to cover this interval
            else {
                output += 1;
                start = end;
                end = interval[1];
            }
        }
        return output;
    }

    public static void main(String[] args) {
        final SetIntersectionSizeAtLeastTwo setIntersectionSizeAtLeastTwo = new SetIntersectionSizeAtLeastTwo();

        int[][] intervals = new int[][]{{1, 3}, {3, 7}, {8, 9}};
        System.out.println(setIntersectionSizeAtLeastTwo.intersectionSizeTwo(intervals));

        intervals = new int[][]{{1, 3}, {1, 4}, {2, 5}, {3, 5}};
        System.out.println(setIntersectionSizeAtLeastTwo.intersectionSizeTwo(intervals));

        intervals = new int[][]{{1, 2}, {2, 3}, {2, 4}, {4, 5}};
        System.out.println(setIntersectionSizeAtLeastTwo.intersectionSizeTwo(intervals));
    }
}
