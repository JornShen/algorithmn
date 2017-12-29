/*
leetcode 151. Reverse Words in a String
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

*/

public class Solution {
    public String reverseWords(String s) {
        if (s == null) return s;
        s = s.trim();
        if (s.length() == 0) return s;
        
        char[] str = s.toCharArray();
        int l = 0, r = 0;
        //　去除多个空格
        boolean flag = false;
        while (r < str.length) {
            if (flag && str[r] == ' ') {
                str[l++] = ' ';
                flag = !flag;
            } else if (str[r] != ' ') {
                str[l++] = str[r];
                flag = true;
            }
            r++;
        }

		int start = 0, end = 1;
        while (end < l) {
            if (str[start] == ' ') start++;
            if (str[end] == ' ') {
                reverseString(str, start, end - 1);
                start = end + 1;
            }
            end++;
        }
        if (str[end - 1] != ' ') reverseString(str, start, end - 1);
        reverseString(str, 0, l - 1);
        return new String(str).substring(0, l);
    }
    
    public void reverseString(char[] c, int start, int end) {
        while (start <= end) {
            char tmp = c[start];
            c[start] = c[end];
            c[end] = tmp;

            start++;
            end--;
        }
    }
    
}

// 比较没有水准的做法
public class Solution {
    public String reverseWords(String s) {
        if (s == null) return s;
        s = s.trim();
        if (s.length() == 0) return s;
        StringBuffer buffer = new StringBuffer();
        String[] str = s.split(" ");
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i].equals("")) continue;
            buffer.append(str[i]);
            if (i != 0) buffer.append(" ");
        }
        return buffer.toString();   
    }
}


