package org.redquark.leetcoding.strings;

public class ReverseWordsInAStringIII {

    public String reverseWords(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return s;
        }
        // Left and right pointers
        int left = 0;
        int right = s.length() - 1;
        // Words in the string
        final String[] words = s.split(" ");
        // String Builder for final output
        final StringBuilder reversedString = new StringBuilder();
        // Process every word
        for (String word : words) {
            reversedString.append(reverseWord(word.toCharArray())).append(" ");
        }
        // Remove space after the last word
        reversedString.deleteCharAt(reversedString.length() - 1);
        return reversedString.toString();
    }

    private String reverseWord(char[] word) {
        int left = 0;
        int right = word.length - 1;
        while (left <= right) {
            final char temp = word[left];
            word[left] = word[right];
            word[right] = temp;
            left++;
            right--;
        }
        return new String(word);
    }

    public static void main(String[] args) {
        final ReverseWordsInAStringIII reverseWordsInAStringIII = new ReverseWordsInAStringIII();

        String s = "Let's take LeetCode contest";
        System.out.println(reverseWordsInAStringIII.reverseWords(s));

        s = "Mr Ding";
        System.out.println(reverseWordsInAStringIII.reverseWords(s));
    }
}
