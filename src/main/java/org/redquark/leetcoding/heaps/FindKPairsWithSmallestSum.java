package org.redquark.leetcoding.heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class FindKPairsWithSmallestSum {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // List to store the final pairs
        final List<List<Integer>> pairs = new ArrayList<>();
        // Special case
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return pairs;
        }
        final int m = nums1.length;
        final int n = nums2.length;
        // Set to store visited cells
        final Set<List<Integer>> visited = new HashSet<>();
        // Min heap to store sum and corresponding pairs
        final Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // Add first pair to heap and visited cells
        visited.add(List.of(0, 0));
        minHeap.offer(new int[]{nums1[0] + nums2[0], 0, 0});
        // Process remaining pairs
        while (k > 0 && !minHeap.isEmpty()) {
            // Top of the heap
            final int[] top = minHeap.remove();
            final int i = top[1];
            final int j = top[2];
            pairs.add(List.of(nums1[i], nums2[j]));
            if (i < m - 1 && !visited.contains(List.of(i + 1, j))) {
                minHeap.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
                visited.add(List.of(i + 1, j));
            }
            if (j < n - 1 && !visited.contains(List.of(i, j + 1))) {
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
                visited.add(List.of(i, j + 1));
            }
            k--;
        }
        return pairs;
    }

    public static void main(String[] args) {
        final FindKPairsWithSmallestSum findKPairsWithSmallestSum = new FindKPairsWithSmallestSum();

        int[] nums1 = new int[]{1, 7, 11};
        int[] nums2 = new int[]{2, 4, 6};
        int k = 3;
        System.out.println(findKPairsWithSmallestSum.kSmallestPairs(nums1, nums2, k));

        nums1 = new int[]{1, 1, 2};
        nums2 = new int[]{1, 2, 3};
        k = 2;
        System.out.println(findKPairsWithSmallestSum.kSmallestPairs(nums1, nums2, k));
    }
}
