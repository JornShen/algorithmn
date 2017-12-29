
/*
leetcode  113. Path Sum II

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]

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
    
    private List<List<Integer>> paths = new ArrayList<>();
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return paths;
        traceTreePath(root, 0, sum, new ArrayList<>());
        return paths;
    }

    public void traceTreePath(TreeNode root, int sum, int target, List<Integer> l) {
        if (root.left == null
                && root.right == null
                && sum + root.val == target) {
        	// 满足条件
            List<Integer> tmp = new ArrayList<>(l);
            tmp.add(root.val);
            paths.add(tmp);
            return;
        }
        // 向下进行递归
        l.add(root.val);
        if (root.left != null) traceTreePath(root.left, sum + root.val, target, l);
        if (root.right != null) traceTreePath(root.right, sum + root.val, target, l);
        l.remove(l.size() - 1);
    }
    
}