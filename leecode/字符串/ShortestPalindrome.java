/*
leetcode 214. Shortest Palindrome

Given a string S, you are allowed to convert it to a palindrome 
by adding characters in front of it. Find and return the shortest 
palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".

*/

// 我的写法，在　manacher 算法的基础上进行改造

class Solution {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;

        StringBuffer buffer = new StringBuffer("#");
        for (char c : s.toCharArray()) {
            buffer.append(c).append("#");
        }
        int mx = 0, id = 0; // id 表示当前可以到达最右侧的回文字符串的中心。 mx 表示以id为中心的最右边界。 
        int[] p = new int[buffer.length()];
        int maxlen = 0;
        for (int i = 1; i < buffer.length(); i++) {
            if (mx > i) {
                p[i] = Math.min(p[2*id-i], mx-i);　// 利用对称的点的回文半径加快查询速度
            } else {
                p[i] = 1;
            }

            // 向左或向右进行扩展边界
            while (i + p[i] < buffer.length()
                    && i - p[i] >= 0
                    && buffer.charAt(i+p[i]) == buffer.charAt(i-p[i]))
                p[i]++;

            if (i + p[i] > mx) {
                id = i;
                mx = p[i] + i - 1;
            }
            // 看是否出击到最开始的字符。　用于记录从左到右最长的回文串
            if (i - p[i] <= 0) {
                maxlen = Math.max(maxlen, i + p[i]);
            }
        }

        StringBuffer tmp = new StringBuffer();
        for (int j = maxlen; j < buffer.length(); j++) {
            if (buffer.charAt(j) != '#') tmp.append(buffer.charAt(j));
        }

        return tmp.reverse().toString() + s;
    }
}

// 别人的写法，　递归的写法　"aacecaaa"
class Solution {

    public String shortestPalindrome(String s) {
        if(s==null||s.length()<2){
            return s;
        }    
        int n　=　s.length();
        int i　=　0;
        char[] array　=　s.toCharArray();

        // 从左到右寻找回文串，　或者非回文的多个字符（需要再向下进行递归） 
        for　(int j　=　n　-　1;　j　>=　0;　j--)　{
            if(array[i]　==　array[j]){
                i++;
            }
        }
        if　(i　==　n)　{
            return s;
        }
        String temp　=　s.substring(i, n);
        String r1　=　getreverse(temp);
        // 还是有必要向下进行递归的
        return r1　+　shortestPalindrome(s.substring(0, i))　+　s.substring(i);
    }
    
    public String getreverse(String s){
        StringBuilder sb　=　new StringBuilder(s);
        return sb.reverse().toString();
    }
}