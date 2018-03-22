/*
leetcode 236. Lowest Common Ancestor of a Binary Tree
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined 
between two nodes v and w as the lowest node in T that has both v and w as descendants 
(where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 
Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself 
according to the LCA definition.

*/

// 递归
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;// 此处直接用引用进行比较，用值进行比较会有问题
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root; 
        else if (left != null) return left;
        else return right;
    }
}

//　求两条路径的最近的公共节点
public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    List<TreeNode> pt = new ArrayList<>();
    List<TreeNode> qt = new ArrayList<>();
    findPath(root, p, pt);
    findPath(root, q, qt);
    int i = 0, j = 0;
    while(i < pt.size() && j < qt.size() && pt.get(i) == qt.get(j)) {
        i++;
        j++;
    }
    if(pt.size() <= 0 || qt.size() <= 0) {
        return null;
    }
    return pt.get(i - 1);
  }

  private boolean findPath(TreeNode root, TreeNode node, List<TreeNode> path) {
    if(root == null) {
      return false;
    }
    path.add(root);
    if(root == node) {
        return true;
    }
    if( !findPath(root.left, node, path) && !findPath(root.right, node, path)) {
    path.remove(path.size() - 1);
    return false;
    }      return true; 

  }
}
