/*
leetcode 49:

Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.

*/

//　我的做法，　借助　排序　然后比较
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> all = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String temp = new String(c);
            if (map.containsKey(temp)) {
                List<String> l = map.get(temp);
                l.add(strs[i]);
            } else {
                List<String> l = new ArrayList<>();
                l.add(strs[i]);
                map.put(temp, l);
                all.add(l);
            }
        }
        return all;
    }
}

//　别人的代码，比较巧妙，　借助　素数的乘积　的　唯一性　　
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
          int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
          31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};//最多10609个素数 
            List<List<String>> res = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            for (String s : strs) {
                int key = 1;
                for (char c : s.toCharArray()) {
                    key *= prime[c - 'a'];
                }
                List<String> t;
                if (map.containsKey(key)) {
                    t = res.get(map.get(key));
                } else {
                    t = new ArrayList<>();
                    res.add(t);
                    map.put(key, res.size() - 1);
                }
                t.add(s);
            }
            return res;
    }
}