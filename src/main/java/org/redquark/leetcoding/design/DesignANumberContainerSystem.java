package org.redquark.leetcoding.design;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DesignANumberContainerSystem {

    static class NumberContainers {

        // Map to store mappings of a number and list of indices at that index
        private final Map<Integer, Queue<Integer>> numberToIndices;
        // Map to store mappings of index and the number at that index
        private final Map<Integer, Integer> indexToNumber;

        public NumberContainers() {
            this.numberToIndices = new HashMap<>();
            this.indexToNumber = new HashMap<>();
        }

        public void change(int index, int number) {
            // Create an entry for the number in the table, if not already exists
            this.numberToIndices.putIfAbsent(number, new PriorityQueue<>());
            // Deal with an index that has already been encountered
            if (this.indexToNumber.containsKey(index)) {
                final int existingNumber = this.indexToNumber.get(index);
                // If the index is being updated with the new number
                if (existingNumber != number) {
                    // We have to remove the current index from the list of the
                    // indices where existingNumber is present
                    this.numberToIndices.get(existingNumber).remove(index);
                    if (this.numberToIndices.get(existingNumber).isEmpty()) {
                        this.numberToIndices.remove(existingNumber);
                    }
                }
                // The number is already present at the same index, we don't have to do anything
                else {
                    return;
                }
                // Update the index list of the current number encountered
                this.numberToIndices.get(number).offer(index);
            } else {
                // This index has encountered for the first time
                this.indexToNumber.put(index, number);
                // Update the index list of number
                this.numberToIndices.get(number).offer(index);
            }
        }

        public int find(int number) {
            // If the number already exists, we return the smallest index
            // at which this number is present, else we return -1
            if (this.numberToIndices.containsKey(number)) {
                return this.numberToIndices.get(number).peek();
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        NumberContainers numberContainers = new NumberContainers();

        numberContainers.change(2, 10); // Your container at index 2 will be filled with number 10.
        numberContainers.change(1, 10); // Your container at index 1 will be filled with number 10.
        numberContainers.change(3, 10); // Your container at index 3 will be filled with number 10.
        numberContainers.change(5, 10); // Your container at index 5 will be filled with number 10.
        System.out.println(numberContainers.find(10)); // Number 10 is at the indices 1, 2, 3, and 5. Since the smallest index filled with 10 is 1, we return 1.
        numberContainers.change(1, 20); // Your container at index 1 will be filled with number 20. Note that index 1 was filled with 10 and then replaced with 20.
        System.out.println(numberContainers.find(10)); // Number 10 is at the indices 2, 3, and 5. The smallest index filled with 10 is 2. Therefore, we return 2.

        numberContainers = new NumberContainers();
        numberContainers.change(1, 10);
        numberContainers.change(1, 10);
        System.out.println(numberContainers.find(10));
        numberContainers.change(1, 20);
        System.out.println(numberContainers.find(10));
    }
}
