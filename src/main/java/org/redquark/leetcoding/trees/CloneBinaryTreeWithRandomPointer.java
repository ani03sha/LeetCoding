package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNodeWithRandom;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CloneBinaryTreeWithRandomPointer {

    public TreeNodeWithRandom copyRandomBinaryTree(TreeNodeWithRandom root) {
        // Base case
        if (root == null) {
            return null;
        }
        // Map to original node and copy node
        final Map<TreeNodeWithRandom, TreeNodeWithRandom> mappings = new HashMap<>();
        // Queue to perform BFS
        final Queue<TreeNodeWithRandom> nodes = new LinkedList<>();
        nodes.offer(root);
        mappings.put(root, new TreeNodeWithRandom(root.val));
        // Perform BFS
        while (!nodes.isEmpty()) {
            // Get the references of the original node and copy node
            final TreeNodeWithRandom node = nodes.remove();
            final TreeNodeWithRandom copyNode = mappings.get(node);
            // Process left, right, and random references one by one
            if (node.left != null) {
                if (!mappings.containsKey(node.left)) {
                    nodes.offer(node.left);
                    mappings.put(node.left, new TreeNodeWithRandom(node.left.val));
                }
                copyNode.left = mappings.get(node.left);
            }
            if (node.right != null) {
                if (!mappings.containsKey(node.right)) {
                    nodes.offer(node.right);
                    mappings.put(node.right, new TreeNodeWithRandom(node.right.val));
                }
                copyNode.right = mappings.get(node.right);
            }
            if (node.random != null) {
                if (!mappings.containsKey(node.random)) {
                    nodes.offer(node.random);
                    mappings.put(node.random, new TreeNodeWithRandom(node.random.val));
                }
                copyNode.random = mappings.get(node.random);
            }
        }
        return mappings.get(root);
    }

    public static void main(String[] args) {
        final CloneBinaryTreeWithRandomPointer cloneBinaryTreeWithRandomPointer = new CloneBinaryTreeWithRandomPointer();
        // Construct the original tree
        TreeNodeWithRandom root = new TreeNodeWithRandom(1);
        root.left = new TreeNodeWithRandom(2);
        root.right = new TreeNodeWithRandom(3);
        root.left.left = new TreeNodeWithRandom(4);
        root.left.right = new TreeNodeWithRandom(5);

        // Set random pointers
        root.random = root.left.right; // 1's random -> 5
        root.left.random = root.right; // 2's random -> 3
        root.right.random = root.left; // 3's random -> 2
        root.left.left.random = root;  // 4's random -> 1
        root.left.right.random = root.left.left; // 5's random -> 4

        // Perform deep copy using your corrected method
        TreeNodeWithRandom copiedRoot = cloneBinaryTreeWithRandomPointer.copyRandomBinaryTree(root);

        // Print original tree level-order
        System.out.println("Original tree (level-order): " + TreeNodeWithRandom.levelOrderTraversal(root));

        // Print copied tree level-order
        System.out.println("Copied tree (level-order): " + TreeNodeWithRandom.levelOrderTraversal(copiedRoot));

        // Print random links for both trees (for validation)
        System.out.println("\nOriginal Random Links:");
        TreeNodeWithRandom.printRandomLinks(root);
        System.out.println("\nCopied Random Links:");
        TreeNodeWithRandom.printRandomLinks(copiedRoot);
    }
}
