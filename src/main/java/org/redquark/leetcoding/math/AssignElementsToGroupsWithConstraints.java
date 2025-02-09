package org.redquark.leetcoding.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AssignElementsToGroupsWithConstraints {

    public int[] assignElements(int[] groups, int[] elements) {
        // Map to store all elements and their indices
        final Map<Integer, Integer> elementMappings = new HashMap<>();
        for (int i = 0; i < elements.length; i++) {
            elementMappings.putIfAbsent(elements[i], i);
        }
        final int n = groups.length;
        // Array to store the assigned index
        final int[] assigned = new int[n];
        // Populate the assigned index
        for (int i = 0; i < n; i++) {
            assigned[i] = getMinimumIndex(groups[i], elementMappings);
        }
        return assigned;
    }

    private int getMinimumIndex(int groupSize, Map<Integer, Integer> elementMappings) {
        // Minimum index of the divisor
        int minIndex = Integer.MAX_VALUE;
        for (int i = 1; i * i <= groupSize; i++) {
            // If the current value of i divides the groupSize
            if (groupSize % i == 0) {
                // Check if this i is present in the map
                if (elementMappings.containsKey(i)) {
                    minIndex = Math.min(minIndex, elementMappings.get(i));
                }
                // Check for co-divisor
                if (groupSize / i != i && elementMappings.containsKey(groupSize / i)) {
                    minIndex = Math.min(minIndex, elementMappings.get(groupSize / i));
                }
            }
        }
        return minIndex == Integer.MAX_VALUE ? -1 : minIndex;
    }

    public static void main(String[] args) {
        final AssignElementsToGroupsWithConstraints assignElementsToGroupsWithConstraints = new AssignElementsToGroupsWithConstraints();

        int[] groups = new int[]{8, 4, 3, 2, 4};
        int[] elements = new int[]{4, 2};
        System.out.println(Arrays.toString(assignElementsToGroupsWithConstraints.assignElements(groups, elements)));

        groups = new int[]{2, 3, 5, 7};
        elements = new int[]{5, 3, 3};
        System.out.println(Arrays.toString(assignElementsToGroupsWithConstraints.assignElements(groups, elements)));

        groups = new int[]{10, 21, 30, 41};
        elements = new int[]{2, 1};
        System.out.println(Arrays.toString(assignElementsToGroupsWithConstraints.assignElements(groups, elements)));
    }
}
