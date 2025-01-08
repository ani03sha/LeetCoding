package org.redquark.leetcoding.strings;

public class VerifyingAnAlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {
        // Special case
        if (words == null || words.length == 0 || order == null || order.length() == 0) {
            return false;
        }
        // Array to keep track of correct order
        final int[] mapper = new int[26];
        for (int i = 0; i < 26; i++) {
            mapper[order.charAt(i) - 'a'] = i;
        }
        // Take two words from the array at a time and determine if they are in
        // the incorrect order
        for (int i = 0; i < words.length - 1; i++) {
            if (isIncorrectOrder(words[i], words[i + 1], mapper)) {
                return false;
            }
        }
        return true;
    }

    private boolean isIncorrectOrder(String a, String b, int[] mapper) {
        final int m = a.length();
        final int n = b.length();
        for (int i = 0; i < m && i < n; i++) {
            if (a.charAt(i) - 'a' != b.charAt(i) - 'a') {
                return mapper[a.charAt(i) - 'a'] > mapper[b.charAt(i) - 'a'];
            }
        }
        return m > n;
    }

    public static void main(String[] args) {
        final VerifyingAnAlienDictionary verifyingAnAlienDictionary = new VerifyingAnAlienDictionary();

        String[] words = new String[]{"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(verifyingAnAlienDictionary.isAlienSorted(words, order));

        words = new String[]{"word", "world", "row"};
        order = "worldabcefghijkmnpqstuvxyz";
        System.out.println(verifyingAnAlienDictionary.isAlienSorted(words, order));

        words = new String[]{"apple", "app"};
        order = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(verifyingAnAlienDictionary.isAlienSorted(words, order));
    }
}
