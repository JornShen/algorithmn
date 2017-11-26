/*
leetcode 87:

Given a string s1, we may represent it as a binary tree by partitioning it 
to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

*/

／／字符串进行两段切分． 对这两段片段进行交叉判断

//　解法一: 暴力遍历
class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true; //　递归出口
        //　判断元素　是否相同
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        if (!Arrays.equals(c1, c2)) return false;
        // 字符串进行切割，　向下递归判断
        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s2.length() - i)))
                return true;
        }
        return false;
    }
}

／／　动态规划 　dp[i][j][len]　从s1的第i个字符开始长度为len的子串，和从s2的第j个字符开始长度为len的子串，是否互为scramble。
／／　dp[i][j][len] |= dp[i][j][k] && dp[i + k][j + k][len - k] || dp[i][j + len - k][k] && dp[i + k][j][len - k];  

public class Solution {  
    public boolean isScramble(String s1, String s2) {  
        if (s1.length() != s2.length()) return false;  
        if (s1.equals(s2)) return true;
        boolean[][][] dp = new boolean[s1.length()][s2.length()][s1.length() + 1];  
        // 初始化, 长度为 1 
        for (int i = 0; i < s1.length(); i++) {  
            for (int j = 0; j < s2.length(); j++) {  
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);  
            }  
        }  
       　　
        for (int len = 2; len <= s1.length(); len++) {  // 长度为　2 
            for (int i = 0; i < s1.length() - len + 1; i++) {  
                for (int j = 0; j < s2.length() - len + 1; j++) {  
                    for (int k = 1; k < len; k++) { 
                    	// 切分串．
                        dp[i][j][len] |= dp[i][j][k] && dp[i + k][j + k][len - k] 
                        || dp[i][j + len - k][k] && dp[i + k][j][len - k];  
                    }  
                }  
            }  
        }  
        return dp[0][0][s1.length()];  
    }  
}  










