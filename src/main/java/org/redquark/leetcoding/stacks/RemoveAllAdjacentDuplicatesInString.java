package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllAdjacentDuplicatesInString {

    public String removeDuplicates(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return s;
        }
        // Stack to store the characters
        final Deque<Character> stack = new ArrayDeque<>();
        // Process the string
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        // StringBuilder to store the final result
        final StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        final RemoveAllAdjacentDuplicatesInString removeAllAdjacentDuplicatesInString = new RemoveAllAdjacentDuplicatesInString();

        System.out.println(removeAllAdjacentDuplicatesInString.removeDuplicates("abbaca"));
        System.out.println(removeAllAdjacentDuplicatesInString.removeDuplicates("azxxzy"));
    }
}
