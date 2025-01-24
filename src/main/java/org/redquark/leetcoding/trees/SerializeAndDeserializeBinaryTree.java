package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {

    static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            final StringBuilder serializedTree = new StringBuilder();
            return serialize(root, serializedTree).toString();
        }

        private StringBuilder serialize(TreeNode root, StringBuilder serializedTree) {
            if (root==null) {
                return serializedTree.append("null");
            }
            serializedTree.append(root.val).append(",");
            serialize(root.left, serializedTree).append(",");
            serialize(root.right, serializedTree);
            return serializedTree;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            // Base case
            if (data == null || data.isEmpty()) {
                return null;
            }
            // Split the string to get nodes
            final String[] dataSplit = data.split(",");
            // Create a queue from these nodes
            final Queue<String> nodes = new ArrayDeque<>(Arrays.asList(dataSplit));
            return deserialize(nodes);
        }

        private TreeNode deserialize(Queue<String> nodes) {
            final String val = nodes.remove();
            if (val.equals("null")) {
                return null;
            }
            final TreeNode root = new TreeNode(Integer.parseInt(val));
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
