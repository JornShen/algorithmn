/*
leetcode 127. Word Ladder
Given two words (beginWord and endWord), and a dictionary's word list, 
find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
 Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

*/

// 参考博客，思路比较清晰。

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0; //　不存在该单词
        Set<String> set = new HashSet<>(wordList); // 判断集合中的元素具有互斥性
        LinkedList<String> queue = new LinkedList<>();
        LinkedList<Integer> len = new LinkedList<>();
        queue.add(beginWord);
        wordList.remove(beginWord);
        len.add(1);
        while (queue.size() > 0) {
            String tmp = queue.removeFirst();
            int currentLen = len.removeFirst();
            char[] word = tmp.toCharArray();
            // 将每个字符进行替换，判断 wordlist 中是否存在
            for (int i = 0; i < word.length; i++) {
                char tmpChar = word[i];
                for (int j = 0; j < 26; j++) {

                    if ('a' + j == tmpChar) continue; // 同一个字母没有改变

                    word[i] = (char)('a' + j);
                    String w = new String(word);

                    if (w.equals(endWord)) return currentLen + 1; // 到达最后一个单词

                    if (set.contains(w)) {
                        set.remove(w);
                        queue.add(w);
                        len.add(currentLen + 1);
                    }
                }
                word[i] = tmpChar; // 需要替换回来
            }
        }
        return 0;
    }
}


／／　第一次的做法，超时
public class Solution {
    private List<List<String>> lists = new ArrayList<>();
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 超时做法
        boolean[] wordFlag = new boolean[wordList.size()];
        if (!wordList.contains(endWord)) return lists;
        List<String> pro = new ArrayList<>();
        pro.add(beginWord);
        findLatter(wordFlag, wordList, pro, beginWord, endWord);
        return lists;
    }

    public void findLatter(boolean[] flag, List<String> l, List<String> process, String pre, String end) {
        if (pre.equals(end)) {
            // 转换结束
            if (lists.isEmpty()) lists.add(new ArrayList<>(process));
            else {
                if (lists.get(0).size() == process.size()) lists.add(l);
                else if (lists.get(0).size() > process.size()) {
                    lists.clear();
                    lists.add(new ArrayList<>(l));
                }
            }
            return;
        }
        for (int i = 0; i < flag.length; i++) {
            if (!flag[i]) {
                int dif = 0;
                String now = l.get(i);
                for (int j = 0; j < pre.length(); j++) {
                    if (pre.charAt(j) != now.charAt(j)) dif++;
                }
                if (dif == 1) {
                    // 只有一个不同
                    process.add(now);
                    flag[i] = true;
                    findLatter(flag, l, process, now, end);
                    flag[i] = false;
                    process.remove(process.size() - 1);
                }
            }
        }
    }
}
