package org.redquark.leetcoding.strings;

public class StringToIntegerAtoi {

    public int myAtoi(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        final int n = s.length();
        // Variable to keep track of numerical value
        double numericalValue = 0;
        // Index to track for the current character
        int index = 0;
        // Remove all leading spaces
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }
        // Check of the sign
        boolean isNegative = false;
        if (index < n && (s.charAt(index) == '-' || s.charAt(index) == '+')) {
            isNegative = s.charAt(index) == '-';
            index++;
        }
        while (index < n && s.charAt(index) - '0' >= 0 && s.charAt(index) - '0' <= 9) {
            numericalValue = numericalValue * 10 + s.charAt(index) - '0';
            index++;
        }
        numericalValue = isNegative ? -numericalValue : numericalValue;
        // Check of index out of bounds
        if (numericalValue > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (numericalValue < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) numericalValue;
    }

    public static void main(String[] args) {
        final StringToIntegerAtoi stringToIntegerAtoi = new StringToIntegerAtoi();

        System.out.println(stringToIntegerAtoi.myAtoi("42"));
        System.out.println(stringToIntegerAtoi.myAtoi(" -042"));
        System.out.println(stringToIntegerAtoi.myAtoi("1337c0d3"));
        System.out.println(stringToIntegerAtoi.myAtoi("0-1"));
        System.out.println(stringToIntegerAtoi.myAtoi("words and 987"));
    }
}
