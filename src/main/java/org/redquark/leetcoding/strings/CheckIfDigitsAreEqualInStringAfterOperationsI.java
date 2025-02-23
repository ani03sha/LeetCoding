package org.redquark.leetcoding.strings;

public class CheckIfDigitsAreEqualInStringAfterOperationsI {

    public boolean hasSameDigits(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return false;
        }
        // StringBuilder to store reduced strings
        StringBuilder reducedString = new StringBuilder(s);
        // Keep on processing the string until there are more than 2
        // characters left in the reducedString
        while (reducedString.length() > 2) {
            // Create a new StringBuilder for current processing
            final StringBuilder currentString = new StringBuilder();
            for (int i = 0; i < reducedString.length() - 1; i++) {
                final int x = reducedString.charAt(i) - '0';
                final int y = reducedString.charAt(i + 1) - '0';
                currentString.append((x + y) % 10);
            }
            // Point reduced string to currentString
            reducedString = currentString;
        }
        return reducedString.charAt(0) == reducedString.charAt(1);
    }

    public static void main(String[] args) {
        final CheckIfDigitsAreEqualInStringAfterOperationsI checkIfDigitsAreEqualInStringAfterOperationsI
                 = new CheckIfDigitsAreEqualInStringAfterOperationsI();

        System.out.println(checkIfDigitsAreEqualInStringAfterOperationsI.hasSameDigits("3902"));
        System.out.println(checkIfDigitsAreEqualInStringAfterOperationsI.hasSameDigits("34789"));
    }
}
