package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighAccessEmployees {

    public List<String> findHighAccessEmployees(List<List<String>> accessTimes) {
        // Map to keep track of employees and their access times
        final Map<String, List<Integer>> accessTimeMappings = new HashMap<>();
        for (List<String> accessTime : accessTimes) {
            final String employee = accessTime.getFirst();
            final Integer time = Integer.parseInt(accessTime.getLast());
            accessTimeMappings.computeIfAbsent(employee, _ -> new ArrayList<>()).add(time);
        }
        // List to store high access employees
        final List<String> highAccessEmployees = new ArrayList<>();
        // Process the map
        for (Map.Entry<String, List<Integer>> entry : accessTimeMappings.entrySet()) {
            final List<Integer> times = entry.getValue();
            // Sort the access times of the current employee
            Collections.sort(times);
            // Flag to indicate if an employee is a high access employee
            boolean isHighAccess = false;
            // Check if access times satisfy the rules
            for (int i = 0; i + 3 <= times.size(); i++) {
                isHighAccess |= times.get(i + 2) < times.get(i) + 100;
            }
            if (isHighAccess) {
                highAccessEmployees.add(entry.getKey());
            }
        }
        return highAccessEmployees;
    }

    public static void main(String[] args) {
        final HighAccessEmployees highAccessEmployees = new HighAccessEmployees();

        List<List<String>> accessTimes = List.of(
                List.of("a", "0549"),
                List.of("b", "0457"),
                List.of("a", "0532"),
                List.of("b", "0540"),
                List.of("a", "0621")
        );
        System.out.println(highAccessEmployees.findHighAccessEmployees(accessTimes));

        accessTimes = List.of(
                List.of("d", "0002"),
                List.of("c", "0808"),
                List.of("c", "0829"),
                List.of("e", "0215"),
                List.of("d", "1508"),
                List.of("d", "1444"),
                List.of("d", "1410"),
                List.of("c", "0809")
        );
        System.out.println(highAccessEmployees.findHighAccessEmployees(accessTimes));

        accessTimes = List.of(
                List.of("cd", "1025"),
                List.of("ab", "1025"),
                List.of("cd", "1046"),
                List.of("cd", "1055"),
                List.of("ab", "1124"),
                List.of("ab", "1120")
        );
        System.out.println(highAccessEmployees.findHighAccessEmployees(accessTimes));
    }
}
