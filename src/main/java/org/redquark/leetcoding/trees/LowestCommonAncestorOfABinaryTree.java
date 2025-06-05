package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Special case
        if (root == null || root == p || root == q) {
            return root;
        }
        // Recursively call the function for left and right subtrees
        final TreeNode left = lowestCommonAncestor(root.left, p, q);
        final TreeNode right = lowestCommonAncestor(root.right, p, q);
        // Check if we have found the given nodes
        if ((left == p && right == q) || (left == q && right == p)) {
            return root;
        }
        return left != null ? left : right;
    }

    private TreeNode lca;

    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return lca;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        // If we have reached to the leaf node
        if (root == null) {
            return false;
        }
        // Recurse left and right subtrees
        final boolean left = dfs(root.left, p, q);
        final boolean right = dfs(root.right, p, q);
        // If the current node is either p or q
        final boolean middle = root == p || root == q;
        // If any of the two flags are true, we have found the LCA
        if ((left && right) || (middle && (left || right))) {
            this.lca = root;
        }
        return left || right || middle;
    }

    public TreeNode lowestCommonAncestorWithParentPointer(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree using stack
        final Deque<TreeNode> stack = new ArrayDeque<>();
        // Map to store node as a key and its parent as value
        final Map<TreeNode, TreeNode> childParentMapping = new HashMap<>();
        stack.push(root);
        childParentMapping.put(root, null);
        // Traverse the tree until we find both p and q
        while (!childParentMapping.containsKey(p) || !childParentMapping.containsKey(q)) {
            // Get the current node
            final TreeNode current = stack.pop();
            if (current.left != null) {
                childParentMapping.put(current.left, current);
                stack.push(current.left);
            }
            if (current.right != null) {
                childParentMapping.put(current.right, current);
                stack.push(current.right);
            }
        }
        // Set to store ancestors of p and q, starting with p
        final Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = childParentMapping.get(p);
        }
        // Traverse similarly for q and stop at the first node that
        // is present in ancestors
        while (!ancestors.contains(q)) {
            q = childParentMapping.get(q);
        }
        return q;
    }

    public static void main(String[] args) {
        final LowestCommonAncestorOfABinaryTree lowestCommonAncestorOfABinaryTree = new LowestCommonAncestorOfABinaryTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        TreeNode p = root.left;
        TreeNode q = root.right;
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestorRecursive(root, p, q).val);
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestorWithParentPointer(root, p, q).val);
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestor(root, p, q).val);

        p = root.left;
        q = root.left.right.right;
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestorRecursive(root, p, q).val);
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestorWithParentPointer(root, p, q).val);
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestor(root, p, q).val);

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        p = root;
        q = root.left;
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestorRecursive(root, p, q).val);
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestorWithParentPointer(root, p, q).val);
        System.out.println(lowestCommonAncestorOfABinaryTree.lowestCommonAncestor(root, p, q).val);
    }
}
