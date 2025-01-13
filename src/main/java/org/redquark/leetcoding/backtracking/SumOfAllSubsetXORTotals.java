package org.redquark.leetcoding.backtracking;

public class SumOfAllSubsetXORTotals {

    public int subsetXORSum(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Calculate sum of XORs of all subsets
        return helper(nums, 0, 0);
    }

    private int helper(int[] nums, int index, int currentXOR) {
        if (index == nums.length) {
            return currentXOR;
        }
        // Include the current element
        int include = helper(nums, index + 1, currentXOR ^ nums[index]);
        // Exclude the current element
        int exclude = helper(nums, index + 1, currentXOR);
        return include + exclude;
    }

    public static void main(String[] args) {
        final SumOfAllSubsetXORTotals sumOfAllSubsetXORTotals = new SumOfAllSubsetXORTotals();

        int[] nums = new int[]{1, 3};
        System.out.println(sumOfAllSubsetXORTotals.subsetXORSum(nums));

        nums = new int[]{5, 1, 6};
        System.out.println(sumOfAllSubsetXORTotals.subsetXORSum(nums));

        nums = new int[]{3, 4, 5, 6, 7, 8};
        System.out.println(sumOfAllSubsetXORTotals.subsetXORSum(nums));
    }
}
