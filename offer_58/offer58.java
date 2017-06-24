/***** 题目描述
请实现一个函数，用来判断一颗二叉树是不是对称的。注意，
如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
*****/

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
public class Solution {
    boolean isSymmetrical(TreeNode pRoot)
    {
        if(pRoot == null) return true;
        return judge(pRoot.left, pRoot.right);
    }
    
     boolean judge(TreeNode lRoot, TreeNode rRoot){
         if(lRoot == null && rRoot == null) return  true;
         if(lRoot == null || rRoot == null) return  false;
         if(lRoot.val == rRoot.val){
             return judge(lRoot.left, rRoot.right) && judge(lRoot.right, rRoot.left);
         }else{
             return false;
         }
         
         /***  同样的代码，这样写更优雅  版本一
            if(left == null) return right==null; //相当于一个 && 操作 
            if(right == null) return false;
            if(left.val != right.val) return false;
            return comRoot(left.right, right.left) && comRoot(left.left, right.right);
         ***/
         /***
         版本二
        if (t1 == null && t2 == null)
            return true;
        if (t1 != null && t2 != null)
            return t1.val == t2.val && f(t1.left,t2.right) && f(t1.right, t2.left);
        return false;
         
         ***/
         
         /**** 非递归版本 遍历 广度优先遍历（队列）
         if(root==NULL) return true;
        queue<TreeNode*> q1,q2;
        TreeNode *left,*right;
        q1.push(root->left);
        q2.push(root->right);
        while(!q1.empty() and !q2.empty())
        {
            left = q1.front();
            q1.pop();
            right = q2.front();
            q2.pop();
            //两边都是空
            if(NULL==left && NULL==right)
                continue;
            //只有一边是空
            if(NULL==left||NULL==right)
                return false;
             if (left->val != right->val)
                return false;
            q1.push(left->left);
            q1.push(left->right);
            q2.push(right->right);
            q2.push(right->left);
        }
        return true;
         *****/
     }
}