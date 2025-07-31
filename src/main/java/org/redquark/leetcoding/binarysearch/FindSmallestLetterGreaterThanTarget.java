package org.redquark.leetcoding.binarysearch;

public class FindSmallestLetterGreaterThanTarget {

    public char nextGreatestLetter(char[] letters, char target) {
        final int n = letters.length;
        // Left and right pointers for binary search
        int left = 0;
        int right = n - 1;
        // Perform binary search on the range
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            if (letters[middle] <= target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return letters[left % n];
    }

    public static void main(String[] args) {
        final FindSmallestLetterGreaterThanTarget solution = new FindSmallestLetterGreaterThanTarget();
        char[] letters = {'c', 'f', 'j'};
        char target = 'a';
        System.out.println(solution.nextGreatestLetter(letters, target)); // Output: c
        target = 'c';
        System.out.println(solution.nextGreatestLetter(letters, target)); // Output: f
        target = 'd';
        System.out.println(solution.nextGreatestLetter(letters, target)); // Output: f
        target = 'g';
        System.out.println(solution.nextGreatestLetter(letters, target)); // Output: j
        target = 'j';
        System.out.println(solution.nextGreatestLetter(letters, target)); // Output: c
    }
}
