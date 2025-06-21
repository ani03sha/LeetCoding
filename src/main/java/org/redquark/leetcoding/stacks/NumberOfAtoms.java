package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

public class NumberOfAtoms {

    public String countOfAtoms(String formula) {
        // Stack to store groups of molecules
        final Deque<Map<String, Integer>> stack = new ArrayDeque<>();
        // Push the base map
        stack.push(new TreeMap<>());
        // Length of the formula string
        final int n = formula.length();
        // Index to traverse the map
        int index = 0;
        // Process the formula string
        while (index < n) {
            final char c = formula.charAt(index);
            // If the current character is starting parenthesis
            if (c == '(') {
                // Push a new group
                stack.push(new TreeMap<>());
                index++;
            }
            // If the current character is an ending parenthesis
            else if (c == ')') {
                index++;
                // Get the multiplier
                int start = index;
                while (index < n && Character.isDigit(formula.charAt(index))) {
                    index++;
                }
                final int multiplier = start < index ? Integer.parseInt(formula.substring(start, index)) : 1;
                // Now, we need to merge the top of the stack with the
                // group beneath it
                final Map<String, Integer> top = stack.pop();
                final Map<String, Integer> newTop = stack.peek();
                for (Map.Entry<String, Integer> entry : top.entrySet()) {
                    newTop.put(entry.getKey(), newTop.getOrDefault(entry.getKey(), 0) + entry.getValue() * multiplier);
                }
            }
            // Parse the atom name
            else {
                int start = index++;
                while (index < n && Character.isLowerCase(formula.charAt(index))) {
                    index++;
                }
                final String atom = formula.substring(start, index);
                // Parse optional number
                start = index;
                while (index < n && Character.isDigit(formula.charAt(index))) {
                    index++;
                }
                int count = start < index ? Integer.parseInt(formula.substring(start, index)) : 1;
                // Add to the current map
                final Map<String, Integer> top = stack.peek();
                top.put(atom, top.getOrDefault(atom, 0) + count);
            }
        }
        // Format output
        final StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : stack.pop().entrySet()) {
            result.append(entry.getKey());
            if (entry.getValue() > 1) {
                result.append(entry.getValue());
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final NumberOfAtoms numberOfAtoms = new NumberOfAtoms();

        String formula = "H2O";
        System.out.println(numberOfAtoms.countOfAtoms(formula)); // H2O

        formula = "Mg(OH)2";
        System.out.println(numberOfAtoms.countOfAtoms(formula)); // H2MgO2

        formula = "K4(ON(SO3)2)2";
        System.out.println(numberOfAtoms.countOfAtoms(formula)); // K4N2O14S4
    }
}
