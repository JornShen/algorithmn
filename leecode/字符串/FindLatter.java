/*
leetcode  126. Word Ladder II
Given two words (beginWord and endWord), and a dictionary's word list, 
find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

*/

／／ 之前的做法，尽管做了优化，但还是超时了
class Solution {
    private List<List<String>> lists = new ArrayList<>();
    private int min = Integer.MAX_VALUE;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 超时做法
        if (!wordList.contains(endWord)) return lists;
        List<String> pro = new ArrayList<>();
        pro.add(beginWord);
        Set<String> set = new HashSet<>(wordList);
        set.remove(beginWord);
        findLatter(set, pro, beginWord, endWord);
        return lists;
    }
    
    public void findLatter(Set<String> l, List<String> process, String pre, String end) {
        if (pre.equals(end)) {
            // 转换结束
            if (process.size() == min) {
                lists.add(new ArrayList<>(process));
            } else if (process.size() < min) {
                min = process.size();
                lists.clear();
                lists.add(new ArrayList<>(process));
            }
            return;
        }
        if (l.size() == 0) return;
        if (min <= process.size()) return; // not the shortest path
        char[] p = pre.toCharArray(); // find next word
        for (int i = 0; i < p.length; i++) {
            char tmpChar = p[i];
            for (int j = 0; j < 26; j++) {
                if ('a' + j == tmpChar) continue;
                p[i] = (char) ('a' + j);
                String tmp = new String(p);
                if (l.contains(tmp)) {
                    l.remove(tmp);
                    process.add(tmp);
                    findLatter(l, process, tmp, end);
                    process.remove(tmp);
                    l.add(tmp);
                }
            }
            p[i] = tmpChar;
        }
    }
}

// BFS  遍历
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return new ArrayList<>();
        if (beginWord == null || beginWord == null) return  null;
        Queue<String> q = new LinkedList<>();
        Set<String> set = new HashSet<>(wordList); //　此处采用 set 加快查询的速度
        // 存储每个单词对应的路径
        HashMap<String, List<List<String>>> map = new HashMap<>();
        boolean find = false;
        List<List<String>> list = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        list.add(path);
        map.put(beginWord, list); //到达key的所有可能list的路径
        q.offer(beginWord);
        while (!q.isEmpty()) {
            int size = q.size();
            // 一个hasmap来记录路径中的单词，另外，
            // 每一个单词对应的value是一个string的list, 记录到达此单词的所有的可能的路径。
            HashMap<String, List<List<String>>> mapTmp = new HashMap<>();
            for (int i = 0; i < size; i++) {
                String str = q.poll();
                char[] strTmp = str.toCharArray();
                for (int j = 0; j < strTmp.length; j++) {
                    char tmp = strTmp[j];
                    // 用　26 个字母进行替换
                    for (int k = 0; k < 26; k++) {
                        if ('a' + k == tmp) continue;
                        strTmp[j] = (char) ('a' + k);
                        String s = new String(strTmp);
                        //  重复的单词，不需要计算.
                        // 必须要在字典中出现, 并且不是最后一个字符.
                        if (map.containsKey(s) || (!set.contains(s) && !s.equals(endWord))) continue;

                        // 比较保险, 同一个单词,　若替换不同的位置的字母, 不可能出现相同的字符串
                        // 找出 所有到达 str 的路径, 整个过程相当于在保存从树根到叶子节点的所有的路径
                        List<List<String>> pre = map.get(str);
                        List<List<String>> curList = mapTmp.get(s); //　从临时中获取

                        if (curList == null) { //　不存在就创建
                            curList = new ArrayList<>();
                            mapTmp.put(s, curList);
                            q.offer(s);
                        }

                        for (List<String> pathPre : pre) {
                            List<String> pathNew = new ArrayList<>(pathPre);
                            pathNew.add(s);
                            curList.add(pathNew);
                        }

                        if (s.equals(endWord)) find = true;
                    }
                    strTmp[j] = tmp;
                }
            }
            if (find) return mapTmp.get(endWord);
            // 如果本层找到一个单词比如Word，本层的其它单词如果也可以到达Word，我们仍然要把它的解都记下来
            map.putAll(mapTmp);
        }
        return new ArrayList<>();
    }
}
