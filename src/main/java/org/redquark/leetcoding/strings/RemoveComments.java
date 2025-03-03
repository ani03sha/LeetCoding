package org.redquark.leetcoding.strings;

import java.util.ArrayList;
import java.util.List;

public class RemoveComments {

    public List<String> removeComments(String[] source) {
        // List to store the final result
        final List<String> result = new ArrayList<>();
        // Special case
        if (source == null) {
            return result;
        }
        // Line without comments
        final StringBuilder lineWithoutComments = new StringBuilder();
        // Flag to indicate if we are inside a block comment
        boolean areWeInsideBlockComment = false;
        // Process every line in the source
        for (String line : source) {
            // Length of the current line
            final int n = line.length();
            for (int i = 0; i < n; i++) {
                // If we are inside a block comment, look for its end
                if (areWeInsideBlockComment) {
                    if (i < n - 1 && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                        areWeInsideBlockComment = false;
                        i++;
                    }
                } else {
                    // If the current line only has the start of block comment
                    if (i < n - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                        areWeInsideBlockComment = true;
                        // Skip this line
                        i++;
                    }
                    // If not a block comment, check if we are inside a line comment
                    else if (i < n - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                        break;
                    }
                    // Not any kind of comment
                    else {
                        lineWithoutComments.append(line.charAt(i));
                    }
                }
            }
            // If the current line is completed, and we are not in any block comment,
            // we append this line to the result
            if (!areWeInsideBlockComment && !lineWithoutComments.isEmpty()) {
                result.add(lineWithoutComments.toString());
                // Reset StringBuilder for the next iteration
                lineWithoutComments.setLength(0);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final RemoveComments removeComments = new RemoveComments();

        String[] source = new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;",
                "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        System.out.println(removeComments.removeComments(source));

        source = new String[]{"a/*comment", "line", "more_comment*/b"};
        System.out.println(removeComments.removeComments(source));
    }
}
