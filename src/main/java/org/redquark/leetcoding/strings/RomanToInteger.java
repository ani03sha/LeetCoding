package org.redquark.leetcoding.strings;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    public int romanToInt(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // Map to store mappings of a roman numeral and integers
        final Map<Character, Integer> mappings = new HashMap<>();
        mappings.put('I', 1);
        mappings.put('V', 5);
        mappings.put('X', 10);
        mappings.put('L', 50);
        mappings.put('C', 100);
        mappings.put('D', 500);
        mappings.put('M', 1000);
        // Integer value
        int intValue = 0;
        final int n = s.length();
        // Process the given string right to left
        for (int i = n - 1; i >= 0; i--) {
            int currentValue = mappings.get(s.charAt(i));
            if (i > 0) {
                final int previousValue = mappings.get(s.charAt(i - 1));
                if (previousValue < currentValue) {
                    currentValue -= previousValue;
                    i--;
                }
            }
            intValue += currentValue;
        }
        return intValue;
    }

    public static void main(String[] args) {
        final RomanToInteger romanToInteger = new RomanToInteger();

        System.out.println(romanToInteger.romanToInt("III"));
        System.out.println(romanToInteger.romanToInt("LVIII"));
        System.out.println(romanToInteger.romanToInt("MCMXCIV"));
    }
}
