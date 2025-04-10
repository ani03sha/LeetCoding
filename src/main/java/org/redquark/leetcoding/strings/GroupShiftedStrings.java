package org.redquark.leetcoding.strings;

import org.redquark.leetcoding.unionfind.GraphValidTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupShiftedStrings {

    public List<List<String>> groupStrings(String[] strings) {
        // Map to store the same shifting strings
        final Map<String, List<String>> mappings = new HashMap<>();
        // Traverse the 'strings' array
        for (String s : strings) {
            // Get character at the 0-th position
            final char c0 = s.charAt(0);
            // Prepare key for the string
            final StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                sb.append("#").append((c - c0 + 26) % 26);
            }
            final String key = sb.toString();
            mappings.putIfAbsent(key, new ArrayList<>());
            mappings.get(key).add(s);
        }
        return new ArrayList<>(mappings.values());
    }

    public static void main(String[] args) {
        final GroupShiftedStrings groupShiftedStrings = new GroupShiftedStrings();

        String[] strings = new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        System.out.println(groupShiftedStrings.groupStrings(strings));

        strings = new String[]{"a"};
        System.out.println(groupShiftedStrings.groupStrings(strings));
    }
}
