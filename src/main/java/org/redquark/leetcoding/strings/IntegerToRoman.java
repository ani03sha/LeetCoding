package org.redquark.leetcoding.strings;

public class IntegerToRoman {

    public String intToRoman(int num) {
        // Special cas
        if (num <= 0) {
            return "";
        }
        // Array to store roman numerals corresponding to the
        // tens, hundreds, and thousands places in num
        final String[] thousands = {"", "M", "MM", "MMM"};
        final String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        final String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        final String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[num / 1000] + hundreds[(num % 1000) / 100] + tens[(num % 100) / 10] + ones[num % 10];
    }

    public static void main(String[] args) {
        final IntegerToRoman integerToRoman = new IntegerToRoman();

        System.out.println(integerToRoman.intToRoman(3749));
        System.out.println(integerToRoman.intToRoman(58));
        System.out.println(integerToRoman.intToRoman(1994));
    }
}
