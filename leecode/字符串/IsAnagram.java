/*
leetcode 242. Valid Anagram
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?

*/
//　统计是否有相同的字母组成
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null) return t == null;
        if (s.length() != t.length()) return false;

        int[] cal = new int[26];
        for (char c : s.toCharArray()) {
            cal[c-'a']++;
        }

        for (char c : t.toCharArray()) {
            if (cal[c-'a'] == 0) return false;
            cal[c-'a']--;
        }
        return true;
    }
}   
