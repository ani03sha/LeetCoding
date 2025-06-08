package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
        final int n = nums.length;
        // There can only be two elements that can occur more than  n/3 times
        // Let them be x and y and their respective counts be xCount and yCount
        int x = nums[0];
        int y = 0;
        int xCount = 1;
        int yCount = 0;
        // Process all elements in the array
        for (int i = 1; i < n; i++) {
            if (nums[i] == x) {
                xCount++;
            } else if (nums[i] == y) {
                yCount++;
            } else if (xCount == 0) {
                x = nums[i];
                xCount = 1;
            } else if (yCount == 0) {
                y = nums[i];
                yCount = 1;
            } else {
                xCount--;
                yCount--;
            }
        }
        // Verify if x and y occur more than n/3 times
        xCount = 0;
        yCount = 0;
        for (int num : nums) {
            if (num == x) {
                xCount++;
            } else if (num == y) {
                yCount++;
            }
        }
        // List to store output
        final List<Integer> result = new ArrayList<>();
        if (xCount > n / 3) {
            result.add(x);
        }
        if (yCount > n / 3) {
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
