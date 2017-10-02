/****

对于一个字符串，请设计一个高效算法，计算其中最长回文子串的长度。
给定字符串A以及它的长度n，请返回最长回文子串的长度。
测试样例：
"abc1234321ab",12
返回：7

*****/

manacher　算法


public static int getLongestPalindrome(String A, int n) {
        // manacher　算法
        StringBuffer sb = new StringBuffer("#");
        for (int i = 0; i < A.length(); i++) {
            sb.append(A.charAt(i) + "#");
        }
        int i, mx = 0, id = 0, max = 0; // mx 回文序列的最长的右端　
        int[] p = new int[sb.length()];
        for (i = 1 ; i < sb.length(); i++) {
            if (mx > i) {
                p[i] = Math.min(p[2 * id - i], mx - i);
            } else {
                p[i] = 1;
            }
            // 向两边扩展
            while (i + p[i] < sb.length() && i - p[i] >= 0 
            	　　　&& sb.charAt(i + p[i]) == sb.charAt(i - p[i])) {
                p[i]++;
            }
            if (p[i] + i> mx) {
                mx = p[i] + i - 1;
                id = i;
            }
            max = Math.max(max, p[i] - 1);
        }
        return max;
}

动态规划　

j = i + len - 1; 当d[i] = d[j] 时候，　且　d[i + 1][j - 1] != 0;
d[i][j] = d[i + 1][j - 1] + 2;












