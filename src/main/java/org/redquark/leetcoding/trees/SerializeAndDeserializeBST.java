package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBST {

    static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            // StringBuilder to store the serialized version of the tree
            final StringBuilder serializedTree = new StringBuilder();
            // Perform serialization using DFS
            serialize(root, serializedTree);
            return serializedTree.toString();
        }

        private void serialize(TreeNode root, StringBuilder serializedTree) {
            if (root == null) {
                return;
            }
            serializedTree.append(root.val).append(",");
            serialize(root.left, serializedTree);
            serialize(root.right, serializedTree);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            // Base case
            if (data.isEmpty()) {
                return null;
            }
            // Queue to perform BFS
            final Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
            return deserialize(nodes, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private TreeNode deserialize(Queue<String> nodes, int minValue, int maxValue) {
            // Base case - if there are no nodes left to process
            if (nodes.isEmpty()) {
                return null;
            }
            // Get the node at the front of the queue
            final String node = nodes.peek();
            final int val = Integer.parseInt(node);
            // Check for the validity of node's value
            if (val < minValue || val > maxValue) {
                return null;
            }
            // Now, remove the node
            nodes.remove();
            // Create root node of the tree
            final TreeNode root = new TreeNode(val);
            // Values in the left subtree cannot be more than val
            root.left = deserialize(nodes, minValue, val);
            // Values in the right subtree cannot be more than val
            root.right = deserialize(nodes, val, maxValue);
            return root;
        }
    }

    public static void main(String[] args) {
        final  Codec codec = new Codec();

        TreeNode root = new TreeNode(2);
        root.left  =new TreeNode(1);
        root.right=new TreeNode(3);
        String serializedTree = codec.serialize(root);
        System.out.println("Serialized Tree: " + serializedTree);
        TreeNode deserializedTree = codec.deserialize(serializedTree);
        System.out.println("Deserialized Tree: " + deserializedTree.levelOrderTraversal(deserializedTree));
    }
}
