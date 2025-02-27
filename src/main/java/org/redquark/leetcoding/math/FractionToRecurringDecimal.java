package org.redquark.leetcoding.math;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        // Special case
        if (numerator == 0) {
            return "0";
        }
        // StringBuilder to store the final result
        final StringBuilder result = new StringBuilder();
        // Check for positive or negative values
        if ((numerator > 0) ^ (denominator > 0)) {
            result.append("-");
        }
        long effectiveNumerator = Math.abs((long) numerator);
        long effectiveDenominator = Math.abs((long) denominator);
        // Calculate the integral part
        result.append(effectiveNumerator / effectiveDenominator);
        // Now, find the fractional part
        effectiveNumerator %= effectiveDenominator;
        // If the denominator divides numerator completely,
        // we don't have to calculate the fraction part
        if (effectiveNumerator == 0) {
            return result.toString();
        }
        // If the division does have a fraction, we solve it further
        result.append(".");
        // Map to store numerator and its length
        final Map<Long, Integer> mappings = new HashMap<>();
        while (effectiveNumerator != 0) {
            effectiveNumerator *= 10;
            result.append(effectiveNumerator / effectiveDenominator);
            effectiveNumerator %= effectiveDenominator;
            if (mappings.containsKey(effectiveNumerator)) {
                // Get the index
                final int index = mappings.get(effectiveNumerator);
                // Insert opening parenthesis at the index
                result.insert(index, "(");
                result.append(")");
                break;
            } else {
                mappings.put(effectiveNumerator, result.length());
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final FractionToRecurringDecimal fractionToRecurringDecimal = new FractionToRecurringDecimal();

        int numerator = 1;
        int denominator = 2;
        System.out.println(fractionToRecurringDecimal.fractionToDecimal(numerator, denominator));

        numerator = 2;
        denominator = 1;
        System.out.println(fractionToRecurringDecimal.fractionToDecimal(numerator, denominator));

        numerator = 4;
        denominator = 333;
        System.out.println(fractionToRecurringDecimal.fractionToDecimal(numerator, denominator));
    }
}
