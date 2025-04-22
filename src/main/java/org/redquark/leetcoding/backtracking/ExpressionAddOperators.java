package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

    private List<String> validExpressions;
    private String number;
    private int targetValue;

    public List<String> addOperators(String num, int target) {
        this.validExpressions = new ArrayList<>();
        this.number = num;
        this.targetValue = target;
        // Perform backtracking
        backtrack(0, 0, 0, "");
        return this.validExpressions;
    }

    private void backtrack(int index, long previousOperand, long currentTotal, String expression) {
        // If we have reached the end of the given string
        if (index == this.number.length()) {
            // If the current value equals the target value
            if (currentTotal == targetValue) {
                validExpressions.add(expression);
            }
        }
        // Try all possible combinations
        for (int i = index; i < number.length(); i++) {
            // Skip numbers with leading zeroes
            if (i != index && number.charAt(index) == '0') {
                break;
            }
            // Get the next operand
            final long nextOperand = Long.parseLong(number.substring(index, i + 1));
            // If this is the first operand
            if (index == 0) {
                backtrack(i + 1, nextOperand, nextOperand, expression + nextOperand);
            } else {
                // Try adding '+'
                backtrack(i + 1, nextOperand, currentTotal + nextOperand, expression + '+' + nextOperand);
                // Try adding '-'
                backtrack(i + 1, -nextOperand, currentTotal - nextOperand, expression + '-' + nextOperand);
                // Try adding '+'
                backtrack(i + 1, previousOperand * nextOperand, currentTotal - previousOperand + previousOperand * nextOperand, expression + '*' + nextOperand);
            }
        }
    }

    public static void main(String[] args) {
        final ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();

        String num = "123";
        int target = 6;
        System.out.println(expressionAddOperators.addOperators(num, target));

        num = "232";
        target = 8;
        System.out.println(expressionAddOperators.addOperators(num, target));

        num = "3456237490";
        target = 9191;
        System.out.println(expressionAddOperators.addOperators(num, target));
    }
}
