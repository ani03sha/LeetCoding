package org.redquark.extras.solutions.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhoneNumberToWords {

    private static final char[] LETTER_TO_DIGITS = new char[26];

    static {
        // Groups in increasing digits order
        final String[] groups = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        char digit = '2';
        for (String group : groups) {
            for (char c : group.toCharArray()) {
                LETTER_TO_DIGITS[c - 'a'] = digit;
            }
            digit++;
        }
    }

    public List<String> wordsFromNumber(String phoneNumber, List<String> knownWords) {
        // List to store the final result
        final List<String> result = new ArrayList<>();
        // Base cases
        if (phoneNumber == null || phoneNumber.isEmpty() || knownWords == null || knownWords.isEmpty()) {
            return result;
        }
        // Check for all known words
        for (String word : knownWords) {
            // If the length of the word is not equal to the length of phone number
            // skip this word
            if (word.length() != phoneNumber.length()) {
                continue;
            }
            // If a valid word
            boolean isValid = true;
            // Check for characters in the word
            for (int i = 0; i < word.length(); i++) {
                // Get the digit for the character
                final char digit = LETTER_TO_DIGITS[word.charAt(i) - 'a'];
                // If the digit does not match with the phone number digit at the same index
                if (digit != phoneNumber.charAt(i)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                result.add(word);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final PhoneNumberToWords phoneNumberToWords = new PhoneNumberToWords();
        final List<String> KNOWN_WORDS = Arrays.asList("careers", "linkedin", "hiring", "interview", "linkedgo");

        String num1 = "2273377";
        System.out.println("phoneNumber: " + num1);
        System.out.println("Output: " + phoneNumberToWords.wordsFromNumber(num1, KNOWN_WORDS));


        String num2 = "54653346";
        System.out.println("phoneNumber: " + num2);
        System.out.println("Output: " + phoneNumberToWords.wordsFromNumber(num2, KNOWN_WORDS));
    }
}