package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllAdjacentDuplicatesInAStringII {

    public String removeDuplicates(String s, int k) {
        // Special case
        if (s == null || s.length() < k) {
            return s;
        }
        // Stack to store nodes of Pair
        final Deque<Pair> stack = new ArrayDeque<>();
        // Process the string
        for (char c : s.toCharArray()) {
            // If we have a duplicate character
            if (!stack.isEmpty() && stack.peek().c == c) {
                // Update the frequency
                final Pair top = stack.pop();
                stack.push(new Pair(top.c, top.frequency + 1));
                // If frequency reaches k
                if (stack.peek().frequency == k) {
                    stack.pop();
                }
            } else {
                stack.push(new Pair(c, 1));
            }
        }
        // Compute the result
        final StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            final Pair top = stack.pop();
            result.append(String.valueOf(top.c).repeat(top.frequency));
        }
        return result.reverse().toString();
    }

    static class Pair {
        char c;
        int frequency;

        Pair(char c, int frequency) {
            this.c = c;
            this.frequency = frequency;
        }
    }

    public static void main(String[] args) {
        final RemoveAllAdjacentDuplicatesInAStringII removeAllAdjacentDuplicatesInAStringII = new RemoveAllAdjacentDuplicatesInAStringII();

        System.out.println(removeAllAdjacentDuplicatesInAStringII.removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(removeAllAdjacentDuplicatesInAStringII.removeDuplicates("abcd", 2));
        System.out.println(removeAllAdjacentDuplicatesInAStringII.removeDuplicates("pbbcggttciiippooaais", 2));
        System.out.println(removeAllAdjacentDuplicatesInAStringII.removeDuplicates(
                "zfggfdystfdxfdgdfhgdgdtasdjfnbfsrdtylkjfhrashfgfdjfhffrdkueryknbvcxcvbjuytrewerrrssddsadasdasfasssssssdfss", 3));
    }
}
