package org.redquark.leetcoding.strings;

public class FindUniqueBinaryString {

    public String findDifferentBinaryString(String[] nums) {
        // StringBuilder to store the final result
        final StringBuilder result = new StringBuilder();
        // Process for all strings in the array
        for (int i = 0; i < nums.length; i++) {
            result.append(nums[i].charAt(i) == '0' ? 1 : 0);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final FindUniqueBinaryString findUniqueBinaryString = new FindUniqueBinaryString();

            String[] nums = new String[]{"01", "10"};
        System.out.println(findUniqueBinaryString.findDifferentBinaryString(nums));

        nums = new String[]{"00", "01"};
        System.out.println(findUniqueBinaryString.findDifferentBinaryString(nums));

        nums = new String[]{"111", "011", "001"};
        System.out.println(findUniqueBinaryString.findDifferentBinaryString(nums));
    }
}
