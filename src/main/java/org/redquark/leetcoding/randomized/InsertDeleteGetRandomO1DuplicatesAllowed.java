package org.redquark.leetcoding.randomized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class InsertDeleteGetRandomO1DuplicatesAllowed {

    static class RandomizedCollection {

        // List to store all the elements in the collection
        private final List<Integer> elements;
        // Map to store inserted elements with the size of the list
        private final Map<Integer, Set<Integer>> mappings;
        // Instance of Random class to generate random index
        private final Random random;

        public RandomizedCollection() {
            this.elements = new ArrayList<>();
            this.mappings = new HashMap<>();
            this.random = new Random();
        }

        public boolean insert(int val) {
            // Flag to denote if an element is already present in the collection
            final boolean isElementPresent = this.mappings.containsKey(val);
            // Add the element to the mappings
            this.mappings.putIfAbsent(val, new HashSet<>());
            this.mappings.get(val).add(this.elements.size());
            // Add the element to the list
            this.elements.add(val);
            return isElementPresent;
        }

        public boolean remove(int val) {
            // If the element is not present in the collection
            if (!mappings.containsKey(val) || this.mappings.get(val).isEmpty()) {
                return false;
            }
            // Get any index of the val
            final int index = this.mappings.get(val).iterator().next();
            mappings.get(val).remove(index);
            // Last index in the list
            final int lastIndex = this.elements.size() - 1;
            // Last element in the list
            final int lastElement = this.elements.getLast();
            // Swap the index with the last index
            if (lastIndex != index) {
                this.elements.set(index, lastElement);
                this.mappings.get(lastElement).remove(lastIndex);
                this.mappings.get(lastElement).add(index);
            }
            // Remove last element
            this.elements.removeLast();
            if (this.mappings.get(val).isEmpty()) {
                this.mappings.remove(val);
            }
            return true;
        }

        public int getRandom() {
            return this.elements.get(random.nextInt(elements.size()));
        }
    }

    public static void main(String[] args) {
        final RandomizedCollection randomizedCollection = new RandomizedCollection();

        System.out.println(randomizedCollection.insert(1));   // return true since the collection does not contain 1.
        // Inserts 1 into the collection.
        System.out.println(randomizedCollection.insert(1));   // return false since the collection contains 1.
        // Inserts another 1 into the collection. The Collection now contains [1,1].
        System.out.println(randomizedCollection.insert(2));   // return true since the collection does not contain 2.
        // Inserts 2 into the collection. The Collection now contains [1,1,2].
        System.out.println(randomizedCollection.getRandom()); // getRandom should:
        // - return 1 with probability 2/3, or
        // - return 2 with probability 1/3.
        System.out.println(randomizedCollection.remove(1));   // return true since the collection contains 1.
        // Removes 1 from the collection. The Collection now contains [1,2].
        System.out.println(randomizedCollection.getRandom()); // getRandom should return 1 or 2, both equally likely.

    }
}
