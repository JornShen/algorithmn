/*

leetcode 5:

Given a string s, find the longest palindromic substring in s. 
You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"

*/

//我的做法，错误 这种情况有问题 abchdefgcba

public String longestPalindrome(String s) {      
    String str = check(s);
    char[] c1 = str.toCharArray();
    char[] c2 = new StringBuffer(str).reverse().toString().toCharArray(); // 字符串反向
    int len = str.length();
    int[][] sum = new int[len][len];
    int maxLen = 0;
    int pos = 0;
    for (int i = 0; i < len; i++) {
        for (int j = 0; j < len; j++) {
            if (c1[i] == c2[j]) {
                if (i == 0 || j == 0) {
                    sum[i][j] = 1;
                } else {
                    sum[i][j] = sum[i - 1][j - 1] + 1;
                }
            }
            if (sum[i][j] > maxLen) {
                maxLen  = sum[i][j];
                pos = i;
            }
        }

    }
    if (maxLen == 1) {
        return s.substring(0, 1);
    } else {
        return str.substring(pos - maxLen + 1, pos + 1);
    }
}
    
public String check(String s) {
    int i = 0, j = s.length() - 1;
    while (s.charAt(i) == s.charAt(j) && i < j) {
        i++;
        j--;
    }
    if (i >= j) return s;
    return s.substring(i, j + 1);
}

／／ 思路：将串进行反向，　最长的公共字串, 但是这样做会有问题，比如 abcdasdfghjkldcba
／／　之后, 为了解决这个问题，　另外再写了一个函数，　用于解决这个问题．但还是有问题, aaabaaaa

可以用的解法

1.暴力解决：　暴力切分，然后判断是否是回文序列　或者　采用中心扩展法

2.　manacher　算法　

public String longestPalindrome(String s) {
    // 解法一, 两层循环暴力解
    // 解法二, 反向, 然后求最长的公共字串,但是遇到这种串就有问题  abcdasdfghjkldcba
    // manacher　算法
    StringBuffer sb = new StringBuffer("#");
    for (int i = 0; i < s.length(); i++) {
        sb.append(s.charAt(i) + "#");
    }
    int i, mx = 0, id = 0, max = 0; // mx 回文序列的最长的右端
    int pos = 0;
    int[] p = new int[sb.length()];
    for (i = 1 ; i < sb.length(); i++) {
        if (mx > i) {
            p[i] = Math.min(p[2 * id - i], mx - i);
        } else {
            p[i] = 1;
        }
        // 向两边扩展
        while (i + p[i] < sb.length() 
        	&& i - p[i] >= 0 
        	&& sb.charAt(i + p[i]) == sb.charAt(i - p[i])) {
            p[i]++;
        }
        if (p[i] + i> mx) {
            mx = p[i] + i - 1;
            id = i;
        }
        if (max < p[i] - 1) {
            max = p[i] - 1;
            pos = i;
        }
    }
    StringBuffer buffer = new StringBuffer();
    int l = pos - p[pos] + 1;
    for (int j = 0; j < max * 2; j++) {
        if (sb.charAt(l) != '#') {
            buffer.append(sb.charAt(l));
        }
        l++;
    }
    return buffer.toString();

}

[参考博客](https://wizardforcel.gitbooks.io/the-art-of-programming-by-july/content/01.05.html)
