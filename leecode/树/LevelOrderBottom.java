/*
leetcode 107. Binary Tree Level Order Traversal II

Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<TreeNode> curr = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        curr.add(root);
        while (!curr.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode t : curr) {
                list.add(t.val);
                if (t.left != null) {
                    next.add(t.left); 
                }
                if (t.right != null) {
                    next.add(t.right);
                }
            }
            result.add(list);
            curr = next;
        }
        Collections.reverse(result);
        return result;
    }
}

// 递归的写法
class Solution {
	/**
	 * DFS - recursive
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
	    List<List<Integer>> result = new ArrayList<>();
	    helper(result, root, 0);
	    return result;
	}

	private void helper(List<List<Integer>> list, TreeNode n, int level) {  // level start from 0
	    if (n == null) return;
	    if (level == list.size()) {  // prepend an empty list for that level
	        list.add(0, new ArrayList<Integer>());
	    }
	    list.get(list.size() - level - 1).add(n.val);
	    helper(list, n.left, level + 1);
	    helper(list, n.right, level + 1);
	}
｝