/*********************

输入一个链表，输出该链表中倒数第k个结点.

*************************/

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/


// 链表常用思想, 双指针移位操作

public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        ListNode l = head;
        for (int i = 0; i < k; i++) {
            if (l == null) return null;
            l = l.next;
        }
        ListNode r = head;
        while (l != null) {
            l = l.next;
            r = r.next;
        }
        return r;
    }
}
