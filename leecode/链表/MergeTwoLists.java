/*

Merge two sorted linked lists and return it as a new list. 
The new list should be made by splicing together the nodes of the first two lists.

*/

非递归做法
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // 当两个链表不为空的时候
        ListNode head = null, next = null;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (head == null) head = l1;
                if (next != null) {
                    next.next = l1;
                }
                next = l1;
                l1 = l1.next;
            } else {
                if (head == null) head = l2;
                if (next != null) {
                    next.next = l2;
                }
                next = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) next.next = l1;
        if (l2 != null) next.next = l2;
        return head;    
    }
}


递归做法, 值得学习一下, 也挺简洁的.

public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = null;
        if (l1.val < l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }
}



