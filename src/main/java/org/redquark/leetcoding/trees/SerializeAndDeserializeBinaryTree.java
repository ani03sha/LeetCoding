package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {

    static public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            final StringBuilder serializedTree = new StringBuilder();
            serialize(root, serializedTree);
            return serializedTree.toString();
        }

        private void serialize(TreeNode root, StringBuilder serializedTree) {
            if (root == null) {
                serializedTree.append("null");
                return;
            }
            // Perform preorder traversal of tree
            // 1. Add the current node's value to the tree
            serializedTree.append(root.val);
            serializedTree.append(", ");
            // 2. Traverse the left child of the tree
            serialize(root.left, serializedTree);
            serializedTree.append(", ");
            // 3. Traverse the right child of the tree
            serialize(root.right, serializedTree);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            // Base case
            if (data == null || data.isEmpty()) {
                return null;
            }
            // Get all nodes of the tree
            final String[] nodeArray = data.split(", ");
            // Create a queue from these nodes
            final Queue<String> nodes = new ArrayDeque<>(Arrays.asList(nodeArray));
            // Perform deserialization
            return deserialize(nodes);
        }

        private TreeNode deserialize(Queue<String> nodes) {
            // Get current node's value
            final String value = nodes.remove();
            // Special case
            if (value.equals("null")) {
                return null;
            }
            final TreeNode root = new TreeNode(Integer.parseInt(value));
            root.left = deserialize(nodes);
            root.right = deserialize(nodes);
            return root;
        }
    }

    public static void main(String[] args) {
        final Codec codec = new Codec();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String serializedTree = codec.serialize(root);
        System.out.println("Serialized Tree: " + serializedTree);
        TreeNode deserializedTree = codec.deserialize(serializedTree);
        System.out.println("Deserialized Tree: " + deserializedTree.levelOrderTraversal(deserializedTree));
    }
}
