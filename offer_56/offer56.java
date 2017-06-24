
/****
在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 
例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
**/
/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public ListNode deleteDuplication(ListNode pHead)
    {
        
        if(pHead == null) return null;
        ListNode p = pHead;
        ListNode n = new ListNode(0);// 有作用
        ListNode pre = n;
        n.next = pHead;
        boolean flag = false;
        while(p != null){
            ListNode q = p.next;
            if(q == null) break;
            if(q.val == p.val){
                //------关键步骤 需要好好学习-------------
                while (q != null && q.val == p.val) { // 遇到相同的情况下，循环找到一个不同的点
                    q = q.next;
                }
                //------------------------------------
                pre.next = q;//pre的next进行更新
                p = q;
            }else{
                if(!flag){//记录头节点
                    flag = true;
                    n.next = p;
                }
                pre = p;//不同的情况下，pre进行替换
                p = q;
            }
        }
        return n.next;        
    }   
}