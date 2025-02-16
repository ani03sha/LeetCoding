package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchIterator {

    static class BSTIterator {

        // Stack to keep track of nodes in the tree
        private final Deque<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            this.stack = new ArrayDeque<>();
            // Reference of the current node
            TreeNode current = root;
            // Add all left children to the tree
            while (current != null) {
                this.stack.push(current);
                if (current.left != null) {
                    current = current.left;
                } else {
                    break;
                }
            }
        }

        public int next() {
            // Get the node at the top of the stack
            final TreeNode top = this.stack.pop();
            // Reference to the top node
            TreeNode current = top;
            // Add all right children to the stack
            if (current.right != null) {
                current = current.right;
                while (current != null) {
                    // Add this node to the stack
                    this.stack.push(current);
                    // Traverse the left child of this node to maintain
                    // the sorted order
                    if (current.left != null) {
                        current = current.left;
                    } else {
                        break;
                    }
                }
            }
            return top.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        final TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        final BSTIterator bstIterator = new BSTIterator(root);

        System.out.println(bstIterator.next());    // return 3
        System.out.println(bstIterator.next());    // return 7
        System.out.println(bstIterator.hasNext()); // return True
        System.out.println(bstIterator.next());    // return 9
        System.out.println(bstIterator.hasNext()); // return True
        System.out.println(bstIterator.next());    // return 15
        System.out.println(bstIterator.hasNext()); // return True
        System.out.println(bstIterator.next());    // return 20
        System.out.println(bstIterator.hasNext()); // return False
    }
}
