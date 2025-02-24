package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class DeleteNodeInALinkedList {

    public void deleteNode(ListNode node) {
        // Get the next node of the given node
        ListNode nextNode = node.next;
        // Copy the value of nextNode in given node
        node.val = nextNode.val;
        // Point the next pointer of the given to the next
        // pointer of the nextNode
        node.next = nextNode.next;
    }

    public static void main(String[] args) {
        final DeleteNodeInALinkedList deleteNodeInALinkedList = new DeleteNodeInALinkedList();

        ListNode head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);
        deleteNodeInALinkedList.deleteNode(head.next);
        head.printList(head);

        head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);
        deleteNodeInALinkedList.deleteNode(head.next.next);
        head.printList(head);
    }
}
