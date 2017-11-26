/*

leetcode 82:

Given a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode tmp = new ListNode(0);　// 设立伪头节点
        ListNode beforeHead = tmp;
        tmp.next = head;
        int count = 1, t = head.val;
        ListNode first = head.next;
        ListNode second = head; // 新数字的起始指针　
        while (first != null) {
            if (first.val == t) {
                count++;
            } else {
                if (count > 1) { // 新数字
                    count = 1;
                } else {
                    tmp.next = second;
                    tmp = second;
                }
                t = first.val;
                second = first;
            }
            first = first.next;
        }

        if (second.next == null) tmp.next = second;　//　最后一个节点，　并且非重复的节点
        else tmp.next = null;
        return beforeHead.next;
    }
}

// 相同的思路，别人的代码

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, slow = head, fast = head;
        while(fast != null) {
        	//　向右进行探寻
            while(fast != null && fast.val == slow.val)
                fast = fast.next;
            if(slow.next != fast) {
                pre.next = fast;
            } else {
                pre = pre.next;
            }
            slow = fast;
        }
        return dummy.next;
    }
}
