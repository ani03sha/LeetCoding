package org.redquark.leetcoding.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        // Special case
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // Sort the intervals by their start times
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        // Count of meeting rooms required
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][1] > intervals[i][0]) {
                count++;
            }
        }
        return count;
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
        System.out.println(meetingRoomsII.minMeetingRoomsOptimized(intervals));

        intervals = new int[][]{{7, 10}, {2, 4}};
        System.out.println(meetingRoomsII.minMeetingRooms(intervals));
        System.out.println(meetingRoomsII.minMeetingRoomsOptimized(intervals));
    }
}
