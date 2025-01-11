package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class BaseballGame {

    public int calPoints(String[] operations) {
        // Special case
        if (operations == null || operations.length == 0) {
            return 0;
        }
        // Stack to store the scores
        final Deque<Integer> scores = new ArrayDeque<>();
        // Process all operations
        for (String operation : operations) {
            switch (operation) {
                case "D" -> scores.push(2 * scores.peek());
                case "C" -> scores.pop();
                case "+" -> {
                    int last = scores.pop();
                    int sum = last + scores.peek();
                    scores.push(last);
                    scores.push(sum);
                }
                default -> {
                    int value = Integer.parseInt(operation);
                    scores.push(value);
                }
            }
        }
        // Calculate total score in the stack
        int totalScore = 0;
        for (int score : scores) {
            totalScore += score;
        }
        return totalScore;
    }

    public static void main(String[] args) {
        final BaseballGame baseballGame = new BaseballGame();

        String[] operations = new String[]{"5", "2", "C", "D", "+"};
        System.out.println(baseballGame.calPoints(operations));

        operations = new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"};
        System.out.println(baseballGame.calPoints(operations));

        operations = new String[]{"1", "C"};
        System.out.println(baseballGame.calPoints(operations));
    }
}
