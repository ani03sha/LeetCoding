package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

public class BinaryTreeCameras {

    // Number of cameras required
    private int requiredCameras = 0;

    public int minCameraCover(TreeNode root) {
        return dfs(root) == -1 ? requiredCameras + 1 : requiredCameras;
    }

    // -1 => Node is not monitored
    // 0 => Node is monitored
    // 1 => Node has camera
    private int dfs(TreeNode root) {
        // Base case
        if (root == null) {
            return 0;
        }
        // Get the status of monitoring on left and right nodes
        final int left = dfs(root.left);
        final int right = dfs(root.right);
        // If either node is not monitored, we need a camera here
        if (left == -1 || right == -1) {
            requiredCameras++;
            return 1;
        }
        // If either node has the camera
        if (left == 1 || right == 1) {
            return 0;
        }
        // If the node is already monitored
        return -1;
    }

    public static void main(String[] args) {
        final BinaryTreeCameras binaryTreeCameras = new BinaryTreeCameras();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        System.out.println(binaryTreeCameras.minCameraCover(root));

        binaryTreeCameras.requiredCameras = 0;

        root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(1);
        root.left.left.left.right = new TreeNode(1);
        System.out.println(binaryTreeCameras.minCameraCover(root));
    }
}
