package org.redquark.leetcoding.heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumIntervalsToIncludeEachQuery {

    public int[] minInterval(int[][] intervals, int[] queries) {
        final int n = queries.length;
        // Create mappings for queries and their indices
        final int[][] queryIndex = new int[n][2];
        for (int i = 0; i < n; i++) {
            queryIndex[i] = new int[]{queries[i], i};
        }
        // Sort the queries
        Arrays.sort(queryIndex, Comparator.comparingInt(query -> query[0]));
        // Sort the intervals by their start values
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        // Array to store the final result
        final int[] result = new int[n];
        // Min heap to store pair of range and end times
        final Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // Index of the current interval in the intervals
        int j = 0;
        // Process all the queries
        for (int i = 0; i < n; i++) {
            final int query = queryIndex[i][0];
            final int index = queryIndex[i][1];
            // Add all intervals to the heap for which left value is smaller
            // or equal to the current query
            while (j < intervals.length && intervals[j][0] <= query) {
                minHeap.offer(new int[]{intervals[j][1] - intervals[j][0] + 1, intervals[j][1]});
                j++;
            }
            // Remove all values from the queue for which right value is less than the query
            while (!minHeap.isEmpty() && minHeap.peek()[1] < query) {
                minHeap.remove();
            }
            result[index] = minHeap.peek() != null ? minHeap.peek()[0] : -1;
        }
        return result;
    }

    public static void main(String[] args) {
        final MinimumIntervalsToIncludeEachQuery minimumIntervalsToIncludeEachQuery = new MinimumIntervalsToIncludeEachQuery();

        int[][] intervals = new int[][]{{1, 4}, {2, 4}, {3, 6}, {4, 4}};
        int[] queries = new int[]{2, 3, 4, 5};
        System.out.println(Arrays.toString(minimumIntervalsToIncludeEachQuery.minInterval(intervals, queries)));

        intervals = new int[][]{{2, 3}, {2, 5}, {1, 8}, {20, 25}};
        queries = new int[]{2, 19, 5, 22};
        System.out.println(Arrays.toString(minimumIntervalsToIncludeEachQuery.minInterval(intervals, queries)));
    }
}
