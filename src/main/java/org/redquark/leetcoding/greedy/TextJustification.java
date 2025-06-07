package org.redquark.leetcoding.greedy;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        // List to store the justified text
        final List<String> justifiedText = new ArrayList<>();
        // Traverse through all the words
        int index = 0;
        while (index < words.length) {
            // Get the words in the current line
            final List<String> currentLineWords = getCurrentLineWords(index, words, maxWidth);
            // Jump i pointer by currentLineWords.size()
            index += currentLineWords.size();
            // Use words in the current line to create the current line
            justifiedText.add(createLine(currentLineWords, index, words, maxWidth));
        }
        return justifiedText;
    }

    private List<String> getCurrentLineWords(int index, String[] words, int maxWidth) {
        final List<String> currentLineWords = new ArrayList<>();
        int currentLineLength = 0;
        while (index < words.length && currentLineLength + words[index].length() <= maxWidth) {
            currentLineWords.add(words[index]);
            currentLineLength += words[index].length() + 1;
            index++;
        }
        return currentLineWords;
    }

    private String createLine(List<String> currentLineWords, int index, String[] words, int maxWidth) {
        // To offset no space after last word, we start baseLength with -1
        int baseLength = -1;
        // Traverse the words that need to be placed in the current line
        for (String word : currentLineWords) {
            baseLength += word.length() + 1;
        }
        // Extra spaces left
        final int extraSpaces = maxWidth - baseLength;
        // Last line or only one word in a line
        if (currentLineWords.size() == 1 || index == words.length) {
            return String.join(" ", currentLineWords) + " ".repeat(extraSpaces);
        }
        // Word count not considering last word
        final int wordCount = currentLineWords.size() - 1;
        // Spaces needed per word
        int spacesPerWord = extraSpaces / wordCount;
        // If extra space is needed
        int remainingSpaces = extraSpaces % wordCount;
        for (int i = 0; i < remainingSpaces; i++) {
            currentLineWords.set(i, currentLineWords.get(i) + " ");
        }
        for (int i = 0; i < wordCount; i++) {
            currentLineWords.set(i, currentLineWords.get(i) + " ".repeat(spacesPerWord));
        }
        return String.join(" ", currentLineWords);
    }

    public static void main(String[] args) {
        final TextJustification textJustification = new TextJustification();

        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        System.out.println(textJustification.fullJustify(words, maxWidth));

        words = new String[]{"What", "must", "be", "acknowledgment", "shall", "be"};
        maxWidth = 16;
        System.out.println(textJustification.fullJustify(words, maxWidth));

        words = new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        maxWidth = 20;
        System.out.println(textJustification.fullJustify(words, maxWidth));
    }
}
