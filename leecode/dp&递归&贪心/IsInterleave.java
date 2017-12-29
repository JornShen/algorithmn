/*
leetcode 97:

Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

*/

// 状态的定义： flag[i][j] 表示　字符串 s[i + j - 1] 是否可以由字符串 s1[0...i]　和　s2[0...j] 组成
// 
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        boolean[][] flag = new boolean[s1.length() + 1][s2.length() + 1];
        if (s1.length() + s2.length() != s3.length()) return false;
        flag[0][0] = true;
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
            	// 递推式
                if (flag[i][j]
                        || (i - 1 >= 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1) && flag[i - 1][j])
                        || (j - 1 >= 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1) && flag[i][j - 1])) {
                    flag[i][j] = true;
                } else {
                    flag[i][j] = false;
                }
            }
        }
        return flag[s1.length()][s2.length()];        
    }
}