package org.redquark.leetcoding.tries;

import org.redquark.leetcoding.utils.TrieNodeWithIndex;

import java.util.Arrays;

public class MinimumCostToConvertStringII {

    // Emulate infinity
    private static final long INFINITY = Long.MAX_VALUE / 2; // To avoid overflow
    // Root of the trie
    private final TrieNodeWithIndex root = new TrieNodeWithIndex();
    // Index of node in the trie
    private int nextIndex = 0;
    // Cost graph
    private long[][] costGraph;
    // Character array representation of the source and target strings
    private char[] sourceChars;
    private char[] targetChars;
    // Lookup table for memoization
    private Long[] lookup;

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        final int n = original.length;
        // Build the trie
        for (int i = 0; i < n; i++) {
            insertWordOrGetIndex(original[i]);
            insertWordOrGetIndex(changed[i]);
        }
        // Total number of nodes
        final int totalNodes = nextIndex;
        this.costGraph = new long[totalNodes][totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            Arrays.fill(this.costGraph[i], INFINITY);
            this.costGraph[i][i] = 0; // The cost to convert a word to itself is 0
        }
        // Add direct transformation costs
        for (int i = 0; i < n; i++) {
            final int from = insertWordOrGetIndex(original[i]);
            final int to = insertWordOrGetIndex(changed[i]);
            this.costGraph[from][to] = Math.min(this.costGraph[from][to], cost[i]);
        }
        // Floyd Warshall algorithm to find the minimum cost between all pairs of nodes
        for (int k = 0; k < totalNodes; k++) {
            for (int i = 0; i < totalNodes; i++) {
                for (int j = 0; j < totalNodes; j++) {
                    this.costGraph[i][j] = Math.min(this.costGraph[i][j], this.costGraph[i][k] + this.costGraph[k][j]);
                }
            }
        }
        this.sourceChars = source.toCharArray();
        this.targetChars = target.toCharArray();
        this.lookup = new Long[sourceChars.length];
        // Perform memoization
        final long result = dfs(0);
        return result == INFINITY ? -1 : result;
    }

    private long dfs(int index) {
        if (index == sourceChars.length) {
            // If we have reached the end of the source string, return 0
            return 0;
        }
        if (lookup[index] != null) {
            // If we have already computed the result for this index, return it
            return lookup[index];
        }
        long result = sourceChars[index] == targetChars[index] ? dfs(index + 1) : INFINITY;
        TrieNodeWithIndex sourceNode = root;
        TrieNodeWithIndex targetNode = root;
        // Try all substrings starting from the current index
        for (int i = index; i < this.sourceChars.length; i++) {
            final int sourceIndex = this.sourceChars[i] - 'a';
            final int targetIndex = this.targetChars[i] - 'a';
            sourceNode = (sourceNode != null) ? sourceNode.children[sourceIndex] : null;
            targetNode = (targetNode != null) ? targetNode.children[targetIndex] : null;
            if (sourceNode == null || targetNode == null) {
                break; // No further substrings can be formed
            }
            // If both nodes exist, calculate the cost to convert the substring
            if (sourceNode.index != -1 && targetNode.index != -1) {
                long cost = this.costGraph[sourceNode.index][targetNode.index];
                if (cost < INFINITY) {
                    long nextCost = cost + dfs(i + 1);
                    result = Math.min(result, nextCost);
                }
            }
        }
        return lookup[index] = result;
    }

    private int insertWordOrGetIndex(String word) {
        // Reference to the root node
        TrieNodeWithIndex current = root;
        // Traverse through the word
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            // If the child node does not exist, create a new one
            if (current.children[index] == null) {
                current.children[index] = new TrieNodeWithIndex();
            }
            // Move to the child node
            current = current.children[index];
        }
        if (current.index == -1) {
            current.index = nextIndex++;
        }
        return current.index;
    }

    public static void main(String[] args) {
        final MinimumCostToConvertStringII minimumCostToConvertStringII = new MinimumCostToConvertStringII();

        String source = "abcd";
        String target = "acbe";
        String[] original = new String[]{"a", "b", "c", "c", "e", "d"};
        String[] changed = new String[]{"b", "c", "b", "e", "b", "e"};
        int[] cost = new int[]{2, 5, 5, 1, 2, 20};
        long result = minimumCostToConvertStringII.minimumCost(source, target, original, changed, cost);
        System.out.println("Minimum cost to convert source to target: " + result);

        source = "abcdefgh";
        target = "acdeeghh";
        original = new String[]{"bcd", "fgh", "thh"};
        changed = new String[]{"cde", "thh", "ghh"};
        cost = new int[]{1, 3, 5};
        result = minimumCostToConvertStringII.minimumCost(source, target, original, changed, cost);
        System.out.println("Minimum cost to convert source to target: " + result);

        source = "abcdefgh";
        target = "addddddd";
        original = new String[]{"bcd", "defgh"};
        changed = new String[]{"ddd", "ddddd"};
        cost = new int[]{100, 1578};
        result = minimumCostToConvertStringII.minimumCost(source, target, original, changed, cost);
        System.out.println("Minimum cost to convert source to target: " + result);
    }
}
