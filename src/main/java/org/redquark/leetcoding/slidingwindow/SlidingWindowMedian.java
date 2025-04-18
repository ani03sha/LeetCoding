package org.redquark.leetcoding.slidingwindow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class SlidingWindowMedian {

    public double[] medianSlidingWindow(int[] nums, int k) {
        final int n = nums.length;
        // Array to store the medians of the sliding windows
        final double[] medians = new double[n - k + 1];
        // Comparator to compare the elements
        final Comparator<Integer> comparator = (a, b) -> {
            if (nums[a] != nums[b]) {
                return Integer.compare(nums[a], nums[b]);
            }
            return a - b;
        };
        // TreeSet to contain elements smaller than median
        final TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
        // TreeSet to contain elements greater than median
        final TreeSet<Integer> right = new TreeSet<>(comparator);
        // Add first k elements to the left
        for (int i = 0; i < k; i++) {
            left.add(i);
        }
        // Balance both tree sets
        balance(left, right);
        // Calculate the median of the first window
        medians[0] = calculateMedian(nums, left, right, k);
        // Process remaining elements
        for (int i = k; i < n; i++) {
            if (!left.remove(i - k)) {
                right.remove(i - k);
            }
            right.add(i);
            left.add(right.removeFirst());
            balance(left, right);
            medians[i - k + 1] = calculateMedian(nums, left, right, k);
        }
        return medians;
    }

    private double calculateMedian(int[] nums, TreeSet<Integer> left, TreeSet<Integer> right, int k) {
        if (k % 2 == 0) {
            return (nums[left.first()] * 1.0 + nums[right.first()]) / 2;
        } else {
            return nums[right.first()];
        }
    }

    private void balance(TreeSet<Integer> left, TreeSet<Integer> right) {
        while (left.size() > right.size()) {
            right.add(left.removeFirst());
        }
    }

    public static void main(String[] args) {
        final SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();

        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow(nums, k)));

        nums = new int[]{1, 2, 3, 4, 2, 3, 1, 4, 2};
        k = 3;
        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow(nums, k)));
    }
}
