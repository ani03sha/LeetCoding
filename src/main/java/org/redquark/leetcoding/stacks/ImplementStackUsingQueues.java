package org.redquark.leetcoding.stacks;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {

    static class MyStack {

        private Queue<Integer> queueOne;
        private Queue<Integer> queueTwo;

        public MyStack() {
            this.queueOne = new LinkedList<>();
            this.queueTwo = new LinkedList<>();
        }

        public void push(int x) {
            queueOne.offer(x);
        }

        public int pop() {
            if (queueOne.isEmpty()) {
                throw new IndexOutOfBoundsException("Empty Stack");
            }
            while (queueOne.size() > 1) {
                queueTwo.offer(queueOne.remove());
            }
            // Pop the only remaining element
            int data = queueOne.remove();
            // Swap both queues
            Queue<Integer> temp = queueOne;
            queueOne = queueTwo;
            queueTwo = temp;
            return data;
        }

        public int top() {
            if (queueOne.isEmpty()) {
                throw new IndexOutOfBoundsException("Empty Stack");
            }
            while (queueOne.size() > 1) {
                queueTwo.offer(queueOne.remove());
            }
            // Pop the only remaining element
            int data = queueOne.remove();
            // Add this element to queueTwo
            queueTwo.offer(data);
            // Swap both queues
            Queue<Integer> temp = queueOne;
            queueOne = queueTwo;
            queueTwo = temp;
            return data;
        }

        public boolean empty() {
            return queueOne.isEmpty();
        }
    }

    public static void main(String[] args) {
        final MyStack myStack = new MyStack();

        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // return 2
        System.out.println(myStack.pop()); // return 2
        System.out.println(myStack.empty()); // return False
    }
}
