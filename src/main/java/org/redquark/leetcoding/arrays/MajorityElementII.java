package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
        // List to store the majority elements
        final List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        final int n = nums.length;
        // There can only be at most two majority elements as
        // (more thab n/3 + more than n/3 == more than 2n/3)
        int x = nums[0];
        int y = 0;
        int countX = 1;
        int countY = 0;
        // Process elements in the array
        for (int i = 1; i < n; i++) {
            if (nums[i] == x) {
                countX++;
            } else if (nums[i] == y) {
                countY++;
            } else if (countX == 0) {
                x = nums[i];
                countX = 1;
            } else if (countY == 0) {
                y = nums[i];
                countY = 1;
            } else {
                countX--;
                countY--;
            }
        }
        // At this point, we have identified our candidates - x and y.
        // Let's see if they occur more than n / 3 times or not
        countX = 0;
        countY = 0;
        for (int num : nums) {
            if (num == x) {
                countX++;
            } else if (num == y) {
                countY++;
            }
        }
        if (countX > n / 3) {
            result.add(x);
        }
        if (countY > n / 3) {
            result.add(y);
        }
        return result;
    }

    public static void main(String[] args) {
        final MajorityElementII majorityElementII = new MajorityElementII();

        int[] nums = new int[]{3, 2, 3};
        System.out.println(majorityElementII.majorityElement(nums));

        nums = new int[]{1};
        System.out.println(majorityElementII.majorityElement(nums));

        nums = new int[]{1, 2};
        System.out.println(majorityElementII.majorityElement(nums));
    }
}
