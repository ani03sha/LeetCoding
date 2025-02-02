package org.redquark.leetcoding.arrays;

public class CheckIfArrayIsSortedAndRotated {

    public boolean check(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return true;
        }
        final int n = nums.length;
        // Total number of rotations in the array
        int rotations = 0;
        // Index at which array is rotated
        int rotationIndex = -1;
        // Traverse the array
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                rotations++;
                rotationIndex = i;
            }
        }
        // If rotations are more than one, it means the array is
        // rotated in between, i.e., a subarray is rotated
        if (rotations > 1) {
            return false;
        }
        // If the array is rotated just once then we should have sorted
        // subarray towards the right of the rotationIndex
        if (rotations == 1) {
            for (int i = rotationIndex + 1; i < n; i++) {
                if (nums[i] < nums[i - 1]) {
                    return false;
                }
            }
            // Since array is rotated just once, then it means the last
            // element must be less than or equal to the first element
            return nums[0] >= nums[nums.length - 1];
        }
        // Array is not rotated at all
        return true;
    }

    public static void main(String[] args) {
        final CheckIfArrayIsSortedAndRotated checkIfArrayIsSortedAndRotated = new CheckIfArrayIsSortedAndRotated();

        int[] nums = new int[]{3, 4, 5, 1, 2};
        System.out.println(checkIfArrayIsSortedAndRotated.check(nums));

        nums = new int[]{2, 1, 3, 4};
        System.out.println(checkIfArrayIsSortedAndRotated.check(nums));

        nums = new int[]{1, 2, 3};
        System.out.println(checkIfArrayIsSortedAndRotated.check(nums));
    }
}
