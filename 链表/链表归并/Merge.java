/***************

输入两个单调递增的链表，输出两个链表合成后的链表，
当然我们需要合成后的链表满足单调不减规则。


**************/

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/

// 需要考虑特殊情况 
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode head = null, tail = null;
        while (list1 != null && list2 != null) {
            if (head == null) {
                head = list1.val > list2.val ? list2 : list1;
                tail = head;
                if (head == list1) {
                    list1 = list1.next;
                } else {
                    list2 = list2.next;
                }
                continue;
            } else {
                if (list1.val < list2.val) {
                    tail.next = list1;
                    tail = list1;
                    list1 = list1.next;
                } else {
                    tail.next = list2;
                    tail = list2;
                    list2 = list2.next;
                }
            }
        }
        if (list1 != null) tail.next = list1;
        if (list2 != null) tail.next = list2;
        return head;
    }
}


