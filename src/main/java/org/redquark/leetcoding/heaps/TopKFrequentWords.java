package org.redquark.leetcoding.heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        // Calculate the frequency of every word in the array
        final Map<String, Integer> frequencies = new HashMap<>();
        for (String word : words) {
            frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
        }
        // Max heap to store words on their frequencies
        final Queue<String> heap = new PriorityQueue<>((word1, word2) -> {
            final int frequencyDifference = frequencies.get(word1) - frequencies.get(word2);
            if (frequencyDifference == 0) {
                return word2.compareTo(word1);
            }
            return frequencyDifference;
        });
        // Add words to the heap and keep only k words at a time
        for (String word : frequencies.keySet()) {
            heap.offer(word);
            if (heap.size() > k) {
                heap.remove();
            }
        }
        // Prepare the final output
        final List<String> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.addFirst(heap.remove());
        }
        return result;
    }

    public static void main(String[] args) {
        final TopKFrequentWords topKFrequentWords = new TopKFrequentWords();

        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        System.out.println(topKFrequentWords.topKFrequent(words, k));

        words = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        k = 4;
        System.out.println(topKFrequentWords.topKFrequent(words, k));
    }
}
