/*
leetcode 173. Binary Search Tree Iterator

Implement an iterator over a binary search tree (BST). 
Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory,
where h is the height of the tree.

*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BSTIterator {
    
    private TreeNode head = null;
    // 非递归先序遍历
    public BSTIterator(TreeNode root) {
        // 中序遍历　
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root, pre = null;

        // right　指针下一个节点
        while (!stack.isEmpty() || curr != null) {
            
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr = stack.pop();

            if (pre == null) pre = curr;
            else { pre.right = curr; pre = curr; }
            
            if (head == null) head = curr;
            
            curr = curr.right;
        }
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return  !(head == null);
    }

    /** @return the next smallest number */
    public int next() {
        if (!hasNext()) return 0;
        int val = head.val;
        head = head.right;
        return val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */


//　递归先序遍历，　设置全局变量　prev 
public class BSTIterator {

    private TreeNode prev;
    private TreeNode head;
    
    public BSTIterator(TreeNode root) {
        inorder(root);        
    }
    
    private void inorder(TreeNode t){
        if(t == null)
            return;
        inorder(t.left);
        // 此处写得很精妙
        if(prev == null)
            head = t;
        else
            prev.right = t;
        prev = t;
        inorder(t.right);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return head != null;
    }

    /** @return the next smallest number */
    public int next() {
        int val = head.val;
        head = head.right;
        return val;
    }
}
