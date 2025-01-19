package org.redquark.leetcoding.arrays;

public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        // Fast and slow pointers
        int slow = nums[0];
        int fast = nums[0];
        // Move slow pointer one hop
        slow = nums[slow];
        // Move fast pointer two hops
        fast = nums[nums[fast]];
        // Perform Floyd's cycle algorithm
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = nums[0];
        // Move both pointers one hop
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        final FindTheDuplicateNumber findTheDuplicateNumber = new FindTheDuplicateNumber();

        int[] nums = new int[]{1, 3, 4, 2, 2};
        System.out.println(findTheDuplicateNumber.findDuplicate(nums));

        nums = new int[]{3, 1, 3, 4, 2};
        System.out.println(findTheDuplicateNumber.findDuplicate(nums));

        nums = new int[]{3, 3, 3, 3, 3};
        System.out.println(findTheDuplicateNumber.findDuplicate(nums));
    }
}
