package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {

    public String decodeString(String s) {
        // StringBuilder instance to store final output
        StringBuilder decodedString = new StringBuilder();
        // Special case
        if (s == null || s.isEmpty()) {
            return decodedString.toString();
        }
        // Stack to store count of strings
        final Deque<Integer> counts = new ArrayDeque<>();
        // Stack to store strings to be repeated
        final Deque<String> parts = new ArrayDeque<>();
        // Loop through each character of the string
        int index = 0;
        while (index < s.length()) {
            // Current character
            final char c = s.charAt(index);
            // If the current character is a digit
            if (Character.isDigit(c)) {
                int value = 0;
                while (Character.isDigit(s.charAt(index))) {
                    value = 10 * value + (s.charAt(index) - '0');
                    index++;
                }
                counts.push(value);
            }
            // If the current character is an opening bracket
            else if (c == '[') {
                // Push the current string to pars
                parts.push(decodedString.toString());
                // Reset the decoded string
                decodedString = new StringBuilder();
                index++;
            }
            // If the current character is a closing bracker
            else if (c == ']') {
                // Now, repeat the string that is at top of parts stack,
                // number of times that is a top of counts stack
                final StringBuilder temp = new StringBuilder(parts.pop());
                int value = counts.pop();
                temp.append(String.valueOf(decodedString).repeat(Math.max(0, value)));
                decodedString = temp;
                index++;
            }
            // If the current character is an alphabet
            else {
                decodedString.append(c);
                index++;
            }
        }
        return decodedString.toString();
    }

    public static void main(String[] args) {
        final DecodeString decodeString = new DecodeString();

        String s = "3[a]2[bc]";
        System.out.println(decodeString.decodeString(s));

        s = "3[a2[c]]";
        System.out.println(decodeString.decodeString(s));

        s = "2[abc]3[cd]ef";
        System.out.println(decodeString.decodeString(s));
    }
}
