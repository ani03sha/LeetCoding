package org.redquark.leetcoding.strings;

public class CheckIfOneStringSwapCanMakeStringsEqual {

    public boolean areAlmostEqual(String s1, String s2) {
        final int n = s1.length();
        // Array to store the net frequencies of characters
        // in both the strings
        final int[] frequencies = new int[26];
        for (int i = 0; i < n; i++) {
            frequencies[s1.charAt(i) - 'a']++;
            frequencies[s2.charAt(i) - 'a']--;
        }
        // Count to store mismatched characters
        int mismatches = 0;
        // Traverse through both strings
        for (int i = 0; i < n; i++) {
            // Check if a character is not present in one of the strings
            if (frequencies[s1.charAt(i) - 'a'] != 0 || frequencies[s2.charAt(i) - 'a'] != 0) {
                return false;
            }
            if (s1.charAt(i) != s2.charAt(i)) {
                mismatches++;
            }
        }
        return mismatches == 0 || mismatches == 2;
    }

    public static void main(String[] args) {
        final CheckIfOneStringSwapCanMakeStringsEqual checkIfOneStringSwapCanMakeStringsEqual = new CheckIfOneStringSwapCanMakeStringsEqual();

        String s1 = "bank";
        String s2 = "kanb";
        System.out.println(checkIfOneStringSwapCanMakeStringsEqual.areAlmostEqual(s1, s2));

        s1 = "attack";
        s2 = "defend";
        System.out.println(checkIfOneStringSwapCanMakeStringsEqual.areAlmostEqual(s1, s2));

        s1 = "kelb";
        s2 = "kelb";
        System.out.println(checkIfOneStringSwapCanMakeStringsEqual.areAlmostEqual(s1, s2));
    }
}
