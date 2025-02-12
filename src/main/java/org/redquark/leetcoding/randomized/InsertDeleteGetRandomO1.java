package org.redquark.leetcoding.randomized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InsertDeleteGetRandomO1 {

    static class RandomizedSet {

        // List to store the actual elements
        private final List<Integer> elements;
        // Map to store inserted elements with the size of the list
        private final Map<Integer, Integer> mappings;
        // Instance of the Random class
        private final Random random;

        public RandomizedSet() {
            this.elements = new ArrayList<>();
            this.mappings = new HashMap<>();
            this.random = new Random();
        }

        public boolean insert(int val) {
            // If the element already exists
            if (mappings.containsKey(val)) {
                return false;
            }
            // Add the mapping
            mappings.put(val, elements.size());
            // Add the elements to the list
            elements.add(val);
            return true;
        }

        public boolean remove(int val) {
            // If the value is not present in the set
            if (!mappings.containsKey(val)) {
                return false;
            }
            // Get the index where the current value is stored
            final int index = mappings.get(val);
            // If this element is not the last element in the list,
            // we will swap it with the last element
            if (index != elements.size() - 1) {
                // Get the last element from the list
                final int lastElement = elements.getLast();
                // Add the lastElement to the index
                elements.set(index, lastElement);
                // Update the mappings
                mappings.put(lastElement, index);
            }
            // Remove the val from mappings and elements
            elements.removeLast();
            mappings.remove(val);
            return true;
        }

        public int getRandom() {
            return elements.get(random.nextInt(elements.size()));
        }
    }

    public static void main(String[] args) {
        final RandomizedSet randomizedSet = new RandomizedSet();

        System.out.println(randomizedSet.insert(1)); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        System.out.println(randomizedSet.remove(2)); // Returns false as 2 does not exist in the set.
        System.out.println(randomizedSet.insert(2)); // Inserts 2 to the set, returns true. Set now contains [1,2].
        System.out.println(randomizedSet.getRandom()); // getRandom() should return either 1 or 2 randomly.
        System.out.println(randomizedSet.remove(1)); // Removes 1 from the set, returns true. Set now contains [2].
        System.out.println(randomizedSet.insert(2)); // 2 was already in the set, so return false.
        System.out.println(randomizedSet.getRandom()); // Since 2 is the only number in the set, getRandom() will always return 2.
    }
}
