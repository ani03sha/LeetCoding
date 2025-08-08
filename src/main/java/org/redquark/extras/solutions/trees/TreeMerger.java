package org.redquark.extras.solutions.trees;

import org.redquark.extras.solutions.utils.TreeNodeWithKey;

public class TreeMerger {

    public TreeNodeWithKey mergeTrees(TreeNodeWithKey tree1, TreeNodeWithKey tree2) {
        // Edge cases
        if (tree1 == null) {
            return tree2;
        }
        if (tree2 == null) {
            return tree1;
        }
        // Sum the values of the current nodes
        tree1.value += tree2.value;
        // Process all children from tree2
        for (TreeNodeWithKey child2 : tree2.children) {
            final TreeNodeWithKey existingChild = findChild(tree1, child2.key);
            if (existingChild != null) {
                // Child exists in tree1, merge it
                mergeTrees(existingChild, child2);
            } else {
                // Child does not exist in tree1, add it
                final TreeNodeWithKey newChild = cloneSubtree(child2);
                tree1.addChild(newChild);
            }
        }
        return tree1;
    }

    private TreeNodeWithKey cloneSubtree(TreeNodeWithKey node) {
        if (node == null) {
            return null;
        }
        final  TreeNodeWithKey clone = new TreeNodeWithKey(node.key, node.value);
        for (TreeNodeWithKey child : node.children) {
            clone.children.add(cloneSubtree(child));
        }
        return clone;
    }

    private TreeNodeWithKey findChild(TreeNodeWithKey tree, String key) {
        for (TreeNodeWithKey child : tree.children) {
            if (child.key.equals(key)) {
                return child;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        final TreeMerger treeMerger = new TreeMerger();

        // Build tree1 as shown in the input
        // Root:10 with children A:2, B:2, M:6
        // A:2 has child L:2
        // B:2 has child L:2 (to match expected output where B:5 has L:2)
        TreeNodeWithKey tree1 = new TreeNodeWithKey("Root", 10);
        TreeNodeWithKey a1 = new TreeNodeWithKey("A", 2);
        TreeNodeWithKey b1 = new TreeNodeWithKey("B", 2);
        TreeNodeWithKey m1 = new TreeNodeWithKey("M", 6);
        TreeNodeWithKey l1 = new TreeNodeWithKey("L", 2);
        TreeNodeWithKey l1_b = new TreeNodeWithKey("L", 2); // L under B in tree1
        tree1.addChild(a1);
        tree1.addChild(b1);
        tree1.addChild(m1);
        a1.addChild(l1);
        b1.addChild(l1_b);

        // Build tree2 as shown in the target
        // Root:13 with children A:4, B:3, D:6
        // A:4 has child L:4
        // D:6 has child M:6
        TreeNodeWithKey tree2 = new TreeNodeWithKey("Root", 13);
        TreeNodeWithKey a2 = new TreeNodeWithKey("A", 4);
        TreeNodeWithKey b2 = new TreeNodeWithKey("B", 3);
        TreeNodeWithKey d2 = new TreeNodeWithKey("D", 6);
        TreeNodeWithKey l2 = new TreeNodeWithKey("L", 4);
        TreeNodeWithKey m2 = new TreeNodeWithKey("M", 6);
        tree2.addChild(a2);
        tree2.addChild(b2);
        tree2.addChild(d2);
        a2.addChild(l2);
        d2.addChild(m2);

        // Print original trees
        System.out.println("Tree1:");
        printTree(tree1, 0);
        System.out.println("\nTree2:");
        printTree(tree2, 0);

        // Merge the trees
        TreeNodeWithKey mergedTree = treeMerger.mergeTrees(tree1, tree2);

        // Print the merged tree
        System.out.println("\nMerged Tree:");
        printTree(mergedTree, 0);
    }

    private static void printTree(TreeNodeWithKey mergedTree, int i) {
        if (mergedTree == null) {
            return;
        }
        // Print the current node
        System.out.println("  ".repeat(i) + mergedTree);
        // Print all children
        for (TreeNodeWithKey child : mergedTree.children) {
            printTree(child, i + 1);
        }
    }
}
