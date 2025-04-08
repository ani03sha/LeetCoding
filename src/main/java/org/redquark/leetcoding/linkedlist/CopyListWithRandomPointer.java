package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNodeWithRandom;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

    public ListNodeWithRandom copyRandomList(ListNodeWithRandom head) {
        // Special case
        if (head == null) {
            return null;
        }
        // Map to store mappings of original list nodes and
        // their respective deep copies
        final Map<ListNodeWithRandom, ListNodeWithRandom> mappings = new HashMap<>();
        // Create the head of the deep copy
        ListNodeWithRandom copyHead = null;
        // Pointer to traverse original and deep copy list
        ListNodeWithRandom temp = head;
        ListNodeWithRandom copyTemp = null;
        // Traverse the list
        while (temp != null) {
            // Create deep copy of the current node
            final ListNodeWithRandom copy = new ListNodeWithRandom(temp.val);
            // Check if the head has already been created
            if (copyHead == null) {
                copyHead = copy;
                copyTemp = copyHead;
            } else {
                copyTemp.next = copy;
                copyTemp = copyTemp.next;
            }
            // Store the mapping in the map
            mappings.put(temp, copyTemp);
            temp = temp.next;
        }
        // Reset the traversing pointer
        temp = head;
        copyTemp = copyHead;
        while (temp != null) {
            // Check if the random node pointer exists
            if (temp.random != null) {
                copyTemp.random = mappings.get(temp.random);
            }
            temp = temp.next;
            copyTemp = copyTemp.next;
        }
        return copyHead;
    }

    public static void main(String[] args) {
        CopyListWithRandomPointer copyListWithRandomPointer = new CopyListWithRandomPointer();

        ListNodeWithRandom head = new ListNodeWithRandom(7);
        head.next = new ListNodeWithRandom(13);
        head.next.random = head;
        head.next.next = new ListNodeWithRandom(11);
        head.next.next.next = new ListNodeWithRandom(10);
        head.next.next.next.random = head.next.next;
        head.next.next.next.next = new ListNodeWithRandom(1);
        head.next.next.random = head.next.next.next.next;
        ListNodeWithRandom copyHead = copyListWithRandomPointer.copyRandomList(head);
        ListNodeWithRandom.printList(copyHead);
    }
}
