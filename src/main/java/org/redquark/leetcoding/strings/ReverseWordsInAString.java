package org.redquark.leetcoding.strings;

import java.util.Arrays;

public class ReverseWordsInAString {

    public String reverseWords(String s) {
        // Special cas;
        if (s == null || s.isEmpty()) {
            return s;
        }
        // First of all clean up the string by removing spaces
        char[] words = cleanSpaces(s.toCharArray(), s.length());
        // Reverse the entire string
        reverse(words, 0, words.length - 1);
        // Reverse each word again
        reverseEachWord(words, words.length);
        return new String(words);
    }

    private char[] cleanSpaces(char[] words, int n) {
        // Pointer to represent clean string
        int i = 0;
        // Pointer to represent actual word
        int j = 0;
        // Process the string
        while (i < n && j < n) {
            // Skip leading spaces
            while (j < n && words[j] == ' ') {
                j++;
            }
            // Add non-space characters
            while (j < n && words[j] != ' ') {
                words[i] = words[j];
                i++;
                j++;
            }
            // Remove trailing spaces
            while (j < n && words[j] == ' ') {
                j++;
            }
            // If the current word is not the last word, add a space
            if (j < n) {
                words[i] = ' ';
                i++;
            }
        }
        return Arrays.copyOf(words, i);
    }

    private void reverse(char[] words, int i, int j) {
        while (i < j) {
            char temp = words[i];
            words[i] = words[j];
            words[j] = temp;
            i++;
            j--;
        }
    }

    private void reverseEachWord(char[] words, int n) {
        // Start and end index of each word
        int start = 0;
        int end = 0;
        while (end < n) {
            while (end < n && words[end] != ' ') {
                end++;
            }
            reverse(words, start, end - 1);
            end++;
            start = end;
        }
    }

    public static void main(String[] args) {
        final ReverseWordsInAString reverseWordsInAString = new ReverseWordsInAString();

        System.out.println(reverseWordsInAString.reverseWords("the sky is blue"));
        System.out.println(reverseWordsInAString.reverseWords("  hello world  "));
        System.out.println(reverseWordsInAString.reverseWords("a good   example"));
    }
}
