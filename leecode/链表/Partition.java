/*
leetcode 86:

Given a linked list and a value x,
partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.

*/

／／　基本思路是，两个指针，一个用来串连小于 x 的值，一个用来串 大于 x 的值
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode pre = head, l = null, r = null, lStart = null, rStart = null;
        while (pre != null) {
            if (pre.val >= x) {
                if (l == null) {
                    l = pre;
                    lStart = l;
                } else {
                    l.next = pre;
                    l = l.next;
                }
            } else {
                if (r == null) {
                    r = pre;
                    rStart = r;
                } else {
                    r.next = pre;
                    r = r.next;
                }
            }
            pre = pre.next;
        }
        if (l == null) return rStart;
        if (r == null) return lStart;
        r.next = lStart;
        l.next = null;
        return rStart;
    }
}

// 别人的写法
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode curr1 = dummy1;
        ListNode curr2 = dummy2;
        while(head != null){
            if(head.val < x){
                curr1.next = head;
                curr1 = curr1.next;
            } else {
                curr2.next = head;
                curr2 = curr2.next;
            }
            head = head.next;
        }
        curr2.next = null;
        curr1.next = dummy2.next;
        return dummy1.next;
    }
}
