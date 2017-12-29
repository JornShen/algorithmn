/*

A linked list is given such that each node contains an additional random 
pointer which could point to any node in the list or null.

Return a deep copy of the list.

*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        RandomListNode p = head, tmp, q;
        //　在每个节点后面插入复制节点
        while (p != null) {
            tmp = p.next;
            RandomListNode newOne = new RandomListNode(p.label);
            newOne.next = tmp;
            p.next = newOne;
            p = tmp;
        }
        // 复制　random 节点
        p = head;
        while (p != null) {
            q = p.next;
            tmp = q.next;
            if (p.random != null) q.random = p.random.next;
            p = tmp;
        }
        // 切分　
        RandomListNode h = head.next;
        p = head;
        q = head.next;
       /* while (true) {
            p.next = q.next;
            p = p.next;
            if (p == null) break;
            q.next = p.next;
            q = q.next;
        }*/
        while (p != null) {
            tmp = p.next;
            p.next = tmp.next;
            p = p.next;
            if (tmp.next != null) tmp.next = tmp.next.next;
        }
        return h;
    }
}


