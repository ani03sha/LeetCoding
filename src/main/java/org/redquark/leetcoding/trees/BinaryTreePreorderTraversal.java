package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        // List to store the final output
        final List<Integer> nodes = new ArrayList<>();
        preorderTraversal(root, nodes);
        return nodes;
    }

    private void preorderTraversal(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }
        nodes.add(root.val);
        preorderTraversal(root.left, nodes);
        preorderTraversal(root.right, nodes);
    }

    public static void main(String[] args) {
        final BinaryTreePreorderTraversal binaryTreeInorderTraversal = new BinaryTreePreorderTraversal();

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        System.out.println(binaryTreeInorderTraversal.preorderTraversal(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        System.out.println(binaryTreeInorderTraversal.preorderTraversal(root));
    }
}
