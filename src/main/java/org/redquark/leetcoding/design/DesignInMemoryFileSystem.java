package org.redquark.leetcoding.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignInMemoryFileSystem {

    static class FileSystem {

        private final TrieNodeForFileSystem root;

        FileSystem() {
            root = new TrieNodeForFileSystem();
        }

        public List<String> ls(String path) {
            // List to store all directories and files at the path
            final List<String> result = new ArrayList<>();
            // Reference of the TrieNode with path
            final TrieNodeForFileSystem node = this.root.search(path);
            if (node == null) {
                return result;
            }
            if (node.isFile) {
                result.add(node.name);
            } else {
                result.addAll(node.children.keySet());
            }
            // Sort the result
            Collections.sort(result);
            return result;
        }

        public void mkdir(String path) {
            this.root.insert(path, false);
        }

        public void addContentToFile(String filePath, String content) {
            final TrieNodeForFileSystem node = root.insert(filePath, true);
            node.content.append(content);
        }

        public String readContentFromFile(String filePath) {
            final TrieNodeForFileSystem node = this.root.search(filePath);
            if (node != null && node.isFile) {
                return node.content.toString();
            }
            return "";
        }

        static class TrieNodeForFileSystem {
            String name;
            boolean isFile;
            StringBuilder content;
            Map<String, TrieNodeForFileSystem> children;

            TrieNodeForFileSystem() {
                this.content = new StringBuilder();
                this.children = new HashMap<>();
            }

            TrieNodeForFileSystem insert(String path, boolean isFile) {
                // Reference to the root node
                TrieNodeForFileSystem current = this;
                // Split the path by slashes
                final String[] parts = path.split("/");
                // For every part
                for (int i = 1; i < parts.length; i++) {
                    final String part = parts[i];
                    if (!current.children.containsKey(part)) {
                        current.children.put(part, new TrieNodeForFileSystem());
                    }
                    current = current.children.get(part);
                }
                current.isFile = isFile;
                if (isFile) {
                    current.name = parts[parts.length - 1];
                }
                return current;
            }

            TrieNodeForFileSystem search(String path) {
                // Reference to the current node
                TrieNodeForFileSystem current = this;
                final String[] parts = path.split("/");
                for (int i = 1; i < parts.length; i++) {
                    final String part = parts[i];
                    if (!current.children.containsKey(part)) {
                        return null;
                    }
                    current = current.children.get(part);
                }
                return current;
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
