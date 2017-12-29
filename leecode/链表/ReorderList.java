/*
leetcode 143. Reorder List
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.

*/

// 我的写法，比较暴力，比较差的算法

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int l = 0, r = list.size() - 1;
        ListNode tmp = new ListNode(0);
        while (l < r) {
            tmp.next = list.get(l);
            tmp = tmp.next;
            tmp.next = list.get(r);
            tmp = tmp.next;
            l++;
            r--;
        }
        if (l == r) tmp.next = list.get(l);
        list.get(l).next = null;
    }
}

// 比较正常的思路，　从中点到末尾进行反向，　然后从首尾指针，　进行重新排列
// 代码稍显复杂，　水平比较高

class Solution {
    public void reorderList(ListNode head) {
        //Tricky part is hard to trace tail and then tail.prev
        //solution: reverse the back half so we can iterater from tail -> tail.next
        //find mid 
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }　
        // slow 节点是 mid 节点
        //reverse the part after mid
        // slow.next　指向的是尾节点
        // 这个写法很复杂，　需要动笔写，否则很难理解, cur.next 指针用来存放　tmpN　的下一个节点
        // slow.next　存放被指向的后节点
        //　写得非常巧妙，　值得学习.
        ListNode cur = slow.next;
        while(cur != null && cur.next != null){
            ListNode tempN = cur.next;
            cur.next = tempN.next;
            tempN.next = slow.next;
            slow.next = tempN;
        }

        //relink 
        ListNode iter1 = head;       //head
        ListNode iter2 = slow.next;  //head of reversed part
        slow.next = null;            //break 2 lists to avoid cycle
        while(iter1 != null && iter2 != null){
            ListNode next1 = iter1.next;
            ListNode next2 = iter2.next;
            iter1.next = iter2;
            iter2.next = next1;
            iter1 = next1;
            iter2 = next2;
        }
    }
}
