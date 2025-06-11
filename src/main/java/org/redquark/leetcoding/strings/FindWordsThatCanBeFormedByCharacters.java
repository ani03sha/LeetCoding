package org.redquark.leetcoding.strings;

public class FindWordsThatCanBeFormedByCharacters {

    public int countCharacters(String[] words, String chars) {
        int[] available = getCharCounts(chars);
        int totalLength = 0;
        for (String word : words) {
            int[] wordCount = getCharCounts(word);
            if (canForm(wordCount, available)) {
                totalLength += word.length();
            }
        }
        return totalLength;
    }

    private int[] getCharCounts(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        return count;
    }

    private boolean canForm(int[] wordCount, int[] available) {
        for (int i = 0; i < 26; i++) {
            if (wordCount[i] > available[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        final FindWordsThatCanBeFormedByCharacters findWordsThatCanBeFormedByCharacters = new FindWordsThatCanBeFormedByCharacters();

        String[] words = new String[]{"cat", "bt", "hat", "tree"};
        String chars = "atach";
        int result = findWordsThatCanBeFormedByCharacters.countCharacters(words, chars);
        System.out.println("Total length of words that can be formed: " + result);

        words = new String[]{"hello", "world", "leetcode"};
        chars = "welldonehoneyr";
        result = findWordsThatCanBeFormedByCharacters.countCharacters(words, chars);
        System.out.println("Total length of words that can be formed: " + result);
    }
}
