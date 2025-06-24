package org.redquark.extras.solutions.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordSearchEngine {

    static class WordSearchEngineUsingPlainMap {
        // Inverted index map that stores word with documentIds
        private final Map<String, Set<Integer>> invertedIndex;
        // Map to store documentId and its respective text
        private final Map<Integer, String> documents;

        public WordSearchEngineUsingPlainMap() {
            this.invertedIndex = new HashMap<>();
            this.documents = new HashMap<>();
        }

        public void addDocument(int documentId, String text) {
            text = text.toLowerCase();
            // Add entry to the "documents" map
            this.documents.put(documentId, text);
            // Tokenize the text and store in inverted index
            final String[] tokens = text.split("\\s+");
            for (String token : tokens) {
                this.invertedIndex.computeIfAbsent(token, _ -> new HashSet<>()).add(documentId);
            }
        }

        public List<Integer> searchWord(String word) {
            // Convert the word to lower case
            word = word.toLowerCase();
            // Get the documentIds from inverted index
            return new ArrayList<>(this.invertedIndex.getOrDefault(word, Set.of()));
        }

        public List<Integer> searchPhrase(String phrase) {
            final List<Integer> documentIds = new ArrayList<>();
            for (Map.Entry<Integer, String> entry : this.documents.entrySet()) {
                if (entry.getValue().contains(phrase)) {
                    documentIds.add(entry.getKey());
                }
            }
            return documentIds;
        }
    }

    static class WordSearchEngineUsingPositionalIndex {
        // Inverted index to store mappings of word and map of documents and words'
        // position in that document
        private final Map<String, Map<Integer, List<Integer>>> invertedIndex;

        public WordSearchEngineUsingPositionalIndex() {
            this.invertedIndex = new HashMap<>();
        }

        public void addDocument(int documentId, String text) {
            // Tokenize the text
            final String[] tokens = text.toLowerCase().split("\\s+");
            // Process every token
            for (int position = 0; position < tokens.length; position++) {
                this.invertedIndex
                        .computeIfAbsent(tokens[position], _ -> new HashMap<>())
                        .computeIfAbsent(documentId, _ -> new ArrayList<>())
                        .add(position);
            }
        }

        public List<Integer> searchWord(String word) {
            word = word.toLowerCase();
            if (!this.invertedIndex.containsKey(word)) {
                return List.of();
            }
            return new ArrayList<>(this.invertedIndex.get(word).keySet());
        }

        public List<Integer> searchPhrase(String phrase) {
            // Tokenize the text
            final String[] tokens = phrase.toLowerCase().split("\\s+");
            if (tokens == null || !this.invertedIndex.containsKey(tokens[0])) {
                return List.of();
            }
            // Get the documents corresponding to the first word
            final Map<Integer, List<Integer>> firstWordDocuments = this.invertedIndex.get(tokens[0]);
            // List to store the final result
            final List<Integer> matchingDocuments = new ArrayList<>();
            // Search in first word's documents
            for (int documentId : firstWordDocuments.keySet()) {
                // Get the positions of first word documents
                final List<Integer> firstWordPositions = firstWordDocuments.get(documentId);
                // Check for all positions
                for (int position : firstWordPositions) {
                    boolean isMatch = true;
                    // Check for all remaining tokens
                    for (int i = 1; i < tokens.length; i++) {
                        final String nextWord = tokens[i];
                        // Documents of next word
                        final Map<Integer, List<Integer>> nextWordDocuments = this.invertedIndex.get(nextWord);
                        if (nextWordDocuments == null || !nextWordDocuments.containsKey(documentId)) {
                            isMatch = false;
                            break;
                        }
                        // Check for all positions now
                        final List<Integer> nextWordPositions = nextWordDocuments.get(documentId);
                        if (!nextWordPositions.contains(position + i)) {
                            isMatch = false;
                            break;
                        }
                    }
                    if (isMatch) {
                        matchingDocuments.add(documentId);
                        break;
                    }
                }
            }
            return matchingDocuments;
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
