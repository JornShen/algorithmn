/******
给定一颗二叉搜索树，请找出其中的第k大的结点。例如，
 5 / \ 3 7 /\ /\ 2 4 6 8 中，按结点数值大小顺序第三个结点的值为4。
**/	


/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
import java.util.*;
public class Solution {
    
    /******
    private int  index = 0;
    private TreeNode temp = null;
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        KthNode1(pRoot, k);
        return temp;
    }
    
    void KthNode1(TreeNode pRoot, int k){
        if(pRoot == null){
            return ;
        }
        KthNode(pRoot.left, k);
        index++;
        if(index == k){
            temp = pRoot;
        }
        KthNode(pRoot.right, k);
        
    }
    
    //-----  以上为递归版本 ----------
    ********/
    
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        
        int index = 0;
        Stack<TreeNode> stack = new Stack<>();
        if(pRoot == null) return null;
        TreeNode temp = pRoot;
        if(k == 0) return null;
        // 　中序遍历 
        while(temp != null || !stack.isEmpty()){
            while(temp != null){
                 stack.push(temp);
                 temp = temp.left;
            }
            temp = stack.pop();
            index++;
            if(index == k) return temp;
            temp = temp.right;
        }
        return null;
    }
}
