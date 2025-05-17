package org.redquark.leetcoding.math;

public class GreatestCommonDivisorOfStrings {

    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // Calculate GCD of lengths of two strings
        final int gcd = evaluateGCD(str1.length(), str2.length());
        return str2.substring(0, gcd);
    }

    private int evaluateGCD(int p, int q) {
        return q == 0 ? p : evaluateGCD(q, p % q);
    }

    public static void main(String[] args) {
        final GreatestCommonDivisorOfStrings greatestCommonDivisorOfStrings = new GreatestCommonDivisorOfStrings();

        System.out.println(greatestCommonDivisorOfStrings.gcdOfStrings("ABCABC", "ABC"));
        System.out.println(greatestCommonDivisorOfStrings.gcdOfStrings("ABABAB", "ABAB"));
        System.out.println(greatestCommonDivisorOfStrings.gcdOfStrings("LEET", "CODE"));
    }
}
