package org.redquark.leetcoding.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRoomsIII {

    public int mostBooked(int n, int[][] meetings) {
        // Special case
        if (meetings == null || meetings.length == 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        // Array to store the availability time for each room
        final long[] roomAvailabilityTime = new long[n];
        // Array to store the count of usage of each meeting room
        final int[] roomUsageCount = new int[n];
        // Sort the meetings by their start time
        Arrays.sort(meetings, Comparator.comparingInt(meeting -> meeting[0]));
        // Process every meeting
        for (int[] meeting : meetings) {
            // Start and end times for the current meeting
            final int start = meeting[0];
            final int end = meeting[1];
            // Flag to mark if a room was found for the meeting or not
            boolean foundRoom = false;
            // Time when the next room will be available
            long minRoomAvailabilityTime = Long.MAX_VALUE;
            // Room that is available at the earliest
            int earliestAvailableRoom = 0;
            // Find the earliest available time for this room
            for (int i = 0; i < n; i++) {
                if (roomAvailabilityTime[i] <= start) {
                    roomUsageCount[i]++;
                    roomAvailabilityTime[i] = end;
                    foundRoom = true;
                    break;
                }
                if (minRoomAvailabilityTime > roomAvailabilityTime[i]) {
                    minRoomAvailabilityTime = roomAvailabilityTime[i];
                    earliestAvailableRoom = i;
                }
            }
            // If we did not find any room
            if (!foundRoom) {
                roomAvailabilityTime[earliestAvailableRoom] += end - start;
                roomUsageCount[earliestAvailableRoom]++;
            }
        }
        // Find the room with maximum meeting count
        int maxMeetingCount = 0;
        int mostUsedRoom = 0;
        for (int i = 0; i < n; i++) {
            if (roomUsageCount[i] > maxMeetingCount) {
                maxMeetingCount = roomUsageCount[i];
                mostUsedRoom = i;
            }
        }
        return mostUsedRoom;
    }

    public static void main(String[] args) {
        final MeetingRoomsIII meetingRoomsIII = new MeetingRoomsIII();

        int n = 2;
        int[][] meetings = new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}};
        System.out.println(meetingRoomsIII.mostBooked(n, meetings));

        n = 3;
        meetings = new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}};
        System.out.println(meetingRoomsIII.mostBooked(n, meetings));
    }
}
