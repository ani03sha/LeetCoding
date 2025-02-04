package org.redquark.leetcoding.dfs;

import java.util.ArrayList;
import java.util.List;

public class NestedListWeightSum {

    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int depthSum = 0;
        for (NestedInteger entry : nestedList) {
            if (entry.isInteger()) {
                depthSum += entry.getInteger() * depth;
            } else {
                depthSum += dfs(entry.getList(), depth + 1);
            }
        }
        return depthSum;
    }

    interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    static class NestedIntegerImpl implements NestedInteger {

        private Integer value;
        private List<NestedInteger> list;

        // Constructor initializes an empty nested list.
        public NestedIntegerImpl() {
            this.list = new ArrayList<>();
        }

        // Constructor initializes a single integer.
        public NestedIntegerImpl(int value) {
            this.value = value;
            this.list = null; // Indicates that this is a single integer
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return value != null;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return value;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
            this.value = value;
            this.list = null; // Clear the list since it's now an integer
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
            if (this.list == null) {
                this.list = new ArrayList<>();
                this.value = null; // Clear the integer since it's now a list
            }
            this.list.add(ni);
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return list != null ? list : new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        final NestedListWeightSum nestedListWeightSum = new NestedListWeightSum();

        List<NestedInteger> nestedList1 = new ArrayList<>();

        NestedInteger firstList = new NestedIntegerImpl();
        firstList.add(new NestedIntegerImpl(1));
        firstList.add(new NestedIntegerImpl(1));

        NestedInteger secondElement = new NestedIntegerImpl(2);

        NestedInteger thirdList = new NestedIntegerImpl();
        thirdList.add(new NestedIntegerImpl(1));
        thirdList.add(new NestedIntegerImpl(1));

        nestedList1.add(firstList);
        nestedList1.add(secondElement);
        nestedList1.add(thirdList);

        System.out.println(nestedListWeightSum.depthSum(nestedList1));

        List<NestedInteger> nestedList2 = new ArrayList<>();

        NestedInteger firstElement = new NestedIntegerImpl(1);

        NestedInteger innerList2 = new NestedIntegerImpl();
        innerList2.add(new NestedIntegerImpl(6));

        NestedInteger innerList1 = new NestedIntegerImpl();
        innerList1.add(new NestedIntegerImpl(4));
        innerList1.add(innerList2);

        nestedList2.add(firstElement);
        nestedList2.add(innerList1);

        System.out.println(nestedListWeightSum.depthSum(nestedList2));
    }
}
