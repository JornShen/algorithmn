/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null, next = null;
        int flag = lists.length, min = -1;
        while (true) {
            min = -1;
            // find minimum one, Time Limit Exceeded   归并排序
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    if (min == -1) {
                        min = i;
                    } else {
                        min = lists[min].val < lists[i].val ? min : i;
                    }
                } 
            }
            if (min == -1) {
                break;
            }
            if (head == null) {
                head = next = lists[min];
            } else {
                next.next = lists[min]; 
                next = lists[min];
            }
            lists[min] = lists[min].next;
        }
        
        return head;   
    }
}

// 以上为超时代码 原因是for循环复杂度太大

// 二路归并，递归下降
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1); // 注意此处是 list.length - 1
    }
    
    public ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (start > end) return null;
        if (start == end) return lists[start];
        int mid = (start + end) / 2;
        ListNode left = mergeKLists(lists, start, mid);
        ListNode right = mergeKLists(lists, mid + 1, end);
        return merge(left, right);        
    }
    
    // 二路归并
    public ListNode merge(ListNode  l1, ListNode l2) {
         ListNode head = null, next = null, min = null;
         while (l1 != null && l2 != null) {
             min = l1.val < l2.val ? l1 : l2;
             if (head == null) {
                 head = next = min;
             } else {
                 next.next = min;
                 next = min;
             }
             if (min == l1) {
                 l1 = l1.next;
             } else {
                 l2 = l2.next;
             }
         }
         if (l1 != null) {
            if (head == null) return l1;
            next.next = l1;
         }
         if (l2 != null) {
            if (head == null) return l2;
            next.next = l2;
         }
         return head;
    }
}



//------------ 比较投机取巧的做法 ----------------

public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists==null||lists.size()==0) return null;
        
        // 优先级队列
        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.size(),
        	new Comparator<ListNode>(){
	            @Override
	            public int compare(ListNode o1,ListNode o2){
	                if (o1.val < o2.val) {
	                    return -1;
	                } else if (o1.val==o2.val) {
	                    return 0;
	                } else {
	                    return 1;
	                }
	            }
        });
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
            
        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;
            if (tail.next!=null) {
                queue.add(tail.next);
            }
        }
        return dummy.next;
    }
}

