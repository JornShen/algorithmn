/*
leetcode 125. Valid Palindrome

Given a string, determine if it is a palindrome, 
considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? 
This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

*/

class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuffer buffer = new StringBuffer();
        for (char c : s.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                buffer.append(c);
            }
        }
        return buffer.toString().equals(buffer.reverse().toString());
    }
}

// 首尾指针算法

class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            char c1 = 0;
            while (start <= end && (c1 = getChar(s, start)) == 0) {
                start++;
            } 
            char c2 = 0;
            while (end >= start && (c2 = getChar(s, end)) == 0) {
                end--;
            }
            if (c1 != c2) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
    private char getChar(String s, int index) {
        char ch = s.charAt(index);
        if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
            return ch;
        } else if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch - 'A' + 'a');
        } else {
            return 0;
        }
    }
}

