package org.redquark.leetcoding.arrays;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class KthLargestElementInAnArray {

    public int findKthLargestWithHeap(int[] nums, int k) {
        // Min heap
        final Queue<Integer> minHeap = new PriorityQueue<>();
        // Traverse through the array
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        return minHeap.remove();
    }

    public int findKthLargest(int[] nums, int k) {
        final int n = nums.length;
        k = n - k;  // Convert to kth smallest
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == k) {
                return nums[pivotIndex];
            } else if (pivotIndex < k) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        return -1;  // This line won't be reached
    }

    private int partition(int[] nums, int left, int right) {
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        int pivot = nums[pivotIndex];
        // Move pivot to the end
        swap(nums, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        // Move pivot to its final place
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        final KthLargestElementInAnArray kthLargestElementInAnArray = new KthLargestElementInAnArray();

        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println("Using heap:");
        System.out.println(kthLargestElementInAnArray.findKthLargestWithHeap(nums, k));
        System.out.println("Using Quick Select");
        System.out.println(kthLargestElementInAnArray.findKthLargest(nums, k));
        System.out.println();

        nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        k = 4;
        System.out.println("Using heap:");
        System.out.println(kthLargestElementInAnArray.findKthLargestWithHeap(nums, k));
        System.out.println("Using Quick Select");
        System.out.println(kthLargestElementInAnArray.findKthLargest(nums, k));
    }
}
