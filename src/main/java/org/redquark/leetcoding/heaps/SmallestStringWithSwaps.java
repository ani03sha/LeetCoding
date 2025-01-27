package org.redquark.leetcoding.heaps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class SmallestStringWithSwaps {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        final int n = s.length();
        // Create instance of union find
        final UnionFind unionFind = new UnionFind(n);
        // Unionize all pairs
        for (List<Integer> pair : pairs) {
            unionFind.union(pair.getFirst(), pair.getLast());
        }
        // Mapping for current root with candidate characters
        final Map<Integer, Queue<Character>> mappings = new HashMap<>();
        final char[] characters = s.toCharArray();
        for (int i = 0; i < n; i++) {
            // Get the root w.r.t. current index
            final int root = unionFind.find(i);
            mappings.putIfAbsent(root, new PriorityQueue<>());
            mappings.get(root).offer(characters[i]);
        }
        // Final output
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int root = unionFind.find(i);
            char c = mappings.get(root).remove();
            result.append(c);
        }
        return result.toString();
    }

    static class UnionFind {
        private final int[] parents;

        UnionFind(int n) {
            this.parents = new int[n];
            Arrays.setAll(this.parents, i -> i);
        }

        void union(int a, int b) {
            final int rootA = find(a);
            final int rootB = find(b);
            if (rootA == rootB) {
                return;
            }
            if (rootA < rootB) {
                this.parents[rootB] = rootA;
            } else {
                this.parents[rootA] = rootB;
            }
        }

        int find(int a) {
            if (this.parents[a] != a) {
                this.parents[a] = find(this.parents[a]);
            }
            return this.parents[a];
        }
    }

    public static void main(String[] args) {
        final SmallestStringWithSwaps smallestStringWithSwaps = new SmallestStringWithSwaps();

        String s = "dcab";
        List<List<Integer>> pairs = List.of(List.of(0, 3), List.of(1, 2));
        System.out.println(smallestStringWithSwaps.smallestStringWithSwaps(s, pairs));

        s = "dcab";
        pairs = List.of(List.of(0, 3), List.of(1, 2), List.of(0, 2));
        System.out.println(smallestStringWithSwaps.smallestStringWithSwaps(s, pairs));

        s = "cba";
        pairs = List.of(List.of(0, 1), List.of(1, 2));
        System.out.println(smallestStringWithSwaps.smallestStringWithSwaps(s, pairs));
    }
}
