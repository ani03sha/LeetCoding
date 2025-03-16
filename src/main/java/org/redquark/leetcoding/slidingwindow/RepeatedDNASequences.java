package org.redquark.leetcoding.slidingwindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        // Set to store the subsequences
        final Set<String> sequences = new HashSet<>();
        // Set to store the seen subsequences
        final Set<String> seen = new HashSet<>();
        // Process the substrings of length 10
        int index = 0;
        while (index <= s.length() - 10) {
            // Get the current substring
            final String current = s.substring(index, index + 10);
            // Check if we have encountered this substring before
            if (seen.contains(current) && !sequences.contains(current)) {
                sequences.add(current);
            } else {
                seen.add(current);
            }
            index++;
        }
        return new ArrayList<>(sequences);
    }

    public static void main(String[] args) {
        final RepeatedDNASequences repeatedDNASequences = new RepeatedDNASequences();

        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(repeatedDNASequences.findRepeatedDnaSequences(s));

        s = "AAAAAAAAAAAAA";
        System.out.println(repeatedDNASequences.findRepeatedDnaSequences(s));
    }
}
