package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MaximumFrequencyStack {

    static class FreqStack {
        // Variable to keep track of maximum frequency
        private int maxFrequency;
        // Map to store frequencies of elements
        private final Map<Integer, Integer> frequencies;
        // Map to store elements with same frequency in the stack
        private final Map<Integer, Deque<Integer>> frequencyToElements;

        public FreqStack() {
            this.frequencies = new HashMap<>();
            this.frequencyToElements = new HashMap<>();
        }

        public void push(int val) {
            frequencies.put(val, frequencies.getOrDefault(val, 0) + 1);
            int frequency = frequencies.get(val);
            this.maxFrequency = Math.max(maxFrequency, frequency);
            if (!frequencyToElements.containsKey(frequency)) {
                frequencyToElements.put(frequency, new ArrayDeque<>());
            }
            frequencyToElements.get(frequency).push(val);
        }

        public int pop() {
            int poppedValue = frequencyToElements.get(maxFrequency).pop();
            frequencies.put(poppedValue, frequencies.get(poppedValue) - 1);
            if (this.frequencyToElements.get(maxFrequency).isEmpty()) {
                maxFrequency--;
            }
            return poppedValue;
        }
    }

    public static void main(String[] args) {
        final FreqStack freqStack = new FreqStack();

        freqStack.push(5); // The stack is [5]
        freqStack.push(7); // The stack is [5,7]
        freqStack.push(5); // The stack is [5,7,5]
        freqStack.push(7); // The stack is [5,7,5,7]
        freqStack.push(4); // The stack is [5,7,5,7,4]
        freqStack.push(5); // The stack is [5,7,5,7,4,5]
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }
}
