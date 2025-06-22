package org.redquark.extras.solutions.arrays;

import java.util.Arrays;
import java.util.Random;

public class FixAndOptimizeQuickSort {

    private final Random random = new Random();

    /**
     * Standard Quick Sort
     */
    public void standardQuickSort(int[] nums, int left, int right) {
        // Base case
        if (left < right) {
            // Get the partition index
            final int partitionIndex = standardPartition(nums, left, right);
            standardQuickSort(nums, left, partitionIndex - 1);
            standardQuickSort(nums, partitionIndex + 1, right);
        }
    }

    private int standardPartition(int[] nums, int left, int right) {
        // Set the pivot at the right-most element in the current partition
        final int pivot = nums[right];
        int index = left - 1;
        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                index++;
                swap(nums, index, i);
            }
        }
        swap(nums, index + 1, right);
        return index + 1;
    }

    /**
     * Randomized Quick Sort
     */
    public void randomizedQuickSort(int[] nums, int left, int right) {
        if (left < right) {
            final int partitionIndex = randomizedPartition(nums, left, right);
            randomizedQuickSort(nums, left, partitionIndex - 1);
            randomizedQuickSort(nums, partitionIndex + 1, right);
        }
    }

    private int randomizedPartition(int[] nums, int left, int right) {
        final int randomIndex = left + random.nextInt(right - left + 1);
        swap(nums, randomIndex, left);
        return standardPartition(nums, left, right);
    }

    /**
     * Median Of Three Pivot Quick Sort
     */
    public void medianOfThreePivotQuickSort(int[] nums, int left, int right) {
        if (left < right) {
            final int partitionIndex = medianOfThreePivotPartition(nums, left, right);
            medianOfThreePivotQuickSort(nums, left, partitionIndex - 1);
            medianOfThreePivotQuickSort(nums, partitionIndex + 1, right);
        }
    }

    private int medianOfThreePivotPartition(int[] nums, int left, int right) {
        final int middle = left + (right - left) / 2;
        if (nums[left] > nums[middle]) {
            swap(nums, left, middle);
        }
        if (nums[left] > nums[right]) {
            swap(nums, left, right);
        }
        if (nums[middle] > nums[right]) {
            swap(nums, middle, right);
        }
        swap(nums, middle, right);
        return standardPartition(nums, left, right);
    }

    private void swap(int[] nums, int i, int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        final FixAndOptimizeQuickSort fixAndOptimizeQuickSort = new FixAndOptimizeQuickSort();

        int[] data = {9, 3, 1, 5, 13, 12, 7, 2, 6};

        int[] arr1 = Arrays.copyOf(data, data.length);
        fixAndOptimizeQuickSort.standardQuickSort(arr1, 0, arr1.length - 1);
        System.out.println("Standard Quicksort:       " + Arrays.toString(arr1));

        int[] arr2 = Arrays.copyOf(data, data.length);
        fixAndOptimizeQuickSort.randomizedQuickSort(arr2, 0, arr2.length - 1);
        System.out.println("Randomized Quicksort:     " + Arrays.toString(arr2));

        int[] arr3 = Arrays.copyOf(data, data.length);
        fixAndOptimizeQuickSort.medianOfThreePivotQuickSort(arr3, 0, arr3.length - 1);
        System.out.println("Median-of-Three Quicksort:" + Arrays.toString(arr3));
    }
}
