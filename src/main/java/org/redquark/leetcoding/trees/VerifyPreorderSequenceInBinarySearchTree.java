package org.redquark.leetcoding.trees;

public class VerifyPreorderSequenceInBinarySearchTree {

    public boolean verifyPreorder(int[] preorder) {
        return dfs(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean dfs(int[] preorder, int start, int end, int minValue, int maxValue) {
        if (start > end) {
            return true;
        }
        // Value at the root of the current tree or subtree
        int val = preorder[start];
        // If value is out of range
        if (val <= minValue || val >= maxValue) {
            return false;
        }
        int index = start + 1;
        // Find first right child of the root
        while (index <= end) {
            if (preorder[index] >= val) {
                break;
            }
            index++;
        }
        return dfs(preorder, start + 1, index - 1, minValue, val)
                && dfs(preorder, index, end, val, maxValue);
    }

    public static void main(String[] args) {
        final VerifyPreorderSequenceInBinarySearchTree verifyPreorderSequenceInBinarySearchTree =
                new VerifyPreorderSequenceInBinarySearchTree();

        int[] preorder = new int[]{5, 2, 1, 3, 6};
        System.out.println(verifyPreorderSequenceInBinarySearchTree.verifyPreorder(preorder));

        preorder = new int[]{5, 2, 6, 1, 3};
        System.out.println(verifyPreorderSequenceInBinarySearchTree.verifyPreorder(preorder));
    }
}
