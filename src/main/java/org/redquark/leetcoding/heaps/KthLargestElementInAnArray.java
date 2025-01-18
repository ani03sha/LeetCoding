package org.redquark.leetcoding.heaps;

public class KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length < k) {
            throw new IllegalArgumentException("Invalid inputs!");
        }
        // Perform quick select
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int left, int right, int kSmallest) {
        // Base case - when left and right pointers meet, we have
        // found the kth smallest element
        if (left == right) {
            return nums[left];
        }
        // Find the pivot index
        final int pivot = nums[left + (right - left) / 2];
        // Pointers for the partitioning step
        int start = left - 1;
        int end = right + 1;
        // Process elements
        while (start < end) {
            // Move start right past any element that is smaller than the pivot
            do {
                start++;
            } while (nums[start] < pivot);
            // Move end left past any element that is greater than the pivot
            do {
                end--;
            } while (nums[end] > pivot);
            // Swap elements at start and end if they are out of order
            if (start < end) {
                final int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
            }
        }
        // After partitioning, pivot should be at index end
        // If we found kth smallest element, return it
        if (end >= kSmallest) {
            return quickSelect(nums, left, end, kSmallest);
        }
        // Otherwise, continue the search in the right partition
        return quickSelect(nums, end + 1, right, kSmallest);
    }

    public static void main(String[] args) {
        final KthLargestElementInAnArray kthLargestElementInAnArray = new KthLargestElementInAnArray();

        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(kthLargestElementInAnArray.findKthLargest(nums, k));

        nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        k = 4;
        System.out.println(kthLargestElementInAnArray.findKthLargest(nums, k));
    }
}
