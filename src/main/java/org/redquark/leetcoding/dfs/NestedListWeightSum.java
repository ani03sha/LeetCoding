package org.redquark.leetcoding.dfs;

import org.redquark.leetcoding.utils.NestedInteger;

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

    public static void main(String[] args) {
        final NestedListWeightSum nestedListWeightSum = new NestedListWeightSum();

        List<NestedInteger> nestedList1 = new ArrayList<>();

        NestedInteger firstList = new NestedInteger();
        firstList.add(new NestedInteger(1));
        firstList.add(new NestedInteger(1));

        NestedInteger secondElement = new NestedInteger(2);

        NestedInteger thirdList = new NestedInteger();
        thirdList.add(new NestedInteger(1));
        thirdList.add(new NestedInteger(1));

        nestedList1.add(firstList);
        nestedList1.add(secondElement);
        nestedList1.add(thirdList);

        System.out.println(nestedListWeightSum.depthSum(nestedList1));

        List<NestedInteger> nestedList2 = new ArrayList<>();

        NestedInteger firstElement = new NestedInteger(1);

        NestedInteger innerList2 = new NestedInteger();
        innerList2.add(new NestedInteger(6));

        NestedInteger innerList1 = new NestedInteger();
        innerList1.add(new NestedInteger(4));
        innerList1.add(innerList2);

        nestedList2.add(firstElement);
        nestedList2.add(innerList1);

        System.out.println(nestedListWeightSum.depthSum(nestedList2));
    }
}
