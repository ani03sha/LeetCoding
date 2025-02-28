package org.redquark.leetcoding.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class SubarraysWithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    private int atMostK(int[] nums, int k) {
        // Frequency map
        final Map<Integer, Integer> frequencies = new HashMap<>();
        // Left and right pointers of the sliding window
        int left = 0;
        int right = 0;
        // Total count of good subarrays
        int totalCount = 0;
        // Process elements in the array
        while (right < nums.length) {
            frequencies.put(nums[right], frequencies.getOrDefault(nums[right], 0) + 1);
            // If the size of frequencies exceeds k, we start shrinking it from the left
            while (frequencies.size() > k) {
                frequencies.put(nums[left], frequencies.get(nums[left]) - 1);
                if (frequencies.get(nums[left]) == 0) {
                    frequencies.remove(nums[left]);
                }
                left++;
            }
            totalCount += right - left + 1;
            right++;
        }
        return totalCount;
    }

    public static void main(String[] args) {
        final SubarraysWithKDifferentIntegers subarraysWithKDifferentIntegers = new SubarraysWithKDifferentIntegers();

        int[] nums = new int[]{1, 2, 1, 2, 3};
        int k = 2;
        System.out.println(subarraysWithKDifferentIntegers.subarraysWithKDistinct(nums, k));

        nums = new int[]{1, 2, 1, 3, 4};
        k = 3;
        System.out.println(subarraysWithKDifferentIntegers.subarraysWithKDistinct(nums, k));
    }
}
