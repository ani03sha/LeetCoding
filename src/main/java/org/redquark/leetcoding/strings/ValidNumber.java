package org.redquark.leetcoding.strings;

public class ValidNumber {

    public boolean isNumber(String s) {
        // Remove any trailing whitespaces
        s = s.trim();
        // Flags for dot, e and number
        boolean hasDotSeen = false;
        boolean hasESeen = false;
        boolean hasNumberSeen = false;
        // Process the string
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            // If the current character is a valid digit
            if (c >= '0' && c <= '9') {
                hasNumberSeen = true;
            }
            // If the current character is a dot
            else if (c == '.') {
                // If we have already seen dot or e
                if (hasESeen || hasDotSeen) {
                    return false;
                }
                hasDotSeen = true;
            }
            // If the current character is e/E
            else if (c == 'e' || c == 'E') {
                // If e is before any number of we have seen it before
                if (!hasNumberSeen || hasESeen) {
                    return false;
                }
                hasNumberSeen = false;
                hasESeen = true;
            }
            // If the current character is '+' or '-'
            else if (c == '+' || c == '-') {
                if (i != 0 && (s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E')) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return hasNumberSeen;
    }

    public static void main(String[] args) {
        final ValidNumber validNumber = new ValidNumber();

        System.out.println(validNumber.isNumber("0"));
        System.out.println(validNumber.isNumber("e"));
        System.out.println(validNumber.isNumber("."));
        System.out.println(validNumber.isNumber("3E+7"));
        System.out.println(validNumber.isNumber("0e"));
    }
}
