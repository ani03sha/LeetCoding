package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNodeWithParent;

public class InorderSuccessorInBSTII {

    public TreeNodeWithParent inorderSuccessor(TreeNodeWithParent node) {
        // The successor node is somewhere lower in the right
        // subtree of the node
        if (node.right != null) {
            // Move right once
            node = node.right;
            // Move left as much as possible
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        // If the successor is somewhere upper in the tree
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    public static void main(String[] args) {
        final InorderSuccessorInBSTII inorderSuccessorInBSTII = new InorderSuccessorInBSTII();

        TreeNodeWithParent root = new TreeNodeWithParent(2);
        root.left = new TreeNodeWithParent(2);
        root.right = new TreeNodeWithParent(3);
        root.left.parent = root;
        root.right.parent = root;
        System.out.println(inorderSuccessorInBSTII.inorderSuccessor(root.left).val);

        root = new TreeNodeWithParent(5);
        root.left = new TreeNodeWithParent(3);
        root.right = new TreeNodeWithParent(6);
        root.left.left = new TreeNodeWithParent(2);
        root.left.right = new TreeNodeWithParent(4);
        root.left.left.left = new TreeNodeWithParent(1);
        root.left.parent = root;
        root.right.parent = root;
        root.left.left.parent = root.left;
        root.left.right.parent = root.left;
        root.left.left.left.parent = root.left.left;
        System.out.println(inorderSuccessorInBSTII.inorderSuccessor(root.right));
    }
}
