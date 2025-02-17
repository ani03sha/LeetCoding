package org.redquark.leetcoding.design;

import java.util.HashMap;
import java.util.Map;

public class TwoSumIIIDataStructureDesign {

    static class TwoSum {

        // Map to store the elements and their frequencies
        private final Map<Integer, Integer> frequencies;

        public TwoSum() {
            this.frequencies = new HashMap<>();
        }

        public void add(int number) {
            this.frequencies.put(number, this.frequencies.getOrDefault(number, 0) + 1);
        }

        public boolean find(int value) {
            // Check for every number
            for (int x : this.frequencies.keySet()) {
                final int y = value - x;
                if (x == y) {
                    return this.frequencies.get(x) >= 2;
                } else {
                    return this.frequencies.containsKey(y);
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        final TwoSum twoSum = new TwoSum();

        twoSum.add(1);   // [] --> [1]
        twoSum.add(3);   // [1] --> [1,3]
        twoSum.add(5);   // [1,3] --> [1,3,5]
        System.out.println(twoSum.find(4));  // 1 + 3 = 4, return true
        System.out.println(twoSum.find(7));  // No two integers sum up to 7, return false
    }
}
