/*
leetcode 129. Sum Root to Leaf Numbers

Given a binary tree containing digits from 0-9 only, 
each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
*/

//　代码过于复杂
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
    private int sum = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        if (root.left != null) traceTreeAndSum(root.left,  root.val);
        if (root.right != null) traceTreeAndSum(root.right, root.val);
        return sum;
    }
    public void traceTreeAndSum(TreeNode root, int upFloor) {
        if (root.left == null && root.right == null) {
            sum += upFloor * 10 + root.val;
            return;
        }
        if (root.left != null) {
            traceTreeAndSum(root.left, upFloor * 10 + root.val);
        }
        if (root.right != null) {
            traceTreeAndSum(root.right, upFloor * 10 + root.val);
        }
    }
}


// 学习一下，别人的代码更加简洁.
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
    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }
    
    public int sum(TreeNode node, int sum) {
        if(node == null) return 0;
        if(node.left == null && node.right == null) return sum *10 + node.val;
        return sum(node.left, sum*10 + node.val) + sum(node.right, sum *10 +node.val);
    }
}

