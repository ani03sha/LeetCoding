package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        // List to store postorder traversal
        final List<Integer> nodes = new ArrayList<>();
        postorderTraversal(root, nodes);
        return nodes;
    }

    private void postorderTraversal(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }
        postorderTraversal(root.left, nodes);
        postorderTraversal(root.right, nodes);
        nodes.add(root.val);
    }

    public static void main(String[] args) {
        final BinaryTreePostorderTraversal binaryTreePostorderTraversal = new BinaryTreePostorderTraversal();

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        System.out.println(binaryTreePostorderTraversal.postorderTraversal(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        System.out.println(binaryTreePostorderTraversal.postorderTraversal(root));
    }
}
