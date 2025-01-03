package org.redquark.leetcoding.arrays;

public class MajorityElement {

    public int majorityElement(int[] nums) {
        // Majority element
        int majorityElement = nums[0];
        // Count of majority element
        int count = 1;
        // Process each element in the array
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == majorityElement) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                majorityElement = nums[i];
                count = 1;
            }
        }
        return majorityElement;
    }

    public static void main(String[] args) {
        final MajorityElement majorityElement = new MajorityElement();

        int[] nums = new int[]{3, 2, 3};
        System.out.println(majorityElement.majorityElement(nums));

        nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement.majorityElement(nums));
    }
}
