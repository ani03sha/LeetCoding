package org.redquark.leetcoding.slidingwindow;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {

    public List<Integer> findAnagrams(String s, String p) {
        // List to store all anagrams
        final List<Integer> anagrams = new ArrayList<>();
        // Special case
        if (s == null || s.isEmpty() || p == null || p.isEmpty() || p.length() > s.length()) {
            return anagrams;
        }
        // Length of p
        final int pLength = p.length();
        // Length of s
        final int sLength = s.length();
        // Calculate the frequency of all characters in p
        final int[] pFrequencies = new int[26];
        for (char c : p.toCharArray()) {
            pFrequencies[c - 'a']++;
        }
        // Left and right pointers for the sliding window
        int left = 0;
        int right = 0;
        // Array to keep track of frequencies of substrings in s
        final int[] sFrequencies = new int[26];
        while (right < pLength) {
            sFrequencies[s.charAt(right) - 'a']++;
            right++;
        }
        // Length of the string is inclusive to handle corner case where the last character
        // in the string is also a part of anagram
        while (right <= sLength) {
            // Check if two strings are anagram or not
            if (areAnagrams(sFrequencies, pFrequencies)) {
                anagrams.add(left);
            }
            // Handle corner case of last character being part of anagram
            if (right == sLength) {
                break;
            }
            sFrequencies[s.charAt(right) - 'a']++;
            // Move right pointer ahead
            right++;
            // Move left pointer ahead
            sFrequencies[s.charAt(left) - 'a']--;
            left++;
        }
        return anagrams;
    }

    private boolean areAnagrams(int[] sFrequencies, int[] pFrequencies) {
        for (int i = 0; i < 26; i++) {
            if (sFrequencies[i] != pFrequencies[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final FindAllAnagramsInAString findAllAnagramsInAString = new FindAllAnagramsInAString();

        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAllAnagramsInAString.findAnagrams(s, p));

        s = "abab";
        p = "ab";
        System.out.println(findAllAnagramsInAString.findAnagrams(s, p));
    }
}
