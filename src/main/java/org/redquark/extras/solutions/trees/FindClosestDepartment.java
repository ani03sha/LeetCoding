package org.redquark.extras.solutions.trees;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindClosestDepartment {

    public String findClosestDepartment(Map<String, List<String>> orgChart, Map<String, String> employeeToDepartment, List<String> employees) {
        // Build the parent map by reversing org chart
        final Map<String, String> parentMap = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : orgChart.entrySet()) {
            for (String child : entry.getValue()) {
                parentMap.put(child, entry.getKey());
            }
        }
        // Initialize with ancestors for the first employee
        final String firstEmployee = employees.getFirst();
        final Set<String> ancestors = getAncestors(employeeToDepartment.get(firstEmployee), parentMap);
        // Intersect with ancestors of other employees
        for (int i = 1; i < employees.size(); i++) {
            final String currentEmployee = employees.get(i);
            final String currentDepartment = employeeToDepartment.get(currentEmployee);
            final Set<String> currentAncestors = getAncestors(currentDepartment, parentMap);
            ancestors.retainAll(currentAncestors);
        }
        // Find the closest department
        String department = employeeToDepartment.get(firstEmployee);
        while (department != null) {
            if (ancestors.contains(department)) {
                return department;
            }
            department = parentMap.get(department);
        }
        return null;
    }

    private Set<String> getAncestors(String department, Map<String, String> parentMap) {
        final Set<String> ancestors = new HashSet<>();
        while (department != null) {
            ancestors.add(department);
            department = parentMap.get(department);
        }
        return ancestors;
    }

    public static void main(String[] args) {

        Map<String, List<String>> orgChart = new HashMap<>();
        orgChart.put("Company", Arrays.asList("Engineering", "Sales"));
        orgChart.put("Engineering", Arrays.asList("Platform", "Infra"));
        orgChart.put("Platform", Arrays.asList("Frontend", "Backend"));

        Map<String, String> employeeToDept = Map.of(
                "Alice", "Frontend",
                "Bob", "Backend",
                "Carol", "Infra",
                "Dan", "Sales"
        );

        final FindClosestDepartment findClosestDepartment = new FindClosestDepartment();

        System.out.println("Closest department for [Alice, Bob]: " +
                findClosestDepartment.findClosestDepartment(orgChart, employeeToDept, List.of("Alice", "Bob")));

        System.out.println("Closest department for [Alice, Carol]: " +
                findClosestDepartment.findClosestDepartment(orgChart, employeeToDept, List.of("Alice", "Carol")));

        System.out.println("Closest department for [Bob, Carol]: " +
                findClosestDepartment.findClosestDepartment(orgChart, employeeToDept, List.of("Bob", "Carol")));

        System.out.println("Closest department for [Alice, Bob, Carol]: " +
                findClosestDepartment.findClosestDepartment(orgChart, employeeToDept, List.of("Alice", "Bob", "Carol")));

        System.out.println("Closest department for [Alice, Bob, Carol, Dan]: " +
                findClosestDepartment.findClosestDepartment(orgChart, employeeToDept, List.of("Alice", "Bob", "Carol", "Dan")));
    }
}
