package org.redquark.leetcoding.strings;

public class CountAndSay {

    public String countAndSay(int n) {
        // Special case
        if (n <= 0) {
            return "";
        }
        // String to keep track of result
        String result = "1";
        // Process all digits until n
        int index = 1;
        while (index < n) {
            // Create countAndSay string for the current index
            final StringBuilder sb = new StringBuilder();
            // Variable to count the number of occurrences of
            // consecutive digits
            int count = 1;
            // Process previous string
            for (int i = 1; i < result.length(); i++) {
                // Check if consecutive characters are same
                if (result.charAt(i) == result.charAt(i - 1)) {
                    count++;
                }
                // If the characters are not equal, we append the string
                else {
                    sb.append(count);
                    sb.append(result.charAt(i - 1));
                    // Reset the count to 1
                    count = 1;
                }
            }
            // For the last character
            sb.append(count);
            sb.append(result.charAt(result.length() - 1));
            result = sb.toString();
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        final CountAndSay countAndSay = new CountAndSay();

        System.out.println(countAndSay.countAndSay(4));
        System.out.println(countAndSay.countAndSay(1));
    }
}
