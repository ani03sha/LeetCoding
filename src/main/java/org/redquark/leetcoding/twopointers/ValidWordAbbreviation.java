package org.redquark.leetcoding.twopointers;

public class ValidWordAbbreviation {

    public boolean validWordAbbreviation(String word, String abbr) {
        if (word == null || abbr == null || word.isEmpty() || abbr.isEmpty()) {
            return false;
        }

        int wordIndex = 0; // Pointer for the word
        int abbrIndex = 0; // Pointer for the abbreviation

        while (wordIndex < word.length() && abbrIndex < abbr.length()) {
            char abbrChar = abbr.charAt(abbrIndex);

            // Case 1: If the current abbreviation character is a letter
            if (!Character.isDigit(abbrChar)) {
                if (word.charAt(wordIndex) != abbrChar) {
                    return false; // Mismatch in characters
                }
                wordIndex++;
                abbrIndex++;
            }
            // Case 2: If the current abbreviation character is a digit
            else {
                // Leading zero is invalid
                if (abbrChar == '0') {
                    return false;
                }
                int num = 0;
                // Build the full number (can be multiple digits)
                while (abbrIndex < abbr.length() && Character.isDigit(abbr.charAt(abbrIndex))) {
                    num = num * 10 + (abbr.charAt(abbrIndex) - '0');
                    abbrIndex++;
                }

                wordIndex += num; // Skip `num` characters in word
            }
        }
        // Both strings should be completely processed
        return wordIndex == word.length() && abbrIndex == abbr.length();
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
