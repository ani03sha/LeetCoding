package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class MergeTwoBinaryTrees {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // Base cases
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        // Merge trees
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    public static void main(String[] args) {
        final MergeTwoBinaryTrees mergeTwoBinaryTrees = new MergeTwoBinaryTrees();

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);
        TreeNode mergedTree = mergeTwoBinaryTrees.mergeTrees(root1, root2);
        System.out.println(mergedTree.levelOrderTraversal(mergedTree));
    }
}
