package org.redquark.extras.solutions.tries;


import org.redquark.extras.solutions.utils.TrieNodeWithDynamicChildren;

public class WildcardRouter {

    private final TrieNodeWithDynamicChildren root;

    public WildcardRouter() {
        this.root = new TrieNodeWithDynamicChildren();
    }

    public void addRoute(String path, String value) {
        // Get all directories in the path
        final String[] directories = path.split("/");
        // Reference to the root node
        TrieNodeWithDynamicChildren temp = this.root;
        for (int i = 1; i < directories.length; i++) {
            if (!temp.children.containsKey(directories[i])) {
                temp.children.put(directories[i], new TrieNodeWithDynamicChildren());
            }
            temp = temp.children.get(directories[i]);
        }
        temp.value = value;
    }

    public String getRoute(String path) {
        // Get all directories in the path
        final String[] directories = path.split("/");
        // Perform DFS on the trie
        return dfs(this.root, directories, 1);
    }

    private String dfs(TrieNodeWithDynamicChildren node, String[] directories, int index) {
        // Base cases
        if (node == null) {
            return null;
        }
        if (index == directories.length) {
            return node.value;
        }
        // Current directory
        final String currentDirectory = directories[index];
        // For wildcard matching
        if (currentDirectory.equals("*")) {
            // Check in all children nodes
            for (TrieNodeWithDynamicChildren child : node.children.values()) {
                final String result = dfs(child, directories, index + 1);
                if (result != null) {
                    return result;
                }
            }
        } else {
            return dfs(node.children.get(currentDirectory), directories, index + 1);
        }
        return null;
    }

    public static void main(String[] args) {
        final WildcardRouter wildcardRouter = new WildcardRouter();

        wildcardRouter.addRoute("/a/b/c", "value1");
        wildcardRouter.addRoute("/a", "test");

        System.out.println(wildcardRouter.getRoute("/a/*/c")); // Output: value1
        System.out.println(wildcardRouter.getRoute("/a"));     // Output: test
        System.out.println(wildcardRouter.getRoute("/a/x/c")); // Output: null
        System.out.println(wildcardRouter.getRoute("/a/b"));   // Output: null
        System.out.println(wildcardRouter.getRoute("/*")); // Output: test
        System.out.println(wildcardRouter.getRoute("/*/*")); // Output: null
        System.out.println(wildcardRouter.getRoute("/*/*/*")); // Output: value1
        System.out.println(wildcardRouter.getRoute(""));
    }
}
