package org.redquark.leetcoding.design;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {

    static class Codec {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            // StringBuilder to store the encoded string
            final StringBuilder encodedString = new StringBuilder();
            // Process every string in the list
            for (String s : strs) {
                encodedString.append(s.length()).append('#').append(s);
            }
            return encodedString.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            // List to store the final decoded strings
            final List<String> decodedStrings = new ArrayList<>();
            final int n = s.length();
            int i = 0;
            // Process encoded string character by character
            while (i < n) {
                // First get the length of the upcoming string
                int j = i;
                while (j < n && s.charAt(j) != '#') {
                    j++;
                }
                final int length = Integer.parseInt(s.substring(i, j));
                j++; // Move past '#'
                decodedStrings.add(s.substring(j, j + length));
                i = j + length;
            }
            return decodedStrings;
        }
    }

    public static void main(String[] args) {
        final Codec codec = new Codec();
        List<String> strings = List.of("Hello", "World", "LeetCode");
        String encodedString = codec.encode(strings);
        System.out.println("Encoded String: " + encodedString);
        List<String> decodedStrings = codec.decode(encodedString);
        System.out.println("Decoded Strings: " + decodedStrings);

        // Check if the original strings and decoded strings are same
        if (strings.equals(decodedStrings)) {
            System.out.println("Original and decoded strings are same.");
        } else {
            System.out.println("Original and decoded strings are different.");
        }

        // Test with an empty list
        strings = List.of();
        encodedString = codec.encode(strings);
        System.out.println("Encoded String for empty list: " + encodedString);
        decodedStrings = codec.decode(encodedString);
        System.out.println("Decoded Strings for empty list: " + decodedStrings);
        // Check if the original strings and decoded strings are same
        if (strings.equals(decodedStrings)) {
            System.out.println("Original and decoded strings for empty list are same.");
        } else {
            System.out.println("Original and decoded strings for empty list are different.");
        }
    }
}
