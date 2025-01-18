package org.redquark.leetcoding.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestPointToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        // Special case
        if (points == null || points.length < k) {
            return new int[][]{};
        }
        // Min heap to store the distance from origin
        final Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(point -> point[0] * point[0] + point[1] * point[1]));
        Collections.addAll(minHeap, points);
        // Array to store the result
        final int[][] result = new int[k][2];
        while (k > 0) {
            result[--k] = minHeap.remove();
        }
        return result;
    }

    public int[][] kClosestOptimized(int[][] points, int k) {
        // Special case
        if (points == null || points.length < k) {
            return new int[][]{};
        }
        // Perform quick select
        quickSelect(points, 0, points.length - 1, k - 1);
        // Build final result
        final int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = points[i];
        }
        return result;
    }

    private void quickSelect(int[][] points, int left, int right, int kSmallest) {
        // Base case - found the kth smallest element
        if (left >= right) {
            return;
        }
        // Get the pivot index by partitioning
        final int pivotIndex = partition(points, left, right);
        if (pivotIndex > kSmallest) {
            // Search in the left partition
            quickSelect(points, left, pivotIndex - 1, kSmallest);
        } else {
            // Search in the right partition
            quickSelect(points, pivotIndex + 1, right, kSmallest);
        }
    }

    private int partition(int[][] points, int left, int right) {
        // Use the right most element at the pivot
        final int[] pivot = points[right];
        // Get distance of the point at pivot from origin
        final int pivotDistance = getEuclideanDistance(pivot);
        int start = left;
        for (int end = start; end < right; end++) {
            if (getEuclideanDistance(points[end]) <= pivotDistance) {
                // Swap
                int[] temp = points[start];
                points[start] = points[end];
                points[end] = temp;
                start++;
            }
        }
        // Place the pivot in it correct position
        final int[] temp = points[start];
        points[start] = points[right];
        points[right] = temp;
        return start;
    }

    private int getEuclideanDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    public static void main(String[] args) {
        final KClosestPointToOrigin kClosestPointToOrigin = new KClosestPointToOrigin();

        int[][] points = new int[][]{{1, 3}, {-2, 2}};
        int k = 1;
        System.out.println(Arrays.deepToString(kClosestPointToOrigin.kClosest(points, k)));
        System.out.println(Arrays.deepToString(kClosestPointToOrigin.kClosestOptimized(points, k)));

        points = new int[][]{{3, 3}, {5, -1}, {-2, 4}};
        k = 2;
        System.out.println(Arrays.deepToString(kClosestPointToOrigin.kClosest(points, k)));
        System.out.println(Arrays.deepToString(kClosestPointToOrigin.kClosestOptimized(points, k)));
    }
}
