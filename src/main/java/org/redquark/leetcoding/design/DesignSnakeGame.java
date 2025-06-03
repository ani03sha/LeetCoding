package org.redquark.leetcoding.design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class DesignSnakeGame {

    static class SnakeGame {

        private final int height;
        private final int width;
        private final int[][] food;
        private int score;
        private int foodIndex;
        private final Deque<Integer> snakeBody;
        private final Set<Integer> visited;

        public SnakeGame(int width, int height, int[][] food) {
            this.height = height;
            this.width = width;
            this.food = food;
            this.score = 0;
            this.foodIndex = 0;
            this.snakeBody = new ArrayDeque<>();
            this.visited = new HashSet<>();
            this.snakeBody.offer(0);
            this.visited.add(0);
        }

        public int move(String direction) {
            // Current head position
            final int head = this.snakeBody.peek();
            // Current row and column position
            int row = head / this.width;
            int column = head % this.width;
            // Update the position based on the direction
            switch (direction) {
                case "U" -> row--;
                case "D" -> row++;
                case "L" -> column--;
                case "R" -> column++;
            }
            // Check if the new direction is out of bounds
            if (row < 0 || row >= this.height || column < 0 || column >= this.width) {
                return -1;
            }
            // If the snake eats food
            if (this.foodIndex < this.food.length && row == this.food[this.foodIndex][0] && column == this.food[this.foodIndex][1]) {
                this.score++;
                this.foodIndex++;
            } // If not eating, move the tail
            else {
                final int tail = this.snakeBody.removeLast();
                this.visited.remove(tail);
            }
            // New head position
            final int newHead = row * this.width + column;
            // Check if snake bites itself
            if (this.visited.contains(newHead)) {
                return -1;
            }
            // Add the new head to snakeBody and mark it as visited
            this.snakeBody.offerFirst(newHead);
            this.visited.add(newHead);
            return this.score;
        }
    }

    public static void main(String[] args) {
        // Write test cases to validate the implementation
        int[][] food = {{1, 2}, {0, 1}, {0, 0}};
        SnakeGame snakeGame = new SnakeGame(3, 2, food);
        System.out.println(snakeGame.move("R")); // 0
        System.out.println(snakeGame.move("D")); // 0
        System.out.println(snakeGame.move("R")); // 1
        System.out.println(snakeGame.move("U")); // 1
        System.out.println(snakeGame.move("L")); // 2
        System.out.println(snakeGame.move("U")); // -1
    }
}
