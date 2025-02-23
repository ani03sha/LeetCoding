package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

    private int currentIndex = 0;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return constructTree(preorder, postorder, 0, preorder.length - 1);
    }

    private TreeNode constructTree(int[] preorder, int[] postorder, int left, int right) {
        // Base cases
        if (left > right) {
            return null;
        }
        currentIndex++;
        final TreeNode root = new TreeNode(postorder[right]);
        if (left == right) {
            return root;
        }
        int index = left;
        while (index <= right && postorder[index] != preorder[currentIndex]) {
            index++;
        }
        root.left = constructTree(preorder, postorder, left, index);
        root.right = constructTree(preorder, postorder, index + 1, right - 1);
        return root;
    }

    public static void main(String[] args) {
        final ConstructBinaryTreeFromPreorderAndPostorderTraversal constructBinaryTreeFromPreorderAndPostorderTraversal
                = new ConstructBinaryTreeFromPreorderAndPostorderTraversal();

        int[] preorder = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] postorder = new int[]{4, 5, 2, 6, 7, 3, 1};
        TreeNode root = constructBinaryTreeFromPreorderAndPostorderTraversal.constructFromPrePost(preorder, postorder);
        System.out.println(root.levelOrderTraversal(root));

        preorder = new int[]{1};
        postorder = new int[]{1};
        root = constructBinaryTreeFromPreorderAndPostorderTraversal.constructFromPrePost(preorder, postorder);
        System.out.println(root.levelOrderTraversal(root));
    }
}
