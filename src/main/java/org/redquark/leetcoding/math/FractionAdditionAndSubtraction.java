package org.redquark.leetcoding.math;

public class FractionAdditionAndSubtraction {

    public String fractionAddition(String expression) {
        // Special case
        if (expression == null || expression.isEmpty()) {
            return expression;
        }
        // Numerator and denominator of the entire expression
        int numerator = 0;
        int denominator = 1;
        // Index to traverse the expression string
        int index = 0;
        // Process the string expression
        while (index < expression.length()) {
            // Current numerator and denominator
            int currentNumerator = 0;
            int currentDenominator = 0;
            // Flag for negative number
            boolean isNegative = false;
            if (expression.charAt(index) == '+' || expression.charAt(index) == '-') {
                if (expression.charAt(index) == '-') {
                    isNegative = true;
                }
                index++;
            }
            // Construct numerator
            while (Character.isDigit(expression.charAt(index))) {
                int value = expression.charAt(index) - '0';
                currentNumerator = currentNumerator * 10 + value;
                index++;
            }
            if (isNegative) {
                currentNumerator *= -1;
            }
            // Skip divisor
            index++;
            // Construct denominator
            while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                int value = expression.charAt(index) - '0';
                currentDenominator = currentDenominator * 10 + value;
                index++;
            }
            // Add fractions together using common denominator
            numerator = numerator * currentDenominator + denominator * currentNumerator;
            denominator = denominator * currentDenominator;
        }
        // Find the GCD of numerator and denominator
        final int gcd = getGCD(numerator, denominator);
        // Reduced numerator and denominator to the lowest form
        numerator /= gcd;
        denominator /= gcd;
        return numerator + "/" + denominator;
    }

    private int getGCD(int a, int b) {
        if (a == 0) {
            return b;
        }
        return getGCD(b % a, a);
    }

    public static void main(String[] args) {
        final FractionAdditionAndSubtraction fractionAdditionAndSubtraction = new FractionAdditionAndSubtraction();

        System.out.println(fractionAdditionAndSubtraction.fractionAddition("-1/2+1/2"));
        System.out.println(fractionAdditionAndSubtraction.fractionAddition("-1/2+1/2+1/3"));
        System.out.println(fractionAdditionAndSubtraction.fractionAddition("1/3-1/2"));
    }
}
