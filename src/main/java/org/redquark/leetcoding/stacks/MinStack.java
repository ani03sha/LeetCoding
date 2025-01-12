package org.redquark.leetcoding.stacks;

public class MinStack {

    private StackNode head;

    public MinStack() {
    }

    public void push(int val) {
        if (this.head == null) {
            this.head = new StackNode(val, null, val);
        } else {
            this.head = new StackNode(val, this.head, Math.min(val, this.head.minValue));
        }
    }

    public void pop() {
        this.head = this.head.next;
    }

    public int top() {
        return this.head.val;
    }

    public int getMin() {
        return this.head.minValue;
    }

    static class StackNode {
        final int val;
        StackNode next;
        int minValue;

        StackNode(int val, StackNode next, int minValue) {
            this.val = val;
            this.next = next;
            this.minValue = minValue;
        }
    }

    public static void main(String[] args) {
        final MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
