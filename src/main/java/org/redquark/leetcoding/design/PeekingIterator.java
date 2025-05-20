package org.redquark.leetcoding.design;

import java.util.Iterator;
import java.util.List;

public class PeekingIterator implements Iterator<Integer> {

    // Reference to the iterator object
    private final Iterator<Integer> iterator;
    // Reference to the next element in the iterator
    private Integer next;
    // Flag to check if there are more elements in the iterator
    private boolean noMoreElements;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        // Advance iterator
        advanceIterator();
    }

    public Integer peek() {
        return this.next;
    }

    @Override
    public boolean hasNext() {
        return !noMoreElements;
    }

    @Override
    public Integer next() {
        final Integer next = this.next;
        advanceIterator();
        return next;
    }

    private void advanceIterator() {
        // If there are still elements left to traverse
        if (this.iterator.hasNext()) {
            this.next = this.iterator.next();
        } else {
            this.noMoreElements = true;
        }
    }

    public static void main(String[] args) {
        final Iterator<Integer> iterator = List.of(1, 2, 3).iterator();
        final PeekingIterator peekingIterator = new PeekingIterator(iterator);
        System.out.println(peekingIterator.next());    // return 1, the pointer moves to the next element [1,2,3].
        System.out.println(peekingIterator.peek());    // return 2, the pointer does not move [1,2,3].
        System.out.println(peekingIterator.next());    // return 2, the pointer moves to the next element [1,2,3]
        System.out.println(peekingIterator.next());    // return 3, the pointer moves to the next element [1,2,3]
        System.out.println(peekingIterator.hasNext()); // return False
    }
}
