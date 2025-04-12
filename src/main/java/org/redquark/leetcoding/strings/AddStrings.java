package org.redquark.leetcoding.strings;

public class AddStrings {

    public String addStrings(String num1, String num2) {
        // StringBuilder to store final result
        final StringBuilder sumString = new StringBuilder();
        // Pointers to keep track of current characters in both strings
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        // Carry
        int carry = 0;
        // Process both strings right to left
        while (i >= 0 && j >= 0) {
            int sum = carry + num1.charAt(i) - '0' + num2.charAt(j) - '0';
            sumString.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }
        // Process remaining characters, if any
        while (i >= 0) {
            int sum = carry + num1.charAt(i) - '0';
            sumString.append(sum % 10);
            carry = sum / 10;
            i--;
        }
        while (j >= 0) {
            int sum = carry + num2.charAt(j) - '0';
            sumString.append(sum % 10);
            carry = sum / 10;
            j--;
        }
        if (carry > 0) {
            sumString.append(carry);
        }
        return sumString.reverse().toString();
    }

    public static void main(String[] args) {
        final AddStrings addStrings = new AddStrings();

        String s1 = "11";
        String s2 = "123";
        System.out.println(addStrings.addStrings(s1, s2));

        s1 = "456";
        s2 = "77";
        System.out.println(addStrings.addStrings(s1, s2));

        s1 = "0";
        s2 = "0";
        System.out.println(addStrings.addStrings(s1, s2));
    }
}
