package org.redquark.extras.solutions.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CompactNAryTreeBuilder {

    static class CompactTreeBuilderImpl<T> implements CompactTreeBuilder<T> {

        @Override
        public Node<T> compact(Node<T> root, int N) {
            if (root == null || N <= 0) {
                return null;
            }
            // 1. Flatten the tree into a list using BFS
            final List<Node<T>> flatList = new ArrayList<>();
            Queue<Node<T>> nodes = new LinkedList<>();
            nodes.offer(root);
            // Perform BFS
            while (!nodes.isEmpty()) {
                final Node<T> node = nodes.remove();
                flatList.add(new Node<>(node.data(), new ArrayList<>()));
                for (Node<T> child : node.children()) {
                    nodes.offer(child);
                }
            }
            // 2. Rebuild the tree with N-ary compacting
            nodes = new LinkedList<>();
            final Node<T> newRoot = flatList.getFirst();
            nodes.offer(newRoot);
            // Start from the second node
            int index = 1;
            while (index < flatList.size()) {
                final Node<T> node = nodes.remove();
                final List<Node<T>> children = node.children();
                // Add up to N children
                for (int i = 0; i < N && index < flatList.size(); i++) {
                    final Node<T> child = flatList.get(index++);
                    children.add(child);
                    nodes.offer(child);
                }
            }
            return newRoot;
        }
    }

    interface CompactTreeBuilder<T> {
        Node<T> compact(Node<T> root, int N);
    }

    record Node<T>(T data, List<Node<T>> children) {}

    public static void main(String[] args) {
        Node<String> root = new Node<>("A", Arrays.asList(
                new Node<>("B", Arrays.asList(
                        new Node<>("D", Collections.emptyList()),
                        new Node<>("E", Collections.singletonList(
                                new Node<>("H", Collections.emptyList())
                        ))
                )),
                new Node<>("C", Arrays.asList(
                        new Node<>("F", Collections.emptyList()),
                        new Node<>("G", Collections.emptyList())
                ))
        ));

        CompactTreeBuilder<String> builder = new CompactTreeBuilderImpl<>();
        Node<String> compacted = builder.compact(root, 2);

        printTree(compacted, 0);
    }

    static <T> void printTree(Node<T> node, int depth) {
        System.out.println("  ".repeat(depth) + node.data());
        for (Node<T> child : node.children()) {
            printTree(child, depth + 1);
        }
    }
}
