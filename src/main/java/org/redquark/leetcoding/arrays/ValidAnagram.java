package org.redquark.leetcoding.arrays;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        // Special case
        if (s.length() != t.length()) {
            return false;
        }
        // Array to store the frequencies of characters in strings
        final int[] frequencies = new int[26];
        // Process the strings
        for (int i = 0; i < s.length(); i++) {
            frequencies[s.charAt(i) - 'a']++;
            frequencies[t.charAt(i) - 'a']--;
        }
        // Check the frequencies array to see non-zero values
        for (int frequency : frequencies) {
            if (frequency != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final ValidAnagram validAnagram = new ValidAnagram();

        String s = "anagram";
        String t = "nagaram";
        System.out.println(validAnagram.isAnagram(s, t));

        s = "rat";
        t = "car";
        System.out.println(validAnagram.isAnagram(s, t));
    }
}
