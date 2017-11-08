/*


leetcode 25:

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode result = null,  start = head,  point = head, end = null;
        ListNode p3 = null , p1 = null, p2 = null; // p1 store the last of start, p2 store the next of start 
        int count = 0; 
        while (point != null) {
            count++;
            point = point.next;
            if (count % k == 0) {
                end = p1 = start;
                start = start.next;
                while (start != point) {
                    p2 = start.next; // prestore the next of node start
                    start.next = p1;
                    p1 = start;
                    start = p2;
                }
                end.next = start;
                if (result == null) result = p1; // record of head
                if (p3 != null) p3.next = p1; // p3 store the last element in last change
                p3 = end;
                count = 0;
            }
        }
        if (result == null) result = head;
        return result;
    }
}

// 以上采用来了非递归的做法

// 下面是递归的做法

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        //find the kth ListNode
        ListNode curr = head;
        int count = 0;  //
        while (curr != null && count != k) {
            curr = curr.next;
            count++;
        }
        // 个数小于 k 直接返回
        if (count < k) {
            return head;
        }
        curr = reverseKGroup(curr, k);
        //reverse within these nodes
        while (count-- > 0) {     //////
            ListNode next = head.next;
            head.next = curr;
            curr = head;
            head = next;
        }

        return curr;
    }
}
