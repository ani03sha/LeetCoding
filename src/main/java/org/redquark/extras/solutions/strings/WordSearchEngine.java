package org.redquark.extras.solutions.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordSearchEngine {

    static class WordSearchEngineUsingPlainMap {

        // Map to store inverted index => word -> [docIds]
        private final Map<String, Set<Integer>> invertedIndex = new HashMap<>();
        // Map to store the document id and the text in that document
        private final Map<Integer, String> documents = new HashMap<>();

        public void addDocument(int documentId, String text) {
            text = text.toLowerCase();
            this.documents.put(documentId, text);
            // Get all words in the string
            final String[] words = text.split("\\s+");
            // Add all words
            for (String word : words) {
                this.invertedIndex.computeIfAbsent(word, _ -> new HashSet<>()).add(documentId);
            }
        }

        public List<Integer> searchWord(String word) {
            word = word.toLowerCase();
            return new ArrayList<>(this.invertedIndex.getOrDefault(word, Set.of()));
        }

        public List<Integer> searchPhrase(String phrase) {
            phrase = phrase.toLowerCase();
            final List<Integer> result = new ArrayList<>();
            for (Map.Entry<Integer, String> entry : this.documents.entrySet()) {
                if (entry.getValue().contains(phrase)) {
                    result.add(entry.getKey());
                }
            }
            return result;
        }
    }

    static class WordSearchEngineUsingPositionalIndex {
        // Map to store word -> [documentId, List of positions]
        private final Map<String, Map<Integer, List<Integer>>> invertedPositionalIndex;

        public WordSearchEngineUsingPositionalIndex() {
            this.invertedPositionalIndex = new HashMap<>();
        }

        public void addDocument(Integer documentId, String text) {
            // Get all tokens in the string
            final String[] tokens = text.toLowerCase().split("\\s+");
            // Update the index using positions
            for (int position = 0; position < tokens.length; position++) {
                this.invertedPositionalIndex
                        .computeIfAbsent(tokens[position], _ -> new HashMap<>())
                        .computeIfAbsent(documentId, _ -> new ArrayList<>())
                        .add(position);
            }
        }

        public List<Integer> searchWord(String word) {
            word = word.toLowerCase();
            if (!this.invertedPositionalIndex.containsKey(word)) {
                return List.of();
            }
            return new ArrayList<>(this.invertedPositionalIndex.get(word).keySet());
        }

        public List<Integer> searchPhrase(String phrase) {
            final String[] tokens = phrase.toLowerCase().split("\\s+");
            if (tokens == null || !this.invertedPositionalIndex.containsKey(tokens[0])) {
                return List.of();
            }
            // Get documents corresponding to the first word
            final Map<Integer, List<Integer>> firstWordDocuments = this.invertedPositionalIndex.get(tokens[0]);
            // List to store the final result
            final List<Integer> result = new ArrayList<>();
            // Search in docs
            for (int documentId : firstWordDocuments.keySet()) {
                // Positions of the first word
                final List<Integer> firstWordPositions = firstWordDocuments.get(documentId);
                for (int position : firstWordPositions) {
                    boolean match = true;
                    for (int i = 1; i < tokens.length; i++) {
                        final String nextWord = tokens[i];
                        final Map<Integer, List<Integer>> nextWordDocs = this.invertedPositionalIndex.get(nextWord);
                        if (nextWordDocs == null || !nextWordDocs.containsKey(documentId)) {
                            match = false;
                            break;
                        }
                        final List<Integer> nextWordPositions = nextWordDocs.get(documentId);
                        if (!nextWordPositions.contains(position + i)) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        result.add(documentId);
                        break;
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        final WordSearchEngineUsingPlainMap wordSearchEngineUsingPlainMap = new WordSearchEngineUsingPlainMap();

        wordSearchEngineUsingPlainMap.addDocument(1, "The quick brown fox");
        wordSearchEngineUsingPlainMap.addDocument(2, "Jumped over the lazy dog");
        wordSearchEngineUsingPlainMap.addDocument(3, "the quick dog is fast");

        System.out.println("Search 'quick': " + wordSearchEngineUsingPlainMap.searchWord("quick")); // [1, 3]
        System.out.println("Search 'the quick': " + wordSearchEngineUsingPlainMap.searchPhrase("the quick")); // [1, 3]
        System.out.println("Search 'lazy dog': " + wordSearchEngineUsingPlainMap.searchPhrase("lazy dog")); // [2]

        final WordSearchEngineUsingPositionalIndex wordSearchEngineUsingPositionalIndex = new WordSearchEngineUsingPositionalIndex();

        wordSearchEngineUsingPositionalIndex.addDocument(1, "The quick brown fox");
        wordSearchEngineUsingPositionalIndex.addDocument(2, "Jumped over the lazy dog");
        wordSearchEngineUsingPositionalIndex.addDocument(3, "the quick dog is fast");

        System.out.println("Search 'quick': " + wordSearchEngineUsingPositionalIndex.searchWord("quick")); // [1, 3]
        System.out.println("Search 'the quick': " + wordSearchEngineUsingPositionalIndex.searchPhrase("the quick")); // [1, 3]
        System.out.println("Search 'lazy dog': " + wordSearchEngineUsingPositionalIndex.searchPhrase("lazy dog")); // [2]


    }
}
