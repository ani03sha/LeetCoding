package org.redquark.leetcoding.bitmanipulation;

public class AddBinary {

    public String addBinary(String a, String b) {
        final int m = a.length();
        final int n = b.length();
        // Pointers to traverse both strings
        int i = m - 1;
        int j = n - 1;
        // Variable to keep track of sum
        final StringBuilder result = new StringBuilder();
        // Variable to keep track of carry
        int carry = 0;
        // Process both strings
        while (i >= 0 && j >= 0) {
            int sum = (a.charAt(i) - '0') + (b.charAt(j) - '0') + carry;
            result.append(sum % 2);
            carry = sum / 2;
            i--;
            j--;
        }
        // For remaining characters
        while (i >= 0) {
            int sum = carry + (a.charAt(i) - '0');
            result.append(sum % 2);
            carry = sum / 2;
            i--;
        }
        while (j >= 0) {
            int sum = carry + (b.charAt(j) - '0');
            result.append(sum % 2);
            carry = sum / 2;
            j--;
        }
        // If there's still carry left
        if (carry != 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        final AddBinary addBinary = new AddBinary();

        String a = "11";
        String b = "1";
        System.out.println(addBinary.addBinary(a, b));

        a = "1010";
        b = "1011";
        System.out.println(addBinary.addBinary(a, b));
    }
}
