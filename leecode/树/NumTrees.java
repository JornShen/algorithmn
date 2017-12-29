/*
leetcode 96:

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

*/

／／　 解法一：　动态规划，　numbers[i][j] 表示从 i　到 j 有多少棵树

class Solution {
    public int numTrees(int n) {
        int[][] number = new int[n+1][n+1];
        return howManyTrees(1, n, number);
    }
    private int howManyTrees(int start, int end, int[][] number) {
        if(start >= end) {
            return 1;
        }
        // 已经统计过了
        if(number[start][end] > 0) {
            return number[start][end];
        }
        int total = 0;
        for(int i=start; i<=end; i++) {
            total +=  (howManyTrees(start, i-1, number)*howManyTrees(i+1, end, number));
        }
        number[start][end] = total;
        return total;
    }

}

／／　卡特兰数问题　h(n)= h(0)*h(n-1)+h(1)*h(n-2) + ... + h(n-1)*h(0) (n>=2)
／／　可以解决　凸多边形三角划分，　给定节点组成二叉搜索树，　n对括号正确匹配数目，　出栈种类问题
／／　参考　https://baike.baidu.com/item/%E5%8D%A1%E7%89%B9%E5%85%B0%E6%95%B0/6125746?fr=aladdin

class Solution {
    public int numTrees(int n) {
        if(n<=0)  
        return 0;  
        int[] res = new int[n+1];  
        res[0] = 1;  
        res[1] = 1;  
        for(int i=2;i<=n;i++)  
        {  
            for(int j=0;j<i;j++)  
            {  
                res[i] += res[j]*res[i-j-1];  
            }  
        }  
        return res[n];  
    }
}

//　出栈的递归写法，　和求创建不同种类的二叉搜索树相类似的．

public List<List<Integer>> generateOutputStack(int[] nums, int left, int right) { // pos
    List<List<Integer>> list = new ArrayList<>();
    if (left > right) {
        list.add(new ArrayList<>()); //这一步很重要
        return list;
    }
    for (int i = left; i <= right; i++) { // num[i] 最后出栈, i　之前的所有数都已经出栈
        List<List<Integer>> lefts = outStack(nums, left, i - 1); // 在 i 之前的数的所有出栈情况
        List<List<Integer>> rights = outStack(nums, i + 1, right); // 在 i 之后的数的所有的出栈情况
        for (List<Integer> l : lefts) {
            for (List<Integer> r : rights) {
                List<Integer> tmp = new ArrayList<>(l);
                tmp.addAll(r);
                tmp.add(nums[i]);
                list.add(tmp);
            }
        }

    }
    return list;
}


// 出栈的种类和上面的解法是一样的．
