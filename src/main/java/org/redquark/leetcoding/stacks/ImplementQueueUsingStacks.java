package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class ImplementQueueUsingStacks {

    static class MyQueue {

        private final Deque<Integer> stackOne;
        private final Deque<Integer> stackTwo;

        public MyQueue() {
            this.stackOne = new ArrayDeque<>();
            this.stackTwo = new ArrayDeque<>();
        }

        public void push(int x) {
            stackOne.push(x);
        }

        public int pop() {
            shift();
            return stackTwo.pop();
        }

        public int peek() {
            shift();
            return stackTwo.peek();
        }

        public boolean empty() {
            return stackOne.isEmpty() && stackTwo.isEmpty();
        }

        private void shift() {
            if (stackTwo.isEmpty()) {
                while (!stackOne.isEmpty()) {
                    stackTwo.push(stackOne.pop());
                }
            }
        }
    }

    public static void main(String[] args) {
        final MyQueue myQueue = new MyQueue();

        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
    }
}
