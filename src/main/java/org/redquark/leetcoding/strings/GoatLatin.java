package org.redquark.leetcoding.strings;

public class GoatLatin {

    public String toGoatLatin(String sentence) {
        // Special case
        if (sentence == null || sentence.isEmpty()) {
            return sentence;
        }
        // StringBuilder for the final result
        final StringBuilder result = new StringBuilder();
        // Words in the array
        final String[] words = sentence.split(" ");
        // Vowels
        final String vowels = "aeiouAEIOU";
        // Process every word
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            // Check the first character of word
            if (vowels.indexOf(word.charAt(0)) != -1) {
                result.append(word);
            } else {
                result.append(word.substring(1)).append(word.charAt(0));
            }
            result.append("ma")
                    .append("a".repeat(i + 1))
                    .append(" ");
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        final GoatLatin goatLatin = new GoatLatin();

        System.out.println(goatLatin.toGoatLatin("I speak Goat Latin"));
        System.out.println(goatLatin.toGoatLatin("The quick brown fox jumped over the lazy dog"));
    }
}
