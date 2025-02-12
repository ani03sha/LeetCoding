package org.redquark.leetcoding.dfs;

import org.redquark.leetcoding.utils.NestedInteger;

import java.util.ArrayList;
import java.util.List;

public class NestedListWeightSumII {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        // First find the depth of the nested list
        int maxDepth = getMaxDepth(nestedList);
        // Now, perform inverse DFS on this depth
        return inverseDFS(nestedList, maxDepth);
    }

    private int inverseDFS(List<NestedInteger> nestedList, int maxDepth) {
        int depthSum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                depthSum += nestedInteger.getInteger() * maxDepth;
            } else {
                depthSum += inverseDFS(nestedInteger.getList(), maxDepth - 1);
            }
        }
        return depthSum;
    }

    private int getMaxDepth(List<NestedInteger> nestedList) {
        int depth = 1;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                continue;
            }
            depth = Math.max(depth, 1 + getMaxDepth(nestedInteger.getList()));
        }
        return depth;
    }

    public static void main(String[] args) {
        final NestedListWeightSumII nestedListWeightSumII = new NestedListWeightSumII();

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

        System.out.println(nestedListWeightSumII.depthSumInverse(nestedList1));

        List<NestedInteger> nestedList2 = new ArrayList<>();

        NestedInteger firstElement = new NestedInteger(1);

        NestedInteger innerList2 = new NestedInteger();
        innerList2.add(new NestedInteger(6));

        NestedInteger innerList1 = new NestedInteger();
        innerList1.add(new NestedInteger(4));
        innerList1.add(innerList2);

        nestedList2.add(firstElement);
        nestedList2.add(innerList1);

        System.out.println(nestedListWeightSumII.depthSumInverse(nestedList2));
    }
}
