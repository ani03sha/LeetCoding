package org.redquark.leetcoding.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ConcatenationOfArray {

    public int[] getConcatenation(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return nums;
        }
        final int n = nums.length;
        final int[] answer = new int[2 * n];
        // Process the array
        for (int i = 0; i < n; i++) {
            answer[i] = nums[i];
            answer[i + n] = nums[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        final ConcatenationOfArray concatenationOfArray = new ConcatenationOfArray();

        int[] nums = new int[]{1, 2, 1};
        System.out.println(Arrays.toString(concatenationOfArray.getConcatenation(nums)));

        nums = new int[]{1, 3, 2, 1};
        System.out.println(Arrays.toString(concatenationOfArray.getConcatenation(nums)));
    }
}
