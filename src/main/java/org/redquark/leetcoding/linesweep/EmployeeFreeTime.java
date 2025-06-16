package org.redquark.leetcoding.linesweep;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeFreeTime {

    public List<Interval> employeeFreeTime(List<List<Interval>> schedules) {
        // Flatten the intervals
        final List<Interval> intervals = new ArrayList<>();
        for (List<Interval> schedule : schedules) {
            intervals.addAll(schedule);
        }
        // Sort the intervals based on their start times
        intervals.sort(Comparator.comparingInt(a -> a.start));
        // Variable to keep track of end time
        int endTime = intervals.getFirst().end;
        // List to store final output
        final List<Interval> freeTimes = new ArrayList<>();
        // Checking for free time between intervals
        for (Interval interval : intervals) {
            if (interval.start > endTime) {
                freeTimes.add(new Interval(endTime, interval.start));
            }
            endTime = Math.max(endTime, interval.end);
        }
        return freeTimes;
    }

    static class Interval {
        public int start;
        public int end;

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }

        public String toString() {
            return "[start: " + this.start + ", end: " + this.end + "]";
        }
    }

    public static void main(String[] args) {
        final EmployeeFreeTime employeeFreeTime = new EmployeeFreeTime();

        List<List<Interval>> schedules = List.of(
                List.of(new Interval(1, 2), new Interval(5, 6)),
                List.of(new Interval(1, 3)),
                List.of(new Interval(4, 10))
        );
        System.out.println(employeeFreeTime.employeeFreeTime(schedules));

        schedules = List.of(
                List.of(new Interval(1, 3), new Interval(6, 7)),
                List.of(new Interval(2, 4)),
                List.of(new Interval(2, 5), new Interval(9, 12))
        );
        System.out.println(employeeFreeTime.employeeFreeTime(schedules));
    }
}
