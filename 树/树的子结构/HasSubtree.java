/*************

输入两棵二叉树A，B，判断B是不是A的子结构。
（ps：我们约定空树不是任意一个树的子结构）


*************/

/********

public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

}

*******/
// 树是学习递归思想的好的材料.
public class Solution {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        // 子结构 A:root1 B:root2  先找到root1（没有向下递归） 
        // 和 root2 值相同的点, 然后向下递归判断 
        
        boolean flag = false;
        
        if(root1 == null || root2 == null){
            return false;
        }
        //****************** 学习了 ****************
        if(root1.val == root2.val){
            flag = isTreeAContainTreeB(root1,root2); // 判断是否有相的子结构
        }

        // find subtree
        if(!flag){ //继续寻找, 分别递归 根节点的左,右子树的
            flag = HasSubtree(root1.left,root2) || 
                HasSubtree(root1.right,root2);
        } 
        return  flag;
        //*******************************************************
    }
    
    public boolean  isTreeAContainTreeB(TreeNode root1,TreeNode root2){
        // B 先到达叶子节点        
        if(root2 == null){
            return true;// b 先到达根节点
        }else if(root1 == null){
            return false;
        }

        if(root1.val == root2.val){
            return isTreeAContainTreeB(root1.left,root2.left) && 
                isTreeAContainTreeB(root1.right,root2.right);
        }else{
            return false;
        }
    }
    
}