package org.redquark.leetcoding.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MeetingScheduler {

    public List<Integer> minAvailableDurationSuboptimal(int[][] slots1, int[][] slots2, int duration) {
        // List to store the final output
        final List<Integer> availableSlot = new ArrayList<>();
        // Sort the arrays by their start time
        Arrays.sort(slots1, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(slots2, Comparator.comparingInt(a -> a[0]));
        // Pointers to keep track of current elements of the slots
        int i = 0;
        int j = 0;
        // Process both arrays together
        while (i < slots1.length && j < slots2.length) {
            // Find intersection between slots1 and slots2
            final int start = Math.max(slots1[i][0], slots2[j][0]);
            final int end = Math.min(slots1[i][1], slots2[j][1]);
            // Check if we have found a suitable slot
            if (end - start >= duration) {
                availableSlot.add(start);
                availableSlot.add(start + duration);
                return availableSlot;
            }
            if (slots1[i][1] < slots2[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return availableSlot;
    }

    public List<Integer> minAvailableDurationOptimal(int[][] slots1, int[][] slots2, int duration) {
        // Create a min heap based on the start times
        final Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparing(a -> a[0]));
        // Add all valid time slots from both persons to the heap
        for (int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration) {
                minHeap.offer(slot);
            }
        }
        for (int[] slot : slots2) {
            if (slot[1] - slot[0] >= duration) {
                minHeap.offer(slot);
            }
        }
        // Process until one element is left in the heap
        while (minHeap.size() > 1) {
            // Get the smallest time slot
            final int[] slot1 = minHeap.remove();
            // Get the second-smallest time slot (without removing it)
            final int[] slot2 = minHeap.peek();
            // Check if we have found the common workable slot
            if (slot1[1] >= slot2[0] + duration) {
                return List.of(slot2[0], slot2[0] + duration);
            }
        }
        return List.of();
    }

    public static void main(String[] args) {
        final MeetingScheduler meetingScheduler = new MeetingScheduler();

        int[][] slots1 = new int[][]{{10, 50}, {60, 120}, {140, 210}};
        int[][] slots2 = new int[][]{{0, 15}, {60, 70}};
        int duration = 8;
        System.out.println(meetingScheduler.minAvailableDurationSuboptimal(slots1, slots2, duration));
        System.out.println(meetingScheduler.minAvailableDurationOptimal(slots1, slots2, duration));

        slots1 = new int[][]{{10, 50}, {60, 120}, {140, 210}};
        slots2 = new int[][]{{0, 15}, {60, 70}};
        duration = 12;
        System.out.println(meetingScheduler.minAvailableDurationSuboptimal(slots1, slots2, duration));
        System.out.println(meetingScheduler.minAvailableDurationOptimal(slots1, slots2, duration));
    }
}
