package org.redquark.leetcoding.slidingwindow;

public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        // Special case
        if (s1.length() > s2.length()) {
            return false;
        }
        // Arrays to keep track for frequencies of s1 and substrings
        // of same length of s2
        final int[] s1Frequencies = new int[26];
        final int[] s2Frequencies = new int[26];
        // Process for first window
        for (int i = 0; i < s1.length(); i++) {
            s1Frequencies[s1.charAt(i) - 'a']++;
            s2Frequencies[s2.charAt(i) - 'a']++;
        }
        // Left and right pointers of the sliding window
        int left = 0;
        int right = s1.length();
        // Process for remaining s2
        while (right < s2.length()) {
            if (matches(s1Frequencies, s2Frequencies)) {
                return true;
            }
            // Move our sliding window
            s2Frequencies[s2.charAt(left) - 'a']--;
            left++;
            s2Frequencies[s2.charAt(right) - 'a']++;
            right++;
        }
        return matches(s1Frequencies, s2Frequencies);
    }

    private boolean matches(int[] s1Frequencies, int[] s2Frequencies) {
        for (int i = 0; i < 26; i++) {
            if (s1Frequencies[i] != s2Frequencies[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final PermutationInString permutationInString = new PermutationInString();

        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(permutationInString.checkInclusion(s1, s2));

        s1 = "ab";
        s2 = "eidboaoo";
        System.out.println(permutationInString.checkInclusion(s1, s2));
    }
}
