package org.redquark.leetcoding.strings;

public class CustomSortStrings {

    public String customSortString(String order, String s) {
        // Frequency array for the string s
        final int[] frequencies = new int[26];
        for (char c : s.toCharArray()) {
            frequencies[c - 'a']++;
        }
        // Prepare the final output by going through string order
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < order.length(); i++) {
            // Current letter
            char c = order.charAt(i);
            // Append occurrences of this character from frequencies
            // array to the final result
            while (frequencies[c - 'a'] > 0) {
                result.append(c);
                frequencies[c - 'a']--;
            }
        }
        // Append remaining characters in s to the output
        for (char c : s.toCharArray()) {
            while (frequencies[c - 'a'] > 0) {
                result.append(c);
                frequencies[c - 'a']--;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final CustomSortStrings customSortStrings = new CustomSortStrings();

        String order = "cba";
        String s = "abcd";
        System.out.println(customSortStrings.customSortString(order, s));

        order = "bcafg";
        s = "abcd";
        System.out.println(customSortStrings.customSortString(order, s));
    }
}
