package org.redquark.leetcoding.heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        // Special case
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // Sort the intervals by their start time
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        // Min heap to store end times of meetings
        final Queue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(intervals[0][1]);
        // Process remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            // If meetings don't overlap, we remove the earliest end
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.remove();
            }
            // Add the current end to the heap
            minHeap.offer(intervals[i][1]);
        }
        return minHeap.size();
    }

    public int minMeetingRoomsChronologicalOrdering(int[][] intervals) {
        // Special case
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        final int n = intervals.length;
        // Start and end times of the meetings
        final int[] startTimes = new int[n];
        final int[] endTimes = new int[n];
        for (int i = 0; i < n; i++) {
            startTimes[i] = intervals[i][0];
            endTimes[i] = intervals[i][1];
        }
        // Sort both arrays
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);
        // Number of rooms required
        int requiredRooms = 0;
        // Pointers to keep track of start time and end time
        int i = 0;
        int j = 0;
        // Process all intervals
        while (i < n) {
            // If there's a meeting that has ended by the current meeting starts,
            // we can use the existing room for it
            if (startTimes[i] >= endTimes[j]) {
                requiredRooms -= 1;
                j++;
            }
            // We do this irrespective if a room is available or not
            requiredRooms += 1;
            i++;
        }
        return requiredRooms;
    }

    public int minMeetingRoomsOptimized(int[][] intervals) {
        // Special case
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        final int n = 1000010; // 0 <= start < end <= 106
        // Array to store delta
        final int[] delta = new int[n];
        // Process all intervals
        for (int[] interval : intervals) {
            delta[interval[0]]++; // A new meeting starts
            delta[interval[1]]--; // Existing meeting ends
        }
        // Required meeting rooms
        int count = delta[0];
        for (int i = 1; i < n; i++) {
            delta[i] += delta[i - 1];
            count = Math.max(count, delta[i]);
        }
        return count;
    }

    public static void main(String[] args) {
        final MeetingRoomsII meetingRoomsII = new MeetingRoomsII();

        int[][] intervals = new int[][]{{0, 30}, {5, 10}, {15, 20}};
        System.out.println(meetingRoomsII.minMeetingRooms(intervals));
        System.out.println(meetingRoomsII.minMeetingRoomsChronologicalOrdering(intervals));
        System.out.println(meetingRoomsII.minMeetingRoomsOptimized(intervals));

        intervals = new int[][]{{7, 10}, {2, 4}};
        System.out.println(meetingRoomsII.minMeetingRooms(intervals));
        System.out.println(meetingRoomsII.minMeetingRoomsChronologicalOrdering(intervals));
        System.out.println(meetingRoomsII.minMeetingRoomsOptimized(intervals));
    }
}
