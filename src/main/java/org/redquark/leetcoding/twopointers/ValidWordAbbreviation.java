package org.redquark.leetcoding.twopointers;

public class ValidWordAbbreviation {

    public boolean validWordAbbreviation(String word, String abbr) {
        // Special case
        if (word == null || word.isEmpty() || abbr == null || abbr.isEmpty()) {
            return false;
        }
        final int m = word.length();
        final int n = abbr.length();
        // Pointers to keep track of current index in both
        // word and abbr strings
        int wordIndex = 0;
        int abbrIndex = 0;
        // Process both strings simultaneously
        while (wordIndex < m && abbrIndex < n) {
            // If the current character in abbr string is not a digit
            if (!Character.isDigit(abbr.charAt(abbrIndex))) {
                if (word.charAt(wordIndex) != abbr.charAt(abbrIndex)) {
                    return false;
                }
                wordIndex++;
                abbrIndex++;
            }
            // If the current character in abbr string is a digit
            else {
                // Ignore leading zeroes
                if (abbr.charAt(abbrIndex) == '0') {
                    return false;
                }
                int value = 0;
                while (abbrIndex < n && Character.isDigit(abbr.charAt(abbrIndex))) {
                    value = value * 10 + abbr.charAt(abbrIndex) - '0';
                    abbrIndex++;
                }
                wordIndex += value;
            }
        }
        // At this point, both indices should reach the end of their respective strings
        return wordIndex == m && abbrIndex == n;
    }

    public static void main(String[] args) {
        final ValidWordAbbreviation validWordAbbreviation = new ValidWordAbbreviation();

        String word = "internationalization";
        String abbr = "i12iz4n";
        System.out.println(validWordAbbreviation.validWordAbbreviation(word, abbr));

        word = "apple";
        abbr = "a2e";
        System.out.println(validWordAbbreviation.validWordAbbreviation(word, abbr));

        word = "abbde";
        abbr = "a1b01e";
        System.out.println(validWordAbbreviation.validWordAbbreviation(word, abbr));
    }
}
