/*
leetcode 145. Binary Tree Postorder Traversal
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

*/

// 非递归做法
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
    public List<Integer> postorderTraversal(TreeNode root) {
    	
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode lastVisted = root;

        while (root != null || !stack.isEmpty()) {

            while (root != null) {
                stack.add(root);
                root = root.left;
            }

            root = stack.peek();

            // 后续遍历核心代码
            if (root.right == null || root.right == lastVisted) {
                //　右边没有节点,　或者右边的节点已经遍历过
                list.add(root.val);
                stack.pop();
                lastVisted = root;
                root = null; // 继续向上进行遍历
            } else {
                // 第一个次遍历, 向右进行遍历
                root = root.right;
            }

        }

        return list;
    }
}