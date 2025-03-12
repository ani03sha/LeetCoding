package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class AddTwoNumbersII {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Reverse both the lists
        ListNode reverseL1 = reverseList(l1);
        ListNode reverseL2 = reverseList(l2);
        // Variables to keep track of carry and sum
        int sum = 0;
        int carry = 0;
        // Head of the final list
        ListNode head = null;
        // Reference node for the final list
        ListNode temp = null;
        // Process nodes in both lists
        while (reverseL1 != null && reverseL2 != null) {
            sum = reverseL1.val + reverseL2.val + carry;
            // Create a new node with this sum
            ListNode node = new ListNode(sum % 10);
            carry = sum / 10;
            if (head == null) {
                head = node;
                temp = head;
            } else {
                temp.next = node;
                temp = temp.next;
            }
            reverseL1 = reverseL1.next;
            reverseL2 = reverseL2.next;
        }
        // Reset sum
        sum = 0;
        // Add remaining nodes, if any
        while (reverseL1 != null) {
            sum = reverseL1.val + carry;
            temp.next = new ListNode(sum % 10);
            carry = sum / 10;
            temp = temp.next;
            reverseL1 = reverseL1.next;
        }
        while (reverseL2 != null) {
            sum = reverseL2.val + carry;
            temp.next = new ListNode(sum % 10);
            carry = sum / 10;
            temp = temp.next;
            reverseL2 = reverseL2.next;
        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        // Reverse the final list
        return reverseList(head);
    }

    private ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    public static void main(String[] args) {
        final AddTwoNumbersII addTwoNumbersII = new AddTwoNumbersII();

        ListNode head1 = new ListNode(7);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(4);
        head1.next.next.next = new ListNode(3);

        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);

        ListNode result = addTwoNumbersII.addTwoNumbers(head1, head2);
        result.printList(result);
    }
}
