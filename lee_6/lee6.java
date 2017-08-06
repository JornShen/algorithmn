/*****
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9]
*****/

public class Solution {
    
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (words == null || words.length == 0) return list;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                map.replace(words[i], map.get(words[i]) + 1);
            } else {
                map.put(words[i], 1);
            }
        }
        int width = words[0].length();
        int len = words.length * words[0].length();
        for (int i = 0; i <= s.length() - len; i++) {
            if (judge(s.substring(i), new HashMap<>(map), width, words.length)) {
                list.add(i);
                //i += width - 1;
            }
        }
        return list;
    }

    public boolean judge(String s, Map<String, Integer> map, int k, int c) {
        String temp;
        for (int i = 0; i < c; i++) {
            temp = s.substring(i * k, i * k + k);
            if (!map.containsKey(temp)) {
                return false;
            }
            if (map.get(temp) == 0) {
                return false;
            } else {
                map.replace(temp, map.get(temp) - 1);
            }
        }
        return true;
    }
    
}

// 一开始使用了set来区分，后面发现是错误的，应该使用map，因为有的单词可能重复。算法复杂度有点高


