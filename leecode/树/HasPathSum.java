/*
leetcode  112. Path Sum
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
    private boolean hasPath = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        traceTreePath(root, 0, sum);
        return hasPath;
    }

    public void traceTreePath(TreeNode root, int sum, int target) {
        if (root == null) return;
        if (root.left == null
                && root.right == null
                && sum + root.val == target) {
            hasPath = true;
            return;
        }
        if (root.left != null) traceTreePath(root.left, sum + root.val, target);
        if (root.right != null) traceTreePath(root.right, sum + root.val, target);
    }
}

//别人的写法,　比较简洁
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return helper(root, sum);
    }
    public boolean helper(TreeNode root, int sum) {
        if (root == null || root.left == null && root.right == null) {
            return root != null && sum == root.val;
        }
        return helper(root.left, sum - root.val) || helper(root.right, sum - root.val);
    }
}