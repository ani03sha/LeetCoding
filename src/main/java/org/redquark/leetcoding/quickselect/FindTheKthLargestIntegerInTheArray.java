package org.redquark.leetcoding.quickselect;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class FindTheKthLargestIntegerInTheArray {

    public String kthLargestNumberWithHeap(String[] nums, int k) {
        // Heap to store all numbers sorted by their integer values
        final Queue<String> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.length() == b.length()) {
                return a.compareTo(b);
            }
            return Integer.compare(a.length(), b.length());
        });
        // Process all numbers
        for (String num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        return minHeap.remove();
    }

    public String kthLargestNumberWithQuickSelect(String[] nums, int k) {
        final int n = nums.length;
        k = n - k;
        // Left and right pointers for the quick select
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            // Get the pivot index
            final int pivotIndex = partition(nums, left, right);
            if (pivotIndex == k) {
                return nums[pivotIndex];
            } else if (pivotIndex < k) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        return null; // Should never reach here
    }

    private int partition(String[] nums, int left, int right) {
        // Get a random pivot index between left and right
        final int pivotIndex = left + new Random().nextInt(right - left + 1);
        final String pivot = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (compare(nums[i], pivot) < 0) {
                swap(nums, i, storeIndex);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(String[] nums, int i, int j) {
        final String temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int compare(String a, String b) {
        if (a.length() == b.length()) {
            return a.compareTo(b);
        }
        return Integer.compare(a.length(), b.length());
    }

    public static void main(String[] args) {
        final FindTheKthLargestIntegerInTheArray findTheKthLargestIntegerInTheArray = new FindTheKthLargestIntegerInTheArray();

        String[] nums = {"3", "6", "7", "10"};
        int k = 4;
        System.out.println("Using heap:");
        System.out.println(findTheKthLargestIntegerInTheArray.kthLargestNumberWithHeap(nums, k));
        System.out.println("Using Quick Select:");
        System.out.println(findTheKthLargestIntegerInTheArray.kthLargestNumberWithQuickSelect(nums, k));

        nums = new String[]{"2", "21", "12", "1"};
        k = 3;
        System.out.println("Using heap:");
        System.out.println(findTheKthLargestIntegerInTheArray.kthLargestNumberWithHeap(nums, k));
        System.out.println("Using Quick Select:");
        System.out.println(findTheKthLargestIntegerInTheArray.kthLargestNumberWithQuickSelect(nums, k));
    }
}
