package org.redquark.leetcoding.arrays;

import java.util.Arrays;
import java.util.Random;

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        // Base case
        if (points == null || points.length < k) {
            throw new IllegalArgumentException("Invalid inputs!");
        }
        quickSelect(points, 0, points.length - 1, k - 1);
        // Build the final result
        final int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = points[i];
        }
        return result;
    }

    private void quickSelect(int[][] points, int left, int right, int k) {
        // If there's only one element in the array left
        if (left >= right) {
            return;
        }
        // Get the pivot index by partitioning
        final int pivotIndex = partition(points, left, right);
        // If the pivot is greater than k, search the left subarray.
        if (pivotIndex > k) {
            quickSelect(points, left, pivotIndex - 1, k);
        }
        // Otherwise, search in the right subarray.
        else {
            quickSelect(points, pivotIndex + 1, right, k);
        }
    }

    private int partition(int[][] points, int left, int right) {
        // Select a random pivot index within [left, right]
        final int pivotIndex = left + new Random().nextInt(right - left + 1);
        // Swap the pivot with the rightmost element so that the pivot is out of the
        // way.
        swap(points, pivotIndex, right);
        // Use the rightmost element (our pivot) as the pivot value.
        final int pivotDistance = getEuclideanDistance(points[right]);
        int storeIndex = left;
        // Partition the array: move all points with a squared distance less than or
        // equal to pivotDistance to the left.
        for (int i = left; i < right; i++) {
            if (getEuclideanDistance(points[i]) <= pivotDistance) {
                swap(points, storeIndex, i);
                storeIndex++;
            }
        }
        // Place the pivot in its correct position
        swap(points, storeIndex, right);
        return storeIndex;
    }

    private int getEuclideanDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    private void swap(int[][] points, int i, int j) {
        final int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    public static void main(String[] args) {
        final KClosestPointsToOrigin kClosestPointsToOrigin = new KClosestPointsToOrigin();

        int[][] points = new int[][]{{1, 3}, {-2, 2}};
        int k = 1;
        System.out.println(Arrays.deepToString(kClosestPointsToOrigin.kClosest(points, k)));

        points = new int[][]{{3, 3}, {5, -1}, {-2, 4}};
        k = 2;
        System.out.println(Arrays.deepToString(kClosestPointsToOrigin.kClosest(points, k)));
    }
}
