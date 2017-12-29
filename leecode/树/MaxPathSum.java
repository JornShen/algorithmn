/*
leetcode 124. Binary Tree Maximum Path Sum

Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes 
from some starting node to any node in the tree along the parent-child connections. 
The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.

*/

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
    private int sum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        tracePath(root);
        return sum;
    }
    public int tracePath(TreeNode root) {
        if (root == null) return 0;
        int left = tracePath(root.left);
        int right = tracePath(root.right);
        if (left <= 0 && right <= 0) {
            sum = Math.max(sum, root.val);
            return root.val;
        } else {
            sum = Math.max(left + right + root.val, sum);
            int tmp = Math.max(left, right); 
            sum = Math.max(sum, root.val + tmp);
            return root.val + tmp;
        }
    }
}

// 别人的做法
class Solution {

    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return res;
    }
    
    public int helper(TreeNode root){
        if(root==null) return 0;
        int left = Math.max(0,helper(root.left));　//　和　0 进行比较, 比较简洁
        int right = Math.max(0,helper(root.right)); 
        res = Math.max(res,left+right+root.val); 
        return Math.max(left,right)+root.val;
    }
    
}
