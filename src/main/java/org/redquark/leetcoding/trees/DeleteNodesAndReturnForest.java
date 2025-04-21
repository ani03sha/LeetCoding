package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturnForest {

    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        // Set for constant time lookup
        final Set<Integer> nodeValues = new HashSet<>();
        for (int item : toDelete) {
            nodeValues.add(item);
        }
        // List to store the forest
        final List<TreeNode> forest = new ArrayList<>();
        // Process the tree using postorder traversal
        root = postOrder(root, nodeValues, forest);
        if (root != null) {
            forest.add(root);
        }
        return forest;
    }

    private TreeNode postOrder(TreeNode node, Set<Integer> nodeValues, List<TreeNode> forest) {
        // Base case
        if (node == null) {
            return null;
        }
        node.left = postOrder(node.left, nodeValues, forest);
        node.right = postOrder(node.right, nodeValues, forest);
        // Check if the current node needs to be deleted
        if (nodeValues.contains(node.val)) {
            // If the node has left and right children, add them to the forest
            if (node.left != null) {
                forest.add(node.left);
            }
            if (node.right != null) {
                forest.add(node.right);
            }
            // Return null to its parent to delete the current node
            return null;
        }
        return node;
    }

    public static void main(String[] args) {
        final DeleteNodesAndReturnForest deleteNodesAndReturnForest = new DeleteNodesAndReturnForest();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        int[] toDelete = new int[]{3, 5};
        System.out.println(deleteNodesAndReturnForest.delNodes(root, toDelete));
    }
}
