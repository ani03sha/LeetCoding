package org.redquark.leetcoding.bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class OpenTheLock {

    public int openLock(String[] deadends, String target) {
        // Special case
        if (target == null || target.isEmpty() || deadends == null || deadends.length == 0) {
            throw new IllegalArgumentException("Invalid inputs");
        }
        // Convert the array of deadends to HashSet for O(1) lookup
        final Set<String> invalidCombinations = new HashSet<>(Arrays.asList(deadends));
        // Set to store already tried combinations
        final Set<String> visited = new HashSet<>();
        visited.add("0000");
        // Queue to perform BFS
        final Deque<String> lockPositions = new ArrayDeque<>();
        lockPositions.offer("0000");
        // Minimum turns required
        int turns = 0;
        // Process until we have elements in the queue
        while (!lockPositions.isEmpty()) {
            int size = lockPositions.size();
            while (size > 0) {
                // Get the current position
                final String currentPosition = lockPositions.remove();
                // If the current position is an invalid position
                if (invalidCombinations.contains(currentPosition)) {
                    size--;
                    continue;
                }
                if (currentPosition.equals(target)) {
                    return turns;
                }
                // Check various lock combinations now
                final StringBuilder combination = new StringBuilder(currentPosition);
                for (int i = 0; i < 4; i++) {
                    char c = combination.charAt(i);
                    // Create two combinations of string
                    final String s1 = combination.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + combination.substring(i + 1);
                    final String s2 = combination.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + combination.substring(i + 1);
                    // If the strings have not already been visited and not an invalid combination, we add them to the queue
                    if (!visited.contains(s1) && !invalidCombinations.contains(s1)) {
                        lockPositions.offer(s1);
                        visited.add(s1);
                    }
                    if (!visited.contains(s2) && !invalidCombinations.contains(s2)) {
                        lockPositions.offer(s2);
                        visited.add(s2);
                    }
                }
                size--;
            }
            turns++;
        }
        return -1;
    }

    public static void main(String[] args) {
        final OpenTheLock openTheLock = new OpenTheLock();

        String[] deadends = new String[]{"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        System.out.println(openTheLock.openLock(deadends, target));

        deadends = new String[]{"8888"};
        target = "0009";
        System.out.println(openTheLock.openLock(deadends, target));

        deadends = new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        target = "8888";
        System.out.println(openTheLock.openLock(deadends, target));
    }
}
