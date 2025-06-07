package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class TopKFrequentElements {

    public int[] topKFrequentWithHeaps(int[] nums, int k) {
        // Special case
        if (k == nums.length) {
            return nums;
        }
        // Find the frequency of every element in the array
        final Map<Integer, Integer> frequencies = new HashMap<>();
        for (int num : nums) {
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }
        // Min heap to store elements based on their frequencies
        final Queue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(frequencies::get));
        // Add all the different elements in frequencies to the heap
        for (int num : frequencies.keySet()) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        // Construct the output array
        int[] topK = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            topK[i] = minHeap.remove();
        }
        return topK;
    }

    // Array to store unique elements in the nums
    private int[] uniques;
    // Map to store frequencies of the elements
    private final Map<Integer, Integer> frequencies = new HashMap<>();
    // Instance of Random to select pivot
    private final Random random = new Random();

    public int[] topKFrequentQuickSelect(int[] nums, int k) {
        for (int num : nums) {
            this.frequencies.put(num, this.frequencies.getOrDefault(num, 0) + 1);
        }
        // Unique elements
        final int n = this.frequencies.size();
        this.uniques = new int[n];
        int i = 0;
        for (int num : this.frequencies.keySet()) {
            this.uniques[i] = num;
            i++;
        }
        // The k-th top frequent element is (n - k)th less frequent element
        quickSelect(0, n - 1, n - k);
        return Arrays.copyOfRange(this.uniques, n - k, n);
    }

    private void quickSelect(int left, int right, int k) {
        // Base case
        if (left >= right) {
            return;
        }
        // Get pivot index
        int pivotIndex = left + random.nextInt(right - left);
        // Find the position of this index in the sorted list
        pivotIndex = partition(left, right, pivotIndex);
        // If the pivot is at correct position
        if (pivotIndex == k) {
            return;
        } else if (k < pivotIndex) {
            quickSelect(left, pivotIndex - 1, k);
        } else {
            quickSelect(pivotIndex + 1, right, k);
        }
    }

    private int partition(int left, int right, int pivotIndex) {
        // Get the frequency against pivot index
        final int pivotFrequency = this.frequencies.get(this.uniques[pivotIndex]);
        // Move the pivot index to the end
        swap(pivotIndex, right);
        int storeIndex = left;
        // Move all less frequent elements to the left
        for (int i = left; i < right; i++) {
            if (this.frequencies.get(this.uniques[i]) < pivotFrequency) {
                swap(storeIndex, i);
                storeIndex++;
            }
        }
        // Move pivot to its final place
        swap(storeIndex, right);
        return storeIndex;
    }

    private void swap(int i, int j) {
        final int temp = this.uniques[i];
        this.uniques[i] = this.uniques[j];
        this.uniques[j] = temp;
    }

    public int[] topKFrequentBucketSort(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }
        final int n = nums.length;
        // Count frequencies of elements in the array
        final Map<Integer, Integer> frequencies = new HashMap<>();
        for (int num : nums) {
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }
        // The frequency of any element cannot be greater than n, so we
        // will keep a list of all elements that have same frequencies
        // together (bucket sort)
        final List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            buckets.add(new ArrayList<>());
        }
        // Fill the buckets
        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }
        // Array to store top k elements
        final int[] topK = new int[k];
        // Index of topK array
        int index = 0;
        for (int i = n; i >= 0 && index < k; i--) {
            for (int num : buckets.get(i)) {
                topK[index] = num;
                index++;
            }
        }
        return topK;
    }

    public static void main(String[] args) {
        final TopKFrequentElements topKFrequentElements = new TopKFrequentElements();

        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentWithHeaps(nums, k)));
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentQuickSelect(nums, k)));
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentBucketSort(nums, k)));

        nums = new int[]{1};
        k = 1;
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentWithHeaps(nums, k)));
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentQuickSelect(nums, k)));
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequentBucketSort(nums, k)));
    }
}
