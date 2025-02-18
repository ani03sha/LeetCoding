package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class ConstructSmallestNumberFromDIString {

    public String smallestNumber(String pattern) {
        // StringBuilder to store the final result
        final StringBuilder result = new StringBuilder();
        // Stack to store the numbers
        final Deque<Integer> stack = new ArrayDeque<>();
        // Iterate through the pattern string
        for (int i = 0; i <= pattern.length(); i++) {
            // Add next number to the stack
            stack.push(i + 1);
            // If 'I' is encountered, or we reach the end, we pop elements
            // from the stack
            if (i == pattern.length() || pattern.charAt(i) == 'I') {
                while (!stack.isEmpty()) {
                    result.append(stack.pop());
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final ConstructSmallestNumberFromDIString constructSmallestNumberFromDIString = new ConstructSmallestNumberFromDIString();

        String pattern = "IIIDIDDD";
        System.out.println(constructSmallestNumberFromDIString.smallestNumber(pattern));

        pattern = "DDD";
        System.out.println(constructSmallestNumberFromDIString.smallestNumber(pattern));
    }
}
