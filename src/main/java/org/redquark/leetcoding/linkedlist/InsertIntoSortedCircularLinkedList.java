package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class InsertIntoSortedCircularLinkedList {

    public ListNode insert(ListNode head, int insertVal) {
        if (head == null) {
            final ListNode newListNode = new ListNode(insertVal);
            newListNode.next = newListNode;
            return newListNode;
        }
        // Previous and current pointers
        ListNode previous = head;
        ListNode current = head.next;
        // Traverse through the list
        do {
            // Case 1: Normal insert between two values
            if (previous.val <= insertVal && insertVal <= current.val) {
                break;
            }
            // Case 2: If insertVal is smaller than the minimum or larger than
            // the maximum value present in the list
            else if (previous.val > current.val) {
                if (previous.val <= insertVal || insertVal <= current.val) {
                    break;
                }
            }
            previous = current;
            current = current.next;
        } while (previous != head);
        // Add new ListNode between previous and current;
        final ListNode newListNode = new ListNode(insertVal);
        previous.next = newListNode;
        newListNode.next = current;
        return head;
    }

    public static void main(String[] args) {
        final InsertIntoSortedCircularLinkedList insertIntoSortedCircularLinkedList = new InsertIntoSortedCircularLinkedList();

        ListNode head = new ListNode(3);
        head.next = new ListNode(4);
        head.next.next = new ListNode(1);
        head.next.next.next = head;
        int insertVal = 2;
        ListNode newHead = insertIntoSortedCircularLinkedList.insert(head, insertVal);
        newHead.printCircularLinkedList(newHead);

        head = null;
        insertVal = 1;
        newHead = insertIntoSortedCircularLinkedList.insert(head, insertVal);
        newHead.printCircularLinkedList(newHead);
    }
}
