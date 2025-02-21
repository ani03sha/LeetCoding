package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FindElementsInAContaminatedBinaryTree {

    static class FindElements {

        private final Set<Integer> elements;

        public FindElements(TreeNode root) {
            this.elements = new HashSet<>();
            if (root != null) {
                root.val = 0;
                this.elements.add(0);
                // Queue to perform BFS
                final Queue<TreeNode> nodes = new LinkedList<>();
                nodes.offer(root);
                while (!nodes.isEmpty()) {
                    final TreeNode node = nodes.remove();
                    if (node.left != null) {
                        node.left.val = 2 * node.val + 1;
                        nodes.offer(node.left);
                        this.elements.add(node.left.val);
                    }
                    if (node.right != null) {
                        node.right.val = 2 * node.val + 2;
                        nodes.offer(node.right);
                        this.elements.add(node.right.val);
                    }
                }
            }
        }

        public boolean find(int target) {
            return this.elements.contains(target);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-1);
        root.right = new TreeNode(-1);
        FindElements findElements = new FindElements(root);
        System.out.println(findElements.find(1));
        System.out.println(findElements.find(2));

        root = new TreeNode(-1);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(-1);
        root.left.left = new TreeNode(-1);
        root.left.right = new TreeNode(-1);
        findElements = new FindElements(root);
        System.out.println(findElements.find(1));
        System.out.println(findElements.find(3));
        System.out.println(findElements.find(5));
    }
}
