package org.redquark.leetcoding.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        // List to store the final output
        final List<List<String>> anagrams = new ArrayList<>();
        // Special case
        if (strs == null || strs.length == 0) {
            return anagrams;
        }
        // Map to store the frequency representation of each string with the actual word
        final Map<String, List<String>> mappings = new HashMap<>();
        // Process each string
        for (String str : strs) {
            final String frequencyRepresentation = getFrequencyRepresentation(str);
            mappings.putIfAbsent(frequencyRepresentation, new ArrayList<>());
            mappings.get(frequencyRepresentation).add(str);
        }
        // Add all values to the final list
        anagrams.addAll(mappings.values());
        return anagrams;
    }

    private String getFrequencyRepresentation(String s) {
        final int[] frequencies = new int[26];
        for (char c : s.toCharArray()) {
            frequencies[c - 'a']++;
        }
        return Arrays.toString(frequencies);
    }

    public static void main(String[] args) {
        final GroupAnagrams groupAnagrams = new GroupAnagrams();

        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams.groupAnagrams(strs));

        strs = new String[]{""};
        System.out.println(groupAnagrams.groupAnagrams(strs));

        strs = new String[]{"a"};
        System.out.println(groupAnagrams.groupAnagrams(strs));
    }
}
