package org.redquark.extras.solutions.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CourtAssignment {

    public Map<Integer, List<int[]>> assignCourts(int[][] intervals) {
        // Sort the intervals based on their start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        // Map to store the final output
        final Map<Integer, List<int[]>> courtAssignments = new HashMap<>();
        // Min heap to store a pair of {endTime, courtNumber}
        final Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // Current court counter
        int courtCounter = 0;
        // Process all intervals
        for (int[] interval : intervals) {
            // Start and end times of the interval
            final int start = interval[0];
            final int end = interval[1];
            // If courts are empty, we can reuse the court
            if (!minHeap.isEmpty() && minHeap.peek()[0] <= start) {
                final int[] court = minHeap.remove();
                final int courtNumber = court[1];
                courtAssignments.get(courtNumber).add(interval);
                minHeap.offer(new int[]{end, courtNumber});
            }
            // Create a new court
            else {
                courtCounter++;
                courtAssignments.computeIfAbsent(courtCounter, _ -> new ArrayList<>()).add(interval);
                minHeap.offer(new int[]{end, courtCounter});
            }
        }
        return courtAssignments;
    }

    public static void main(String[] args) {
        final CourtAssignment courtAssignment = new CourtAssignment();

        int[][] intervals = {{1, 4}, {4, 5}, {2, 4}};
        Map<Integer, List<int[]>> result = courtAssignment.assignCourts(intervals);

        for (Map.Entry<Integer, List<int[]>> entry : result.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (int[] interval : entry.getValue()) {
                System.out.print(Arrays.toString(interval) + " ");
            }
            System.out.println();
        }
    }
}
