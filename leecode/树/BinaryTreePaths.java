/*
leetcode 257. Binary Tree Paths

Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
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
    private List<String> paths = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return paths;
        pathSearch(root, root.val+"");
        return paths;
    }

    public void pathSearch(TreeNode root, String road) {
        if (root.left == null && root.right == null) {
            paths.add(road);
        }
        if (root.left != null) {
            pathSearch(root.left, road + "->" + root.left.val);
        }
        if (root.right != null) {
            pathSearch(root.right, road + "->" + root.right.val);
        }
    }
}