package org.redquark.extras.solutions.trees;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindClosestDepartment {

    public String findClosestDepartment(Map<String, List<String>> orgChart, Map<String, String> employeeToDepartment, List<String> employees) {
        // Build the parent map by reversing the org chart
        final Map<String, String> childParentMappings = new HashMap<>();
        orgChart.forEach((parent, children) -> children.forEach(child -> childParentMappings.put(child, parent)));
        // Start with the first employee and find its base department
        final String firstEmployee = employees.getFirst();
        // Department of the first employee
        String baseDepartment = employeeToDepartment.get(firstEmployee);
        // Find common ancestors to this base department
        final Set<String> ancestors = getAncestors(baseDepartment, childParentMappings);
        // Intersect with ancestors of remaining employees
        for (int i = 1; i < employees.size(); i++) {
            final String currentDepartment = employeeToDepartment.get(employees.get(i));
            ancestors.retainAll(getAncestors(currentDepartment, childParentMappings));
        }
        // Walk up from the base department to find LCA
        while (baseDepartment != null) {
            if (ancestors.contains(baseDepartment)) {
                return baseDepartment;
            }
            baseDepartment = childParentMappings.get(baseDepartment);
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
