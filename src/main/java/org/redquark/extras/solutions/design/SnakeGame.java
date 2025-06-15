package org.redquark.extras.solutions.design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class SnakeGame {

    // Size of the board (n * n)
    private final int size;
    // Body of the snake
    private final Deque<Integer> snakeBody;
    // Cells occupied by snake
    private final Set<Integer> visited;
    // Count of the moves
    private int moveCount;
    // Flag to see if the game is over
    private boolean gameOver;

    public SnakeGame(int size) {
        this.size = size;
        this.snakeBody = new ArrayDeque<>();
        this.visited = new HashSet<>();
        // Initialize snake of length 3 at top-left corner
        // moving right
        for (int i = 2; i >= 0; i--) {
            this.snakeBody.offer(i);
            this.visited.add(i);
        }
    }

    public void moveSnake(String direction) {
        // Get the current head of the snake
        final int head = this.snakeBody.peek();
        // Row and column of the snake
        int row = head / this.size;
        int column = head % this.size;
        // Make a move based on the direction
        switch (direction) {
            case "U" -> row--;
            case "D" -> row++;
            case "L" -> column--;
            case "R" -> column++;
        }
        // Check if the snake is out of bounds after moving
        if (row < 0 || row >= this.size || column < 0 || column < this.size) {
            this.gameOver = true;
            return;
        }
        // New head of the snake
        final int newHead = row * this.size + column;
        // Remove tail temporarily
        final int tail = this.snakeBody.peekLast();
        this.visited.remove(tail);
        // Check if the snake has bitten itself
        if (this.visited.contains(newHead)) {
            this.gameOver = true;
            return;
        }
        // Add new head
        this.snakeBody.offerFirst(newHead);
        this.visited.add(newHead);
        this.moveCount++;
        // Check if we need to grow
        if (this.moveCount % 5 == 0) {
            this.visited.add(tail);
        } else {
            // Remove tail
            this.snakeBody.remove(tail);
        }
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame(5); // 5x5 board

        System.out.println("Initial game state:");
        System.out.println("Game over? " + game.isGameOver());

        String[] moves = {"R", "R", "D", "D", "L", "L", "U", "U", "R", "D"};
        for (int i = 0; i < moves.length; i++) {
            System.out.println("Move " + (i + 1) + ": " + moves[i]);
            game.moveSnake(moves[i]);
            System.out.println("Game over? " + game.isGameOver());
            if (game.isGameOver()) {
                System.out.println("Game ended after move " + (i + 1));
                break;
            }
        }

        // Test boundary case
        SnakeGame game2 = new SnakeGame(3);
        System.out.println("\n--- New game to test boundary ---");
        game2.moveSnake("U");
        System.out.println("Move U - Game over? " + game2.isGameOver()); // should be true (hits wall)

        // Test self-collision
        SnakeGame game3 = new SnakeGame(4);
        System.out.println("\n--- New game to test self-collision ---");
        game3.moveSnake("R"); // 1
        game3.moveSnake("D"); // 2
        game3.moveSnake("L"); // 3
        game3.moveSnake("U"); // 4
        game3.moveSnake("R"); // 5 (grows here)
        game3.moveSnake("D"); // 6
        game3.moveSnake("L"); // 7
        game3.moveSnake("U"); // 8 - hits itself
        System.out.println("Game over after U? " + game3.isGameOver()); // true
    }
}
