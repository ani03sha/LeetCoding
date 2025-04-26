package org.redquark.leetcoding.strings;

import java.util.List;

public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        // Length of last word
        int count = 0;
        // Skip spaces from the right of string
        while (s.charAt(index) == ' ') {
            index--;
        }
        // Find length of last word
        while (index >= 0 && s.charAt(index) != ' ') {
            count++;
            index--;
        }
        return count;
    }

    public static void main(String[] args) {
        final LengthOfLastWord lengthOfLastWord = new LengthOfLastWord();

        System.out.println(lengthOfLastWord.lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord.lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(lengthOfLastWord.lengthOfLastWord("luffy is still joyboy"));
    }
}
