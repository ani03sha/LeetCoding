package org.redquark.leetcoding.design;

import org.redquark.leetcoding.utils.TrieNodeWithDynamicChildren;

public class DesignFileSystem {

    static class FileSystem {

        // Root of the trie
        private final TrieNodeWithDynamicChildren root;

        public FileSystem() {
            this.root = new TrieNodeWithDynamicChildren();
        }

        public boolean createPath(String path, int value) {
            // Get directories from the path
            final String[] directories = path.split("/");
            // Reference to the root node
            TrieNodeWithDynamicChildren temp = this.root;
            // Process all directories
            for (int i = 1; i < directories.length; i++) {
                // Check if the current directory exists in the current hierarchy
                if (!temp.children.containsKey(directories[i])) {
                    // If it is the last node, add it to trie
                    if (i == directories.length - 1) {
                        temp.children.put(directories[i], new TrieNodeWithDynamicChildren());
                    } else {
                        return false; // Invalid path
                    }
                }
                temp = temp.children.get(directories[i]);
            }
            if (temp.value != -1) {
                return false;
            }
            temp.value = value;
            return true;
        }

        public int get(String path) {
            // Get directories from the path
            final String[] directories = path.split("/");
            // Reference to the root node
            TrieNodeWithDynamicChildren temp = this.root;
            // Process all directories
            for (int i = 1; i < directories.length; i++) {
                if (!temp.children.containsKey(directories[i])) {
                    return -1;
                }
                temp = temp.children.get(directories[i]);
            }
            return temp.value;
        }
    }

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        System.out.println(fileSystem.createPath("/a", 1)); // return true
        System.out.println(fileSystem.get("/a")); // return 1

        fileSystem = new FileSystem();

        System.out.println(fileSystem.createPath("/leet", 1)); // return true
        System.out.println(fileSystem.createPath("/leet/code", 2)); // return true
        System.out.println(fileSystem.get("/leet/code")); // return 2
        System.out.println(fileSystem.createPath("/c/d", 1)); // return false because the parent path "/c" doesn't exist.
        System.out.println(fileSystem.get("/c")); // return -1 because this path doesn't exist.

    }
}
