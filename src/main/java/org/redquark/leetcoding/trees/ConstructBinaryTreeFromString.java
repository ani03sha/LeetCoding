package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ConstructBinaryTreeFromString {

    public TreeNode str2tree(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return null;
        }
        // Stack to store nodes of the tree
        final Deque<TreeNode> nodes = new ArrayDeque<>();
        // Index to traverse the string
        int index = 0;
        // Traverse the string
        while (index < s.length()) {
            // Get the current character
            final char c = s.charAt(index);
            if (c == ')') {
                nodes.pop();
                index++;
            } else if (c == '(') {
                index++;
            } else {
                boolean isNegative = false;
                if (s.charAt(index) == '-') {
                    index++;
                    isNegative = true;
                }
                // Value to be stored in the node
                int value = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    value = value * 10 + s.charAt(index) - '0';
                    index++;
                }
                final TreeNode current = new TreeNode(isNegative ? -value : value);
                // Add left or right children
                if (!nodes.isEmpty()) {
                    // Parent node
                    final TreeNode parent = nodes.peek();
                    if (parent.left == null) {
                        parent.left = current;
                    } else {
                        parent.right = current;
                    }
                }
                nodes.push(current);
            }
        }
        return nodes.isEmpty() ? null : nodes.peekLast();
    }

    public static void main(String[] args) {
        final ConstructBinaryTreeFromString constructBinaryTreeFromString = new ConstructBinaryTreeFromString();

        String s = "4(2(3)(1))(6(5))";
        TreeNode root = constructBinaryTreeFromString.str2tree(s);
        System.out.println(root.levelOrderTraversal(root));

        s = "4(2(3)(1))(6(5)(7))";
        root = constructBinaryTreeFromString.str2tree(s);
        System.out.println(root.levelOrderTraversal(root));

        s = "-4(2(3)(1))(6(5)(7))";
        root = constructBinaryTreeFromString.str2tree(s);
        System.out.println(root.levelOrderTraversal(root));
    }
}
