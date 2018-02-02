/*
leetcode 230. Kth Smallest Element in a BST
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often 
and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?

*/

// 我的写法，　非递归
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0) return root.val;
            root = root.right;
        }
        return 0;
    }
}



／／　递归的写法

class Solution {
    int seq = 0;
    int res = 0;
    public int kthSmallest(TreeNode root, int k) {
        seq = k-1;
        kthSmallest(root);
        return  res;
    }
    public void kthSmallest(TreeNode root) {
        if (root == null　|| seq<0)　{ // 减枝
            return;
        }
        
        kthSmallest(root.left);
        if (seq == 0)　{
            res = root.val;
            seq--;
        }
        seq--;
        kthSmallest(root.right);        
        
    }    
}