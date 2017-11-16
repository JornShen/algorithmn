/*
leetcode 61:

Given a list, rotate the list to the right by k places, where k is non-negative.

Example:

Given 1->2->3->4->5->NULL and k = 2,

return 4->5->1->2->3->NULL.

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        ListNode tmp = head;
        int count = 0;

        while (tmp != null) {
            tmp = tmp.next;
            count++;
        }

        k = k % count;
        if (k == 0) return head;
        // 双指针方法，　类似于求倒数　第n个数
        ListNode pre = head;
        while (k != 0) {
            pre = pre.next;
            k--;
        }

        ListNode after = head;
        while (pre.next != null) {
            pre = pre.next;
            after = after.next;
        }
        pre.next = head;
        tmp = after.next;
        after.next = null;
        
        return tmp;
    }
}



