package org.redquark.leetcoding.arrays;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxSumOfAPairWithEqualSumOfDigits {

    public int maximumSumSuboptimal(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // Map to store digit sum and max heap of elements with that digit sum
        final Map<Integer, Queue<Integer>> sumDigitMap = new HashMap<>();
        // Process elements in the array
        for (int num : nums) {
            final int digitSum = getSumOfDigits(num);
            sumDigitMap.putIfAbsent(digitSum, new PriorityQueue<>(Collections.reverseOrder()));
            sumDigitMap.get(digitSum).add(num);
        }
        // Maximum sum
        int maxSum = Integer.MIN_VALUE;
        for (Queue<Integer> elements : sumDigitMap.values()) {
            // We only need to get the top two largest elements from each list
            if (elements.size() >= 2) {
                int sum = elements.remove();
                sum += elements.remove();
                maxSum = Math.max(maxSum, sum);
            }

        }
        return maxSum == Integer.MIN_VALUE ? -1 : maxSum;
    }

    public int maximumSumOptimal(int[] nums) {
        // Map to store digit sum and sum of numbers
        final Map<Integer, Integer> mappings = new HashMap<>();
        int maxSum = -1;
        // Process elements in the array
        for (int num : nums) {
            final int digitSum = getSumOfDigits(num);
            if (mappings.containsKey(digitSum)) {
                maxSum = Math.max(maxSum, mappings.get(digitSum) + num);
            }
            mappings.put(digitSum, mappings.getOrDefault(digitSum, 0) + num);
        }
        return maxSum;
    }

    private int getSumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        final MaxSumOfAPairWithEqualSumOfDigits maxSumOfAPairWithEqualSumOfDigits = new MaxSumOfAPairWithEqualSumOfDigits();

        int[] nums = new int[]{18, 43, 36, 13, 7};
        System.out.println(maxSumOfAPairWithEqualSumOfDigits.maximumSumSuboptimal(nums));
        System.out.println(maxSumOfAPairWithEqualSumOfDigits.maximumSumOptimal(nums));

        nums = new int[]{10, 12, 19, 14};
        System.out.println(maxSumOfAPairWithEqualSumOfDigits.maximumSumSuboptimal(nums));
        System.out.println(maxSumOfAPairWithEqualSumOfDigits.maximumSumOptimal(nums));
    }
}
