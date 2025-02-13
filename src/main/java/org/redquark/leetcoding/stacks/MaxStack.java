package org.redquark.leetcoding.stacks;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MaxStack {

    // Stack of elements represented by a doubly linked list
    private final DoublyLinkedList elements;
    // TreeMap to maintain the maximum element and its occurrences
    private final TreeMap<Integer, List<StackNode>> elementToNodeMappings;

    public MaxStack() {
        this.elements = new DoublyLinkedList();
        this.elementToNodeMappings = new TreeMap<>();
    }

    public void push(int x) {
        // Append the given element to the top of the stack
        final StackNode stackNode = this.elements.append(x);
        // Add this node to the TreeMap
        this.elementToNodeMappings.computeIfAbsent(x, _ -> new ArrayList<>()).add(stackNode);
    }

    public int pop() {
        // Pop the node from the stack
        final StackNode stackNode = this.elements.pop();
        // Get all nodes corresponding to stackNode's value
        List<StackNode> nodes = this.elementToNodeMappings.get(stackNode.value);
        // Remove the node reference from the list in TreeMap
        nodes.removeLast();
        if (nodes.isEmpty()) {
            this.elementToNodeMappings.remove(stackNode.value);
        }
        return stackNode.value;
    }

    public int top() {
        return this.elements.peek();
    }

    public int peekMax() {
        return this.elementToNodeMappings.lastKey();
    }

    public int popMax() {
        // Get the current maximum value
        final int maxValue = peekMax();
        // Get the corresponding list of nodes that have maxValue
        final List<StackNode> nodes = this.elementToNodeMappings.get(maxValue);
        // Remove the last node
        final StackNode node = nodes.removeLast();
        if (nodes.isEmpty()) {
            this.elementToNodeMappings.remove(maxValue);
        }
        // Remove the node from the list
        DoublyLinkedList.remove(node);
        return maxValue;
    }

    static class StackNode {
        int value;
        StackNode previous;
        StackNode next;

        StackNode() {
        }

        StackNode(int value) {
            this.value = value;
        }
    }

    static class DoublyLinkedList {
        // Dummy head and tail nodes
        private final StackNode head;
        private final StackNode tail;

        DoublyLinkedList() {
            this.head = new StackNode();
            this.tail = new StackNode();
            this.head.next = this.tail;
            this.tail.previous = this.head;
        }

        /**
         * Append a node with given value to the end of the list
         */
        StackNode append(int value) {
            final StackNode newNode = new StackNode(value);
            newNode.next = this.tail;
            newNode.previous = this.tail.previous;
            this.tail.previous.next = newNode;
            this.tail.previous = newNode;
            return newNode;
        }

        /**
         * Remove a given node from the list
         */
        static StackNode remove(StackNode node) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            return node;
        }

        /**
         * Pop the last node from the list
         */
        StackNode pop() {
            return remove(this.tail.previous);
        }

        /**
         * Peek the last node's value from the list
         */
        int peek() {
            return this.tail.previous.value;
        }
    }

    public static void main(String[] args) {
        final MaxStack maxStack = new MaxStack();

        maxStack.push(5);   // [5] the top of the stack and the maximum number is 5.
        maxStack.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
        maxStack.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
        System.out.println(maxStack.top());     // return 5, [5, 1, 5] the stack did not change.
        System.out.println(maxStack.popMax());  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
        System.out.println(maxStack.top());     // return 1, [5, 1] the stack did not change.
        System.out.println(maxStack.peekMax()); // return 5, [5, 1] the stack did not change.
        System.out.println(maxStack.pop());     // return 1, [5] the top of the stack and the max element is now 5.
        System.out.println(maxStack.top());     // return 5, [5] the stack did not change.
    }
}
