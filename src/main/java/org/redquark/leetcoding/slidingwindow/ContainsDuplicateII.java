package org.redquark.leetcoding.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length == 0) {
            return false;
        }
        // Set to store only k elements
        final Set<Integer> kElements = new HashSet<>();
        // Left and right pointers
        int left = 0;
        int right = 0;
        // Process elements in the array
        while (right < nums.length) {
            if (!kElements.add(nums[right])) {
                return true;
            }
            right++;
            if (kElements.size() >= k + 1) {
                kElements.remove(nums[left]);
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final ContainsDuplicateII containsDuplicateII = new ContainsDuplicateII();

        int[] nums = new int[]{1, 2, 3, 1};
        int k = 3;
        System.out.println(containsDuplicateII.containsNearbyDuplicate(nums, k));

        nums = new int[]{1, 0, 1, 1};
        k = 1;
        System.out.println(containsDuplicateII.containsNearbyDuplicate(nums, k));

        nums = new int[]{1, 2, 3, 1, 2, 3};
        k = 2;
        System.out.println(containsDuplicateII.containsNearbyDuplicate(nums, k));
    }
}
