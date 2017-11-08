/*

Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

*/

链表操作
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head; 

        ListNode pre = head.next, later = head, next = null, last = null, h = null;

        while (pre != null) {
            next = pre.next;
            pre.next = later;
            later.next = next;
            if (last != null) last.next = pre; //与前一次交换进行对接
            if (h == null) h = pre;
            if (next == null) break;
            last = later;
            pre = next.next;
            later = next;
        }
        return h;

        // 1 -> 2 -> 3 -> 4 
        // |    |    | 
        // V    V    V 
       //later pre  next
    }
}


别人的做法, 比较简洁.

public ListNode swapPairs(ListNode head) {

    if (head == null){
        return head;
    }

    if (head.next == null){
        return head;
    }

    ListNode dummy = new ListNode(0); // 虚假的头节点
    dummy.next = head;
    ListNode current = dummy;
    
    while (current.next!=null && current.next.next!=null){

        ListNode a = current.next;
        ListNode b = current.next.next;
        
        current.next = b;
        a.next = b.next;
        b.next = a;
    
        current = b.next;     
    }

     // 0 -> 1 -> 2 -> 3 -> 4 
    //  |    |    | 
    //  V    V    V  
   // curr   a    b

    return dummy.next;
}

