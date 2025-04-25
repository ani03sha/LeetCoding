package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountLargestGroup {

    public int countLargestGroup(int n) {
        final Map<Integer, List<Integer>> groups = new HashMap<>();
        int maxSize = 0;
        for (int i = 1; i <= n; i++) {
            int sum = calculateSumOfDigits(i);
            groups.computeIfAbsent(sum, _ -> new ArrayList<>()).add(i);
            maxSize = Math.max(maxSize, groups.get(sum).size());
        }
        int count = 0;
        // Check how many groups have max size
        for (List<Integer> group : groups.values()) {
            if (group.size() == maxSize) {
                count++;
            }
        }
        return count;
    }

    private int calculateSumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        CountLargestGroup countLargestGroup = new CountLargestGroup();

        System.out.println(countLargestGroup.countLargestGroup(13));
        System.out.println(countLargestGroup.countLargestGroup(2));
    }
}
