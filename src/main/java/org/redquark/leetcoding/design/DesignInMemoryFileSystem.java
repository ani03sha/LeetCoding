package org.redquark.leetcoding.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignInMemoryFileSystem {

    static class FileSystem {

        private final TrieNode root;

        public FileSystem() {
            this.root = new TrieNode();
        }

        public List<String> ls(String path) {
            // List to store the files/directories at the path
            final List<String> result = new ArrayList<>();
            // Get the node corresponding to the path
            final TrieNode node = search(path);
            // If the path doesn't exist, return an empty list
            if (node == null) {
                return result;
            }
            // If the path represents a file, then we only return a list
            // with that file name
            if (node.isFile) {
                result.add(node.name);
            }
            // If the path represents a directory, we return all the
            // children of that directory
            else {
                result.addAll(node.children.keySet());
            }
            // Sort in lexicographical order
            Collections.sort(result);
            return result;
        }

        public void mkdir(String path) {
            insert(path, false);
        }

        public void addContentToFile(String filePath, String content) {
            // Create a file at path if it doesn't exist already
            final TrieNode node = insert(filePath, true);
            node.content.append(content);
        }

        public String readContentFromFile(String filePath) {
            // Get the file at the path
            final TrieNode node = search(filePath);
            if (node == null || !node.isFile) {
                return "";
            }
            return node.content.toString();
        }

        private TrieNode search(String path) {
            // Reference to the current node
            TrieNode temp = this.root;
            final String[] directories = path.split("/");
            // Traverse through directories
            for (int i = 1; i < directories.length; i++) {
                if (!temp.children.containsKey(directories[i])) {
                    return null;
                }
                temp = temp.children.get(directories[i]);
            }
            return temp;
        }

        private TrieNode insert(String path, boolean isFile) {
            // Reference to the current node
            TrieNode temp = this.root;
            final String[] directories = path.split("/");
            // Traverse through directories
            for (int i = 1; i < directories.length; i++) {
                if (!temp.children.containsKey(directories[i])) {
                    temp.children.put(directories[i], new TrieNode());
                }
                temp = temp.children.get(directories[i]);
            }
            temp.isFile = isFile;
            // If a file, then set its name
            if (isFile) {
                temp.name = directories[directories.length - 1];
            }
            return temp;
        }

        static class TrieNode {
            String name;
            boolean isFile;
            final StringBuilder content;
            final Map<String, TrieNode> children;

            TrieNode() {
                this.content = new StringBuilder();
                this.children = new HashMap<>();
            }
        }
    }

    public static void main(String[] args) {
        final FileSystem fileSystem = new FileSystem();

        System.out.println(fileSystem.ls("/"));
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fileSystem.ls("/"));
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d"));
    }
}
