package org.redquark.leetcoding.strings;

import java.util.ArrayDeque;
import java.util.Deque;

public class ClearDigits {

    public String clearDigitsSuboptimalSpace(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return s;
        }
        // Stack to store the characters of the string
        final Deque<Character> stack = new ArrayDeque<>();
        // Process characters in the stack
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c) && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        // Convert stack to string
        final StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    public String clearDigitsSpaceOptimized(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return s;
        }
        // StringBuilder to store the final result
        final StringBuilder result = new StringBuilder();
        // Process characters in the string
        for (int i = 0; i < s.length(); i++) {
            // If the current character is a digit, we will delete the
            // previous character to this digit
            if (Character.isDigit(s.charAt(i))) {
                result.deleteCharAt(result.length() - 1);
            }
            // Else, add the character to the result
            else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final ClearDigits clearDigits = new ClearDigits();

        String s = "abc";
        System.out.println(clearDigits.clearDigitsSuboptimalSpace(s));
        System.out.println(clearDigits.clearDigitsSpaceOptimized(s));

        s = "cb34";
        System.out.println(clearDigits.clearDigitsSuboptimalSpace(s));
        System.out.println(clearDigits.clearDigitsSpaceOptimized(s));
    }
}
