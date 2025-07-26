package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits {

    public String removeKDigits(String num, int k) {
        // Stack to perform the computation
        final Deque<Character> stack = new ArrayDeque<>();
        // Process the number character by character
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        // If k is still not zero, remove from last
        while (k > 0) {
            stack.remove();
            k--;
        }
        // Build the result from the stack
        final StringBuilder output = new StringBuilder();
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }
        // Reverse the output to get the correct order
        output.reverse();
        // Remove leading zeros
        int index = 0;
        while (index < output.length() && output.charAt(index) == '0') {
            index++;
        }
        final String result = output.substring(index);
        return result.isEmpty() ? "0" : result;
    }

    public String removeKDigitsOptimized(String num, int k) {
        // Edge case
        if (k >= num.length()) {
            return "0";
        }
        // Convert the string into its character array, which we will
        // use as a monotonic stack as well.
        final char[] stack = num.toCharArray();
        // Variable to keep track of the stack's top
        int top = 0;
        // Process all characters in the string
        for (char c : stack) {
            // If the current character is smaller than the top of the stack,
            // we should remove the top and keep the current character instead,
            // and we have k left to remove digits
            while (top > 0 && k > 0 && c < stack[top - 1]) {
                top--;
                k--;
            }
            // Add current character to the stack;
            stack[top] = c;
            top++;
        }
        // If k is still not zero, then it makes sense for us to remove characters
        // from the right (this usually happens when the string is sorted, e.g. "12345")
        final int length = top - k;
        // Skip leading zeroes, if any
        int i = 0;
        while (i < length && stack[i] == '0') {
            i++;
        }
        return i == length ? "0" : new String(stack, i, length - i);
    }

    public static void main(String[] args) {
        final RemoveKDigits removeKDigits = new RemoveKDigits();

        System.out.println(removeKDigits.removeKDigits("1432219", 3)); // 1219
        System.out.println(removeKDigits.removeKDigitsOptimized("1432219", 3)); // 1219

        System.out.println(removeKDigits.removeKDigits("10200", 1)); // 200
        System.out.println(removeKDigits.removeKDigitsOptimized("10200", 1)); // 200

        System.out.println(removeKDigits.removeKDigits("10", 2)); // 0
        System.out.println(removeKDigits.removeKDigitsOptimized("10", 2)); // 0

        System.out.println(removeKDigits.removeKDigits("1234567890", 9)); // 0
        System.out.println(removeKDigits.removeKDigitsOptimized("1234567890", 9)); // 0
    }
}
