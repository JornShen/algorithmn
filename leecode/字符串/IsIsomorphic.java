/*
leetcode 205. Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another 
character while preserving the order of characters. No two characters 
may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

*/
//　我的写法，　关键在于标记位置，　同构主要体现在　字母映射相同
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null) return t == null;
        if (s.length() != t.length()) return false;
        int[] s2 = new int[256]; // 标记位置
        int[] t2 = new int[256];
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        for (int i = 0; i < s1.length; i++) {
            if (s2[s1[i]] == t2[t1[i]]) {
                s2[s1[i]] = i + 1;
                t2[t1[i]] = i + 1;
            } else {
                return false;
            }
        }
        return true;
    }
}