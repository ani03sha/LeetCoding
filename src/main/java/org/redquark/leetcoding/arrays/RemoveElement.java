package org.redquark.leetcoding.arrays;

public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Index to keep track of array elements that are not val
        int index = 0;
        // Process the array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            }
            nums[index] = nums[i];
            index++;
        }
        return index;
    }

    public static void main(String[] args) {
        final RemoveElement removeElement = new RemoveElement();

        int[] nums = new int[]{3, 2, 2, 3};
        int val = 3;
        System.out.println(removeElement.removeElement(nums, val));

        nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        val = 2;
        System.out.println(removeElement.removeElement(nums, val));
    }
}
