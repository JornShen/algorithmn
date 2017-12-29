/*
leetcode 140. Word Break II

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
add spaces in s to construct a sentence where each word is a valid dictionary word. 
You may assume the dictionary does not contain duplicate words.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

*/

// 在前一道题目的基础上进行改进，　但是超时了。
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Map<Integer, Set<String>> map = new HashMap<>();
        Set<String> words = new HashSet<>();
        boolean[] flag = new boolean[s.length() + 1];
        flag[0] = true;
        words.add("");
        map.put(0, words);
        for (int i = 1; i <= s.length(); i++) { // 由于 subString 切分的问题, i 从 1 开始
            for (int j = 0; j < i; j++) {
             　　　// j 将外层切分的字符串再次进行切分, 左侧的字符串可以数组直接判断, 右侧根据 dict 判断
                if (flag[j] && set.contains(s.substring(j, i))) {

                    String addition =  s.substring(j, i);
                    flag[i] = true;
                    Set<String> pre = map.get(j);
                    Set<String> newOne;

                    if (!map.containsKey(i)) { // 新创建
                        newOne = new HashSet<>();
                        map.put(i, newOne);
                    } else {
                        newOne = map.get(i);
                    }
                    // 重新进行拼接
                    for (String str : pre) {
                        if (str.equals("")) newOne.add(str + addition);
                        else newOne.add(str + " " + addition);
                    }

                }
            }
        }
        if (!map.containsKey(s.length())) return new ArrayList<>();
        return new ArrayList<>(map.get(s.length()));
    }
}


// 自己写的 dfs 超时
class Solution {
    
    private Set<String> allWords;
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        allWords = new HashSet<>();
        dfs2(s, new HashSet<>(wordDict), "");
        return new ArrayList<>(allWords);
    }
    
    public void dfs2(String s, Set<String> set, String con) {
        if (s.equals("")) {
            allWords.add(con);
            return;
        }
        for (String str : set) {
            if (s.startsWith(str)) {
                dfs2(s.substring(str.length()), set, con.equals("") ? str : con + " " + str);
            }
        }
    }

}



//  参考别人的算法，　map存放之前的结果， 减枝
class Solution {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, new HashSet<>(wordDict), new HashMap<>());
    }

    public List<String> dfs(String s, Set<String> wordDict, Map<String, List<String>> map) {
    	
        if (map.containsKey(s)) return map.get(s);
        List<String> list = new LinkedList<>();
        if (s.length() == 0) {
            list.add("");
            return list; // 遍历到末尾了
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> subString = dfs(s.substring(word.length()), wordDict, map);
                for (String sub : subString) {
                    list.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put(s, list);
        return list;
    }
}
