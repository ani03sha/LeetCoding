package org.redquark.leetcoding.greedy;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public List<String> fullJustifyOne(String[] words, int maxWidth) {
        // List to store each line of the justified text
        final List<String> justifiedText = new ArrayList<>();
        // Index to keep track of words in the array
        int index = 0;
        // Total number of words
        final int n = words.length;
        // Process every word in the array
        while (index < n) {
            // Get the words in the current line
            final List<String> currentLineWords = getCurrentLineWords(index, words, maxWidth);
            // Hop index by size of the currentLineWords list
            index += currentLineWords.size();
            // Using the above words creates a line and adds it to an output list
            justifiedText.add(createLine(currentLineWords, index, n, maxWidth));
        }
        return justifiedText;
    }

    private List<String> getCurrentLineWords(int index, String[] words, int maxWidth) {
        // Length of the current line
        int currentLineLength = 0;
        // List of words in the current line
        final List<String> currentLineWords = new ArrayList<>();
        while (index < words.length && currentLineLength + words[index].length() <= maxWidth) {
            currentLineWords.add(words[index]);
            currentLineLength += words[index].length() + 1;
            index++;
        }
        return currentLineWords;
    }

    private String createLine(List<String> currentLineWords, int index, int n, int maxWidth) {
        // To offset no space after the last word, we initialize with -1;
        int baseLength = -1;
        for (String word : currentLineWords) {
            baseLength += word.length() + 1;
        }
        // The extra spaces we need
        final int extraSpaces = maxWidth - baseLength;
        // If there's only one word in the list, or it is the last word
        if (currentLineWords.size() == 1 || index == n) {
            return String.join(" ", currentLineWords) + " ".repeat(extraSpaces);
        }
        // Word count not considering last word
        final int wordCount = currentLineWords.size() - 1;
        // Spaces needed per word - equal distribution
        final int spacesPerWord = extraSpaces / wordCount;
        // Remaining spaces - which need to be packed left
        final int remainingSpaces = extraSpaces % wordCount;
        // Pack remaining spaces as a left as possible
        for (int i = 0; i < remainingSpaces; i++) {
            currentLineWords.set(i, currentLineWords.get(i) + " ");
        }
        // Distribute spacesPerWord
        for (int i = 0; i < wordCount; i++) {
            currentLineWords.set(i, currentLineWords.get(i) + " ".repeat(spacesPerWord));
        }
        return String.join(" ", currentLineWords);
    }

    public List<String> fullJustifyTwo(String[] words, int maxWidth) {
        // List to store justified text
        final List<String> justifiedText = new ArrayList<>();
        final int n = words.length;
        // Pointer representing words in the array
        int index = 0;
        // Process all words
        while (index < n) {
            // List to represent words in the current line
            final List<String> currentLineWords = new ArrayList<>();
            currentLineWords.add(words[index]);
            // Variable to represent the width of the current
            // line including spaces
            int currentLineWidth = words[index].length();
            index++;
            // Greedily fit as many words as possible
            while (index < n && currentLineWidth + 1 + words[index].length() <= maxWidth) {
                currentLineWidth += 1 + words[index].length();
                currentLineWords.add(words[index]);
                index++;
            }
            // If it's the last line or contains only one word
            if (index == n || currentLineWords.size() == 1) {
                final String leftAlignedText = String.join(" ", currentLineWords);
                // Fill remaining spaces on the right with spaces
                final String rightPadding = " ".repeat(maxWidth - leftAlignedText.length());
                justifiedText.add(leftAlignedText + rightPadding);
                continue;
            }
            // Distribute spaces evenly among words
            final StringBuilder justifiedLine = getJustifiedLine(maxWidth, currentLineWidth, currentLineWords);
            justifiedText.add(justifiedLine.toString());
        }
        return justifiedText;
    }

    private StringBuilder getJustifiedLine(int maxWidth, int currentLineWidth, List<String> currentLineWords) {
        final int totalSpaces = maxWidth - (currentLineWidth - currentLineWords.size() + 1);
        final int spaceWidth = totalSpaces / (currentLineWords.size() - 1);
        final int extraSpaces = totalSpaces % (currentLineWords.size() - 1);
        // Justified line
        final StringBuilder justifiedLine = new StringBuilder();
        for (int i = 0; i < currentLineWords.size() - 1; i++) {
            justifiedLine.append(currentLineWords.get(i));
            // Append the spaces
            justifiedLine.append(" ".repeat(spaceWidth));
            // Add extra space to the leftmost gaps, if needed
            if (i < extraSpaces) {
                justifiedLine.append(" ");
            }
        }
        // Append the last word in the line
        justifiedLine.append(currentLineWords.getLast());
        return justifiedLine;
    }

    public static void main(String[] args) {
        final TextJustification textJustification = new TextJustification();

        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        System.out.println(textJustification.fullJustifyOne(words, maxWidth));
        System.out.println(textJustification.fullJustifyTwo(words, maxWidth));

        words = new String[]{"What", "must", "be", "acknowledgment", "shall", "be"};
        maxWidth = 16;
        System.out.println(textJustification.fullJustifyOne(words, maxWidth));
        System.out.println(textJustification.fullJustifyTwo(words, maxWidth));

        words = new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        maxWidth = 20;
        System.out.println(textJustification.fullJustifyOne(words, maxWidth));
        System.out.println(textJustification.fullJustifyTwo(words, maxWidth));
    }
}
