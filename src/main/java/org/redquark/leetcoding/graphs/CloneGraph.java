package org.redquark.leetcoding.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

    public Node cloneGraph(Node node) {
        // Special case
        if (node == null) {
            return null;
        }
        // Map to keep track of nodes and clone nodes
        final Map<Node, Node> mappings = new HashMap<>();
        final Node clone = new Node(node.val);
        mappings.put(node, clone);
        // Queue to perform BFS on the graph
        final Queue<Node> nodes = new ArrayDeque<>();
        nodes.offer(node);
        while (!nodes.isEmpty()) {
            Node current = nodes.remove();
            // Traverse through the neighbors of this node
            for (Node neighbor : current.neighbors) {
                if (!mappings.containsKey(neighbor)) {
                    mappings.put(neighbor, new Node(neighbor.val));
                    nodes.offer(neighbor);
                }
                mappings.get(current).neighbors.add(mappings.get(neighbor));
            }
        }
        return clone;
    }

    static class Node {
        final int val;
        final List<Node> neighbors;

        Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Node neighbor : neighbors) {
                sb.append(neighbor.val).append(", ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        final CloneGraph cloneGraph = new CloneGraph();

        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        one.neighbors.add(two);
        one.neighbors.add(four);
        two.neighbors.add(one);
        two.neighbors.add(three);
        three.neighbors.add(two);
        three.neighbors.add(four);
        four.neighbors.add(one);
        four.neighbors.add(three);

        Node clone = cloneGraph.cloneGraph(one);
        System.out.println(clone.hashCode() != one.hashCode());
        System.out.println(clone.neighbors.toString());
    }
}
