package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class InorderSuccessorInBST {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // Successor node
        TreeNode successor = null;
        // Traverse until root is not null
        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }

    public static void main(String[] args) {
        final InorderSuccessorInBST inorderSuccessorInBST = new InorderSuccessorInBST();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(inorderSuccessorInBST.inorderSuccessor(root, root.left).val);

        root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        System.out.println(inorderSuccessorInBST.inorderSuccessor(root, root.right));
    }
}
