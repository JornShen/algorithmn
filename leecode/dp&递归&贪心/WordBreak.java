/*
leetcode 139. Word Break

Given a non-empty string s and a dictionary wordDict containing a list 
of non-empty words, determine if s can be segmented into a space-separated 
sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code"

*/

// 使用了递归的做法，结果超时了
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return judgeWordBreak(s, set);
    }
    public boolean judgeWordBreak(String s, Set<String> set) {
        if (s.equals("")) return true;
        boolean flag = false;
        for (String str : set) {
            if (s.startsWith(str)) {
                flag |= judgeWordBreak(s.substring(str.length(), s.length()), set);
            }
        }
        return flag;
    }
}

// dp　的做法
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 字符串 dp　问题 flag[i] 表示了从　0 到 i 的位置字符串开头由 wordDict 来进行组合得到
        Set<String> set = new HashSet<>(wordDict);
        //　状态的定义
        boolean[] flag = new boolean[s.length() + 1];
        flag[0] = true;
        for (int i = 1; i <= s.length(); i++) { // 由于 subString 切分的问题, i 从 1 开始
            for (int j = 0; j < i; j++) { 
            	// j 将外层切分的字符串再次进行切分, 左侧的字符串可以数组直接判断, 右侧根据 dict 判断
                if (flag[j] && set.contains(s.substring(j, i))) {
                    flag[i] = true;
                    break;
                }
            }
        }

        return flag[s.length()];
    }
}
