package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class DeleteLeavesWithAGivenValue {

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        // Base case
        if (root == null) {
            return null;
        }
        // Recurse for left and right subtrees
        if (root.left != null) {
            root.left = removeLeafNodes(root.left, target);
        }
        if (root.right != null) {
            root.right = removeLeafNodes(root.right, target);
        }
        return root.left == root.right && root.val == target ? null : root;
    }

    public static void main(String[] args) {
        final DeleteLeavesWithAGivenValue deleteLeavesWithAGivenValue = new DeleteLeavesWithAGivenValue();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        int target = 2;
        root = deleteLeavesWithAGivenValue.removeLeafNodes(root, target);
        System.out.println(root.levelOrderTraversal(root));

        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        target = 3;
        root = deleteLeavesWithAGivenValue.removeLeafNodes(root, target);
        System.out.println(root.levelOrderTraversal(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(2);
        target = 2;
        root = deleteLeavesWithAGivenValue.removeLeafNodes(root, target);
        System.out.println(root.levelOrderTraversal(root));
    }
}
