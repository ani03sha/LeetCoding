package org.redquark.leetcoding.strings;

public class FirstUniqueCharacterInAString {

    public int firstUniqChar(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return -1;
        }
        // Array to store the frequencies of characters in the string
        final int[] frequencies = new int[26];
        for (char c : s.toCharArray()) {
            frequencies[c - 'a']++;
        }
        // Find the first unique character in the string
        for (int i = 0; i < s.length(); i++) {
            if (frequencies[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final FirstUniqueCharacterInAString firstUniqueCharacterInAString = new FirstUniqueCharacterInAString();

        System.out.println(firstUniqueCharacterInAString.firstUniqChar("leetcode"));
        System.out.println(firstUniqueCharacterInAString.firstUniqChar("loveleetcode"));
        System.out.println(firstUniqueCharacterInAString.firstUniqChar("aabb"));
    }
}
