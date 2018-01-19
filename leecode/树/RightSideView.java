/*
leetcode 199. Binary Tree Right Side View
Given a binary tree, imagine yourself standing on the right side of it, 
return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].

*/

// 广度优先遍历，　借助队列
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
    public List<Integer> rightSideView(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        queue.add(root);
        while (!queue.isEmpty()) {

            int size = queue.size();
            list.add(queue.peek().val);
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.right != null) queue.add(tmp.right);
                if (tmp.left != null) queue.add(tmp.left);
            }

        }
        return list;
    }
}


// 递归的写法

class Solution {
	
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        dfs(result, root, 0);
        return result;
    }
    
    private void dfs(List<Integer> result, TreeNode root, int h){
        //operation at node
        h += 1;
        if(h > result.size()){
            result.add(root.val);
        }
        
        //generate children
        if(root.right != null){
            dfs(result, root.right, h);
        }
        
        if(root.left != null){
            dfs(result, root.left, h);
        }
        
        //leaving this node
        h -= 1;       
    }   
}