package org.redquark.leetcoding.arrays;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        // Special case
        if (strs == null || strs.length == 0) {
            return "";
        }
        // Shortest length among all words
        int shortestLength = Integer.MAX_VALUE;
        // Shortest word among all words
        String shortestWord = "";
        for (String str : strs) {
            if (str.length() < shortestLength) {
                shortestLength = str.length();
                shortestWord = str;
            }
        }
        // Longest Common Prefix
        final StringBuilder lcp = new StringBuilder();
        // Index to keep track of position in every word
        int index = 0;
        // Process all words only until the shortest length
        for (char c : shortestWord.toCharArray()) {
            boolean isCurrentCharacterMatching = true;
            for (String str : strs) {
                if (str.charAt(index) != c) {
                    isCurrentCharacterMatching = false;
                    break;
                }
            }
            if (isCurrentCharacterMatching) {
                lcp.append(c);
            } else {
                break;
            }
            index++;
        }
        return lcp.toString();
    }

    public static void main(String[] args) {
        final LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();

        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix.longestCommonPrefix(strs));

        strs = new String[]{"dog", "racecar", "car"};
        System.out.println(longestCommonPrefix.longestCommonPrefix(strs));
    }
}
