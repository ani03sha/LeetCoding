package org.redquark.leetcoding.strings;

public class IntegerToEnglishWords {

    private final String[] oneToNineteen = {
            "",
            "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private final String[] tens = {
            "",
            "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        // Recursively find the solution
        return helper(num).trim();
    }

    private String helper(int num) {
        // StringBuilder to store the result
        final StringBuilder result = new StringBuilder();
        if (num >= 1_000_000_000) {
            result.append(helper(num / 1_000_000_000)).append(" Billion ").append(helper(num % 1_000_000_000));
        } else if (num >= 1_000_000) {
            result.append(helper(num / 1_000_000)).append(" Million ").append(helper(num % 1_000_000));
        } else if (num >= 1_000) {
            result.append(helper(num / 1_000)).append(" Thousand ").append(helper(num % 1_000));
        } else if (num >= 100) {
            result.append(helper(num / 100)).append(" Hundred ").append(helper(num % 100));
        } else if (num >= 20) {
            result.append(tens[num / 10]).append(" ").append(helper(num % 10));
        } else {
            result.append(oneToNineteen[num]);
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        final IntegerToEnglishWords integerToEnglishWords = new IntegerToEnglishWords();

        System.out.println(integerToEnglishWords.numberToWords(123));
        System.out.println(integerToEnglishWords.numberToWords(12345));
        System.out.println(integerToEnglishWords.numberToWords(1234567));
    }
}
