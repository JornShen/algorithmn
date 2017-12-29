/*
leetcode 114. Flatten Binary Tree to Linked List

Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

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
    public TreeNode pre = null; // 记录处理的上一个节点
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode right = root.right;
        pre = root;
        if (root.left != null) {
            root.right = root.left;
        }
        flatten(root.left);
        root.left = null;　// 这边左侧要设置成 null
        pre.right = right;
        flatten(right); // 此处不能在用　root.right
    }
}
