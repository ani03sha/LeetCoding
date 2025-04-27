package org.redquark.leetcoding.trees;

import org.redquark.leetcoding.utils.TreeNodeWithParent;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorOfABinaryTreeIII {

    public TreeNodeWithParent lowestCommonAncestorIntuitive(TreeNodeWithParent p, TreeNodeWithParent q) {
        // Special case
        if (p == null || q == null) {
            throw new IllegalArgumentException("Invalid input!");
        }
        // Find the depths of both nodes from the given tree
        int pDepth = getDepth(p);
        int qDepth = getDepth(q);
        // Traverse the deeper node alone so that both nodes
        // are at the same level
        while (pDepth > qDepth) {
            p = p.parent;
            pDepth--;
        }
        while (qDepth > pDepth) {
            q = q.parent;
            qDepth--;
        }
        // Now, move both nodes upwards together until they reach
        // the common node; this will be the LCA
        while (p != q) {
            p = p.parent;
            q = q.parent;
        }
        return p;
    }

    private int getDepth(TreeNodeWithParent node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.parent;
        }
        return depth;
    }

    public TreeNodeWithParent lowestCommonAncestorWithSet(TreeNodeWithParent p, TreeNodeWithParent q) {
        // Set to store the nodes that come from p to q
        final Set<TreeNodeWithParent> pPath = new HashSet<>();
        while (p != null) {
            pPath.add(p);
            p = p.parent;
        }
        // Traverse the path from q to root
        while (q != null) {
            if (pPath.contains(q)) {
                return q;
            }
            q = q.parent;
        }
        return null;
    }

    public TreeNodeWithParent lowestCommonAncestorOptimal(TreeNodeWithParent p, TreeNodeWithParent q) {
        // Two pointers for traversing paths of p and q
        TreeNodeWithParent pRunner = p;
        TreeNodeWithParent qRunner = q;
        // It is given that the LCA exists
        while (pRunner != qRunner) {
            pRunner = pRunner == null ? q : p.parent;
            qRunner = qRunner == null ? q : q.parent;
        }
        return pRunner;
    }

    public static void main(String[] args) {
        final LowestCommonAncestorOfABinaryTreeIII lowestCommonAncestorOfABinaryTreeIII = new LowestCommonAncestorOfABinaryTreeIII();

        TreeNodeWithParent root = new TreeNodeWithParent(3, null);
        root.left = new TreeNodeWithParent(5, root);
        root.right = new TreeNodeWithParent(1, root);
        root.left.left = new TreeNodeWithParent(6, root.left);
        root.left.right = new TreeNodeWithParent(2, root.left);
        root.right.left = new TreeNodeWithParent(0, root.right);
        root.right.right = new TreeNodeWithParent(8, root.right);
        root.left.right.left = new TreeNodeWithParent(7, root.left.right);
        root.left.right.right = new TreeNodeWithParent(4, root.left.right);
        TreeNodeWithParent p = root.left;
        TreeNodeWithParent q = root.right;
        System.out.println(lowestCommonAncestorOfABinaryTreeIII.lowestCommonAncestorIntuitive(p, q).val);
        System.out.println(lowestCommonAncestorOfABinaryTreeIII.lowestCommonAncestorWithSet(p, q).val);
        System.out.println(lowestCommonAncestorOfABinaryTreeIII.lowestCommonAncestorOptimal(p, q).val);
    }
}
