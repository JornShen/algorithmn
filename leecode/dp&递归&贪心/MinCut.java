/*
leetcode 132. Palindrome Partitioning II

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

*/
// 动态规划的思想,  思路比较清晰
class Solution {

    public int minCut(String s) {

        if (s == null || s.length() == 0 || s.length() == 1) {
            return 0;
        }
　
        int[][] map = new int[s.length()][s.length()]; // 从　i 到　j 是否是回文字符　（i, j 是位置的表示）

        int[] cut = new int[s.length() + 1]; // cut[i]表示从 i 到 n 最小的划分长度

        for (int i = s.length() - 1; i >= 0; i--) {
            cut[i] = s.length() - i;
            for (int j = i; j < s.length(); j++) {
                // 将字符串
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 2 || map[i + 1][j - 1] == 1) { // i字符和j字符相差比较小，或者　i+1 ... j - 1 是回文的情况下
                        map[i][j] = 1; // i ... j 是回文
                        cut[i] = Math.min(cut[i], cut[j + 1] + 1); // i ... j 回文的情况下
                    }
                }
            }
        }

        return cut[0] - 1;
    }
}

// 两层 for 循环判断字符串是否是回文串

void dp(String s, char[][] palindrome_map) {  
    for(int i = s.length()-1;i>=0;i--) {  
        for(int j=i;j<s.length();j++) {  
            if(i==j) {  
                palindrome_map[i][j] = 1;  
            } else {  
                if(s.charAt(i)==s.charAt(j)) {  
                    if(j==i+1||palindrome_map[i+1][j-1]==1) {  
                        palindrome_map[i][j] = 1;  
                    }  
                }  
            }  
        }  
    }  
}

