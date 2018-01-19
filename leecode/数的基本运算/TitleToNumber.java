/*
leetcode 171. Excel Sheet Column Number
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 

*/
class Solution {
    public int titleToNumber(String s) {
        char[] str = s.toCharArray();
        int base = 1;
        int sum = 0;
        for (int i = str.length - 1; i >= 0; i--) {
            sum += (str[i] - 'A' + 1) * base;
            base *= 26;
        }
        return sum;
    }
}