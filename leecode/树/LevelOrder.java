/*
leetcode 102:

Given a binary tree, return the level order traversal of its nodes' values. 
(ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

*/

//　我的做法：非递归做法　
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        List<TreeNode> tmp = new ArrayList<>();
        tmp.add(root);
        while (!tmp.isEmpty()) {
            List<TreeNode> pre = new ArrayList<>();
            List<Integer> l = new ArrayList<>();
            for (TreeNode t : tmp) {
                l.add(t.val);
                if (t.left != null) pre.add(t.left);
                if (t.right != null) pre.add(t.right);
            }
            list.add(l);
            tmp = pre;
        }
        return list;
    }
}

//　递归做法

class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return ans;
        search(root, 1);
        return ans;
    }

    public void search(TreeNode node, int depth) { // 深度
        
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        if(ans.size() < depth) {　// 小于当前深度
            List<Integer> temp = new ArrayList<>();
            temp.add(node.val);
            ans.add(temp);
        } else {
            List<Integer> temp = ans.get(depth-1);
            temp.add(node.val);
        }

        if(left != null) {
            search(left, depth+1);
        }
        
        if(right != null) {
            search(right, depth+1);
        }
        
        return;
    }
}