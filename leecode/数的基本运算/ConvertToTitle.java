/*
leetcode 168. Excel Sheet Column Title
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 

*/

// 我的做法，　特别是遇到 26　的情况不好处理
class Solution {
    public String convertToTitle(int n) {
        StringBuffer buffer = new StringBuffer();
        while (n != 0) {
            int mod = n % 26;
            if (mod == 0) {
                buffer.append('Z');
                n = n / 26 - 1;
            } else {
                buffer.append((char)('A' + mod - 1));
                n = n / 26;
            }
        }
        return buffer.reverse().toString();
    } 
}

// 更加简洁的做法　
class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n>0){
            n--;
            sb.insert(0,(char)('A'+n%26));
            n=n/26;
        }
        return sb.toString();
        
    }
}