package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculatorII {

    public int calculate(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // Variable to keep track of the final result
        int result = 0;
        // Variable to keep track of current number
        int currentValue = 0;
        // Stack to store the numbers
        final Deque<Integer> stack = new ArrayDeque<>();
        // Current operator
        char currentOperator = '+';
        s = s + currentOperator;
        // Process the string
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            // Handle digits
            if (Character.isDigit(c)) {
                currentValue = currentValue * 10 + (c - '0');
            }
            // Handle operators
            else {
                switch (currentOperator) {
                    case '+' -> stack.push(currentValue);
                    case '-' -> stack.push(-currentValue);
                    case '*' -> stack.push(stack.pop() * currentValue);
                    case '/' -> stack.push(stack.pop() / currentValue);
                    default -> {
                        return -1;
                    }
                }
                currentValue = 0;
                currentOperator = c;
            }
        }
        // Calculate result
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        final BasicCalculatorII basicCalculatorII = new BasicCalculatorII();

        System.out.println(basicCalculatorII.calculate("3+2*2"));
        System.out.println(basicCalculatorII.calculate(" 3/2 "));
        System.out.println(basicCalculatorII.calculate(" 3+5 / 2 "));
    }
}
