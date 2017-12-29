/*
leetcode 115. Distinct Subsequences

Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/


/*

  Ø r a b b b i t
Ø 1 1 1 1 1 1 1 1
r 0 1 1 1 1 1 1 1
a 0 0 1 1 1 1 1 1
b 0 0 0 1 2 3 3 3
b 0 0 0 0 1 3 3 3
i 0 0 0 0 0 0 3 3
t 0 0 0 0 0 0 0 3 

因为空串也是空串的一个子序列,  空串也是任意字符串的一个子序列, 非空字符串不能当空字符串的子序列

dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1]

*/


class Solution {
    public int numDistinct(String s, String t) {
        if (s.equals(t)) return 1;
        int[][] dp = new int[t.length() + 1][s.length() + 1]; // 从源s到t 总共需要多少操作
        for (int i = 0; i <= s.length(); i++) {
            dp[0][i] = 1; //  从源到目的删除 n 个字符
        }

        for (int i = 1; i <= t.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                int tmp = s.charAt(j - 1) == t.charAt(i - 1) ? dp[i - 1][j - 1] : 0;
                dp[i][j] = dp[i][j - 1] + tmp;
            }
        }
        return dp[t.length()][s.length()];
    }
}






