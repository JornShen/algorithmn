/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

*/

//　使用队列存放不相同的字符
import java.util.*;
class Solution {
    public int lengthOfLongestSubstring(String s) { 
        int sum = 0;
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (queue.contains(temp)) { //　看 queue 是否相同的字符，　有的话， 将其左边的，包括该字符都出队列
                char c = queue.poll();
                while (c != temp) {
                    c = queue.poll();
                }
            }
            queue.add(temp);
            sum  = queue.size() > sum ? queue.size() : sum;
        }
        return sum;
    }
}

//　别人代码,　学习一下
public int lengthOfLongestSubstring(String s) {
    if(s == null || s.length() == 0) {
		return 0;
	}
	//用来判断一个字母是否出现了两次
	int[] freq = new int[256];
	char[] c = s.toCharArray();
	int l = 0;
	int r = -1;
	int res = 0;
	while(l < s.length()) {
		if(r + 1 < c.length && freq[c[r + 1]] == 0) {
			freq[c[++r]]++ ; // 右边扩张并记录
		}else {
			freq[c[l++]]--;
		}
		res = Math.max(res, r - l + 1);
	}
	return res;
}
