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

    public static void main(String[] args) {
        final RemoveKDigits removeKDigits = new RemoveKDigits();

        System.out.println(removeKDigits.removeKDigits("1432219", 3)); // 1219
        System.out.println(removeKDigits.removeKDigits("10200", 1)); // 200
        System.out.println(removeKDigits.removeKDigits("10", 2)); // 0
        System.out.println(removeKDigits.removeKDigits("1234567890", 9)); // 0
    }
}
