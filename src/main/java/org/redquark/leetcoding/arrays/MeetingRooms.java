package org.redquark.leetcoding.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        // Special case
        if (intervals == null || intervals.length == 0) {
            return false;
        }
        // Sort the intervals based on their start time
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        // Process all intervals one by one
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1][1] > intervals[i][0]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final MeetingRooms meetingRooms = new MeetingRooms();

        int[][] intervals = new int[][]{{0, 30}, {5, 10}, {15, 20}};
        System.out.println(meetingRooms.canAttendMeetings(intervals));

        intervals = new int[][]{{7, 10}, {2, 4}};
        System.out.println(meetingRooms.canAttendMeetings(intervals));
    }
}
