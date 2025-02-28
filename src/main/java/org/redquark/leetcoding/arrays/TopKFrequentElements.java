package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        final int n = nums.length;
        // Buckets for storing numbers based on their frequencies
        final List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            buckets.add(new ArrayList<>());
        }
        // Map to store the frequencies of array elements
        final Map<Integer, Integer> frequencies = new HashMap<>();
        for (int num : nums) {
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }
        // Traverse the map
        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }
        // List to store the final result
        final List<Integer> result = new ArrayList<>();
        for (int i = n; i >= 0 && result.size() < k; i--) {
            if (!buckets.get(i).isEmpty()) {
                result.addAll(buckets.get(i));
            }
        }
        int[] finalResult = new int[k];
        for (int i = 0; i < k; i++) {
            finalResult[i] = result.get(i);
        }
        return finalResult;
    }

    public static void main(String[] args) {
        final TopKFrequentElements topKFrequentElements = new TopKFrequentElements();

        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequent(nums, k)));

        nums = new int[]{1};
        k = 1;
        System.out.println(Arrays.toString(topKFrequentElements.topKFrequent(nums, k)));
    }
}
