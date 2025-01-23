package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        final int n = preorder.length;
        // Map to store values and their indices from the inorder
        final Map<Integer, Integer> mappings = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mappings.put(inorder[i], i);
        }
        // Perform the construction
        return constructTree(preorder, 0, 0, n, mappings);
    }

    private TreeNode constructTree(int[] preorder, int preorderStart, int inorderStart, int n, Map<Integer, Integer> mappings) {
        if (n <= 0) {
            return null;
        }
        // Fetching the root of the current subtree
        int currentRootValue = preorder[preorderStart];
        // Getting the index of current root value from the mappings
        int inorderRootIndex = mappings.get(currentRootValue);
        // Count the number of nodes in left subtree
        int leftSubtreeSize = inorderRootIndex - inorderStart;
        // Build the left subtree recursively
        final TreeNode left = constructTree(preorder, preorderStart + 1, inorderStart, leftSubtreeSize, mappings);
        // Build the right subtree recursively
        final TreeNode right = constructTree(preorder, preorderStart + 1 + leftSubtreeSize, inorderRootIndex + 1, n - leftSubtreeSize - 1, mappings);
        // Construct new node
        final TreeNode root = new TreeNode(currentRootValue);
        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {
        final ConstructBinaryTreeFromPreorderAndInorderTraversal constructBinaryTreeFromPreorderAndInorderTraversal = new ConstructBinaryTreeFromPreorderAndInorderTraversal();

        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        TreeNode root = constructBinaryTreeFromPreorderAndInorderTraversal.buildTree(preorder, inorder);
        System.out.println(root.levelOrderTraversal(root));

        preorder = new int[]{-1};
        inorder = new int[]{-1};
        root = constructBinaryTreeFromPreorderAndInorderTraversal.buildTree(preorder, inorder);
        System.out.println(root.levelOrderTraversal(root));
    }
}
