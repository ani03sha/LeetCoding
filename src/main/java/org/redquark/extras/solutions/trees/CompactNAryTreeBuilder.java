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
            final Queue<Node<T>> nodes = new LinkedList<>();
            nodes.offer(root);
            // Perform BFS
            while (!nodes.isEmpty()) {
                final Node<T> node = nodes.remove();
                flatList.add(new Node<>(node.getData(), new ArrayList<>()));
                for (Node<T> child : node.getChildren()) {
                    nodes.offer(child);
                }
            }
            // 2. Rebuild the tree with N-ary compacting
            final Queue<Node<T>> parents = new LinkedList<>();
            final Node<T> newNode = flatList.getFirst();
            parents.offer(newNode);
            // Start from the second node
            int index = 1;
            while (index < flatList.size()) {
                final Node<T> parent = parents.remove();
                final List<Node<T>> children = parent.getChildren();
                for (int i = 0; i < N && index < flatList.size(); i++) {
                    final Node<T> child = flatList.get(index++);
                    children.add(child);
                    parents.offer(child);
                }
            }
            return newNode;
        }
    }


    interface CompactTreeBuilder<T> {
        Node<T> compact(Node<T> root, int N);
    }

    static class Node<T> {
        private final T data;
        private final List<Node<T>> children;

        Node(T data, List<Node<T>> children) {
            this.data = data;
            this.children = children;
        }

        public T getData() {
            return data;
        }

        public List<Node<T>> getChildren() {
            return children;
        }
    }

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
        System.out.println("  ".repeat(depth) + node.getData());
        for (Node<T> child : node.getChildren()) {
            printTree(child, depth + 1);
        }
    }
}
