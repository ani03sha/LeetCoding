package org.redquark.leetcoding.strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateTheBracketPairsOfAString {

    public String evaluate(String s, List<List<String>> knowledge) {
        final int n = s.length();
        // Create mappings for knowledge list
        final Map<String, String> mappings = new HashMap<>();
        for (List<String> pair : knowledge) {
            mappings.put(pair.get(0), pair.get(1));
        }
        // StringBuilder to store the final result
        final StringBuilder result = new StringBuilder();
        // Process the string
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                i++;
                final StringBuilder key = new StringBuilder();
                while (i < n && s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }
                result.append(mappings.getOrDefault(key.toString(), "?"));
            } else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final EvaluateTheBracketPairsOfAString evaluateTheBracketPairsOfAString = new EvaluateTheBracketPairsOfAString();

        String s = "(name)is(age)yearsold";
        List<List<String>> knowledge = List.of(List.of("name", "bob"), List.of("age", "two"));
        System.out.println(evaluateTheBracketPairsOfAString.evaluate(s, knowledge));

        s = "hi(name)";
        knowledge = List.of(List.of("a", "b"));
        System.out.println(evaluateTheBracketPairsOfAString.evaluate(s, knowledge));

        s = "(a)(a)(a)aaa";
        knowledge = List.of(List.of("a", "yes"));
        System.out.println(evaluateTheBracketPairsOfAString.evaluate(s, knowledge));
    }
}
