package org.redquark.leetcoding.greedy;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
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
        System.out.println(textJustification.fullJustify(words, maxWidth));

        words = new String[]{"What", "must", "be", "acknowledgment", "shall", "be"};
        maxWidth = 16;
        System.out.println(textJustification.fullJustify(words, maxWidth));

        words = new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        maxWidth = 20;
        System.out.println(textJustification.fullJustify(words, maxWidth));
    }
}
