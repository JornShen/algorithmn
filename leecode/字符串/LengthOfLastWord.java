/*
leetcode 58:

Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5

*/


//　切分句子成数组， 求最后一个单词的长度.
class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.equals(" ")) return 0;
        s = s.trim();　// 去除空格
        if (s.equals("")) return  0;
        String[]str = s.split(" ");
        return str[str.length - 1].length();
    }
}


// 别人的代码，　

class Solution {
    public int lengthOfLastWord(String s) {
        int res=0;
        int end=s.length()-1;
        // 去除空格
        while (end>=0 && s.charAt(end) == ' ')
            end--;
        // 统计长度
        while (end>=0 && s.charAt(end) != ' '){
            res++;
            end--;}
        return res;
    }
}