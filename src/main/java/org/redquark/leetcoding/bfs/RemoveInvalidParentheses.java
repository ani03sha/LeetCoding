package org.redquark.leetcoding.bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        final List<String> validStrings = new ArrayList<>();
        if (s == null) {
            return validStrings;
        }
        final Queue<String> nodes = new LinkedList<>();
        final Set<String> visited = new HashSet<>();
        nodes.offer(s);
        visited.add(s);
        boolean found = false;
        // Perform BFS
        while (!nodes.isEmpty()) {
            final String current = nodes.remove();
            if (isValid(current)) {
                validStrings.add(current);
                found = true;
            }
            // Only generate next states if no valid strings found yet
            if (!found) {
                for (int i = 0; i < current.length(); i++) {
                    if (current.charAt(i) != '(' && current.charAt(i) != ')') {
                        continue;
                    }
                    String nextString = current.substring(0, i) + current.substring(i + 1);
                    if (!visited.contains(nextString)) {
                        nodes.offer(nextString);
                        visited.add(nextString);
                    }
                }
            }
        }
        return validStrings;
    }

    private boolean isValid(String s) {
        int balanced = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                balanced++;
            } else if (s.charAt(i) == ')') {
                balanced--;
                if (balanced < 0) {
                    return false;
                }
            }
        }
        return balanced == 0;
    }

    public static void main(String[] args) {
        final RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();

        System.out.println(removeInvalidParentheses.removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses.removeInvalidParentheses("(a)())()"));
        System.out.println(removeInvalidParentheses.removeInvalidParentheses(")("));
    }
}
