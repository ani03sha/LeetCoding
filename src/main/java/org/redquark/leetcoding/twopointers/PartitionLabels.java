package org.redquark.leetcoding.twopointers;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {

    public List<Integer> partitionLabels(String s) {
        // List to store partition indices
        final List<Integer> partitions = new ArrayList<>();
        // Special case
        if (s == null || s.isEmpty()) {
            return partitions;
        }
        final int n = s.length();
        // Array to store the last index of every character
        final int[] lastIndices = new int[26];
        for (int i = 0; i < n; i++) {
            lastIndices[s.charAt(i) - 'a'] = i;
        }
        // Left and right pointers of the window
        int left = 0;
        int right = 0;
        // Process the string again
        for (int i = 0; i < n; i++) {
            // Get the last index of current character
            right = Math.max(right, lastIndices[s.charAt(i) - 'a']);
            // If the current index is equal to the last index, it
            // means our partition ends here
            if (i == right) {
                partitions.add(right - left + 1);
                left = right + 1;
            }
        }
        return partitions;
    }

    public static void main(String[] args) {
        final PartitionLabels partitionLabels = new PartitionLabels();

        String s = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels.partitionLabels(s));

        s = "eccbbbbdec";
        System.out.println(partitionLabels.partitionLabels(s));
    }
}
