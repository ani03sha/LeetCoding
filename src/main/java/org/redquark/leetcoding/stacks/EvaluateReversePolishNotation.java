package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        // Special case
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        // Stack to store the operands
        final Deque<Integer> operands = new ArrayDeque<>();
        // Process tokens
        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    int x = operands.pop();
                    int y = operands.pop();
                    operands.push(x + y);
                }
                case "-" -> {
                    int x = operands.pop();
                    int y = operands.pop();
                    operands.push(y - x);
                }
                case "*" -> {
                    int x = operands.pop();
                    int y = operands.pop();
                    operands.push(x * y);
                }
                case "/" -> {
                    int x = operands.pop();
                    int y = operands.pop();
                    operands.push(y / x);
                }
                default -> operands.push(Integer.parseInt(token));
            }
        }
        return operands.pop();
    }

    public static void main(String[] args) {
        final EvaluateReversePolishNotation evaluateReversePolishNotation = new EvaluateReversePolishNotation();

        String[] tokens = new String[]{"2", "1", "+", "3", "*"};
        System.out.println(evaluateReversePolishNotation.evalRPN(tokens));

        tokens = new String[]{"4", "13", "5", "/", "+"};
        System.out.println(evaluateReversePolishNotation.evalRPN(tokens));

        tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evaluateReversePolishNotation.evalRPN(tokens));
    }
}
