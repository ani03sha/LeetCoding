package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {

    public String simplifyPath(String path) {
        // Special case
        if (path == null || path.isEmpty()) {
            return path;
        }
        // Stack to store the directory structure
        final Deque<String> stack = new ArrayDeque<>();
        // Split the path with directories
        final String[] directories = path.split("/");
        // Process all directories
        for (String directory : directories) {
            if (directory.equals("..") && !stack.isEmpty()) {
                // Move one directory up
                stack.pop();
            } else if (!directory.equals("..") && !directory.equals(".") && !directory.isEmpty()) {
                stack.push(directory);
            }
        }
        // Reconstruct the final path
        final StringBuilder finalPath = new StringBuilder();
        while (!stack.isEmpty()) {
            finalPath.append("/").append(stack.pollLast());
        }
        return finalPath.isEmpty() ? "/" : finalPath.toString();
    }

    public static void main(String[] args) {
        final SimplifyPath simplifyPath = new SimplifyPath();

        System.out.println(simplifyPath.simplifyPath("/home/"));
        System.out.println(simplifyPath.simplifyPath("/home//foo/"));
        System.out.println(simplifyPath.simplifyPath("/home/user/Documents/../Pictures"));
        System.out.println(simplifyPath.simplifyPath("/../"));
        System.out.println(simplifyPath.simplifyPath("/.../a/../b/c/../d/./"));
    }
}
