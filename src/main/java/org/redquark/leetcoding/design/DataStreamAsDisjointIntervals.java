package org.redquark.leetcoding.design;

import java.util.Arrays;
import java.util.TreeMap;

public class DataStreamAsDisjointIntervals {

    static class SummaryRanges {

        // TreeMap to store the key as start of interval and value
        // as an array of start and end values
        private final TreeMap<Integer, int[]> intervals;

        public SummaryRanges() {
            this.intervals = new TreeMap<>();
        }

        public void addNum(int value) {
            // Find the interval just before the value
            final Integer left = this.intervals.floorKey(value);
            // Find the interval just after the value
            final Integer right = this.intervals.ceilingKey(value);
            // If left, value, and right form the continuous interval
            if (left != null && right != null && this.intervals.get(left)[1] + 1 == value && this.intervals.get(right)[0] - 1 == value) {
                // Merge both intervals
                this.intervals.get(left)[1] = intervals.get(right)[1];
                // Remove the redundant right intervals
                this.intervals.remove(right);
            }
            // Extend the interval to the left if the value fits within it or
            // adjacent to it
            else if (left != null && value <= this.intervals.get(left)[1] + 1) {
                this.intervals.get(left)[1] = Math.max(value, this.intervals.get(left)[1]);
            }
            // Extend the interval to the right if the value fits within it or
            // adjacent to it
            else if (right != null && value >= this.intervals.get(right)[0] - 1) {
                this.intervals.get(right)[0] = Math.min(value, this.intervals.get(right)[0]);
            }
            // If the value is not adjacent to any interval, add it as its own interval
            else {
                this.intervals.put(value, new int[]{value, value});
            }
        }

        public int[][] getIntervals() {
            final int[][] result = new int[intervals.size()][2];
            int index = 0;
            for (int[] interval : this.intervals.values()) {
                result[index] = interval;
                index++;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);      // arr = [1]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals())); // return [[1, 1]]
        summaryRanges.addNum(3);      // arr = [1, 3]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals())); // return [[1, 1], [3, 3]]
        summaryRanges.addNum(7);      // arr = [1, 3, 7]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals())); // return [[1, 1], [3, 3], [7, 7]]
        summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals())); // return [[1, 3], [7, 7]]
        summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals())); // return [[1, 3], [6, 7]]
    }
}
