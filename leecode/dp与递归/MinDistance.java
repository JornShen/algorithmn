/***********************
leetcode 72:

Given two words word1 and word2, find the minimum number 
of steps required to convert word1 to word2.
(each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a characte

***************************/

class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();
        int len1 = word1.length() + 1;
        int len2 = word2.length() + 1;
        int[][] steps = new int[len1][len2];

        for (int i = 0; i < len1; i++) {
            steps[i][0] = i;
        }

        for (int j = 0; j < len2; j++) {
            steps[0][j] = j;
        }

        for(int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {　//　相同
                    steps[i][j] = steps[i - 1][j - 1];
                } else {
                    int min  = Math.min(steps[i - 1][j], steps[i][j - 1]);
                    steps[i][j] = Math.min(min, steps[i - 1][j - 1]) + 1;
                }
            }
        }

        return steps[len1 - 1][len2 - 1];
    }
}


// 动态规划的算法，　step[i][j] 表示源串到目的串所花费的操作数。

迭代表达式。

step[i][j] = word1[i] == word2[j] ? steps[i - 1][j - 1] :
			 Math.min(steps[i - 1][j], steps[i][j - 1], steps[i - 1][j - 1]) + 1;



