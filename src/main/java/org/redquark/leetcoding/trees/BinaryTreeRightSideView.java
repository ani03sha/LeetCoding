package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        // List to store the right side view
        final List<Integer> result = new ArrayList<>();
        // Special case
        if (root == null) {
            return result;
        }
        // Queue to perform BFS
        final Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        // Process all levels
        while (!nodes.isEmpty()) {
            final int size = nodes.size();
            for (int i = 0; i < size; i++) {
                final TreeNode node = nodes.remove();
                if (i == size - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    nodes.offer(node.left);
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final BinaryTreeRightSideView binaryTreeRightSideView = new BinaryTreeRightSideView();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(binaryTreeRightSideView.rightSideView(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        System.out.println(binaryTreeRightSideView.rightSideView(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(binaryTreeRightSideView.rightSideView(root));

        System.out.println(binaryTreeRightSideView.rightSideView(null));
    }
}
