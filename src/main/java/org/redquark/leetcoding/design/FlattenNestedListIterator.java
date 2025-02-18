package org.redquark.leetcoding.design;

import org.redquark.leetcoding.utils.NestedInteger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlattenNestedListIterator {

    static public class NestedIterator implements Iterator<Integer> {

        // List to store all the elements in the list
        private final List<Integer> elements;
        // Current iterator
        private final Iterator<Integer> current;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.elements = new ArrayList<>();
            dfs(nestedList);
            this.current = this.elements.iterator();
        }

        @Override
        public Integer next() {
            return current.next();
        }

        @Override
        public boolean hasNext() {
            return current.hasNext();
        }

        private void dfs(List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    this.elements.add(nestedInteger.getInteger());
                } else {
                    dfs(nestedInteger.getList());
                }
            }
        }
    }

    public static void main(String[] args) {
        final NestedInteger nestedInteger1 = new NestedInteger(1);
        final NestedInteger nestedInteger2 = new NestedInteger(1);
        final NestedInteger nestedInteger3 = new NestedInteger(2);
        final NestedInteger nestedInteger4 = new NestedInteger(1);
        final NestedInteger nestedInteger5 = new NestedInteger(1);
        final List<NestedInteger> nestedIntegers = new ArrayList<>();
        nestedIntegers.add(nestedInteger1);
        nestedIntegers.add(nestedInteger2);
        nestedIntegers.add(nestedInteger3);
        nestedIntegers.add(nestedInteger4);
        nestedIntegers.add(nestedInteger5);
        final NestedIterator nestedIterator = new NestedIterator(nestedIntegers);
        while (nestedIterator.hasNext()) {
            System.out.println(nestedIterator.next());
        }
    }
}
