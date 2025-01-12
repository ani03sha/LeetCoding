package org.redquark.leetcoding.binarysearch;

public class GuessNumberHigherOrLower {

    private int pick = 0;

    public int guessNumber(int n) {
        // Left and right pointers
        int left = 1;
        int right = n;
        // Process array from both ends
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            if (guess(middle) == 0) {
                return middle;
            } else if (guess(middle) == -1) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        throw new IllegalArgumentException("Should never reach here!");
    }

    private int guess(int number) {
        return Integer.compare(pick, number);
    }

    public static void main(String[] args) {
        final GuessNumberHigherOrLower guessNumberHigherOrLower = new GuessNumberHigherOrLower();

        int n = 10;
        guessNumberHigherOrLower.pick = 6;
        System.out.println(guessNumberHigherOrLower.guessNumber(n));

        n = 1;
        guessNumberHigherOrLower.pick = 1;
        System.out.println(guessNumberHigherOrLower.guessNumber(n));

        n = 2;
        guessNumberHigherOrLower.pick = 1;
        System.out.println(guessNumberHigherOrLower.guessNumber(n));
    }
}
