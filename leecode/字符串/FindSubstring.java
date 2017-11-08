/*****

leetcode 30:

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

// 其他人的解法 好像跟我的差不多
public class Solution {

    public List<Integer> findSubstring(String s, String[] words)  
    {
        List<Integer> res = new ArrayList<>();   
        int strlen = s.length(), wordlen = words[0].length(), totallen = wordlen * words.length;
        if (strlen < totallen) return res;
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) map.put(word, map.getOrDefault(word, 0) + 1);
        for (int i = 0; i < wordlen; i++)
        {
            int wsize = 0;
            Map<String, Integer> map1 = new HashMap();
            for (int j = i; j <= strlen + wsize - totallen; j += wordlen)
            {
                String word = s.substring(j, j+wordlen);
                if (map.containsKey(word))
                {
                    if (map.get(word) > map1.getOrDefault(word,0))
                    {
                        map1.put(word,map1.getOrDefault(word,0) + 1);
                        wsize += wordlen;
                        if (wsize == totallen) 
                        {
                    		int id = j - wsize + wordlen;
                    		res.add(id); // not i
                    		wsize -= wordlen; // update wsize, sliding to next;
                    		String first = s.substring(id,id+wordlen);
                    		map1.put(first, map1.get(first) - 1);
                        }
			
                    }
                    else
                    {
                        
                        int k = j - wsize;
                		String sameword = s.substring(k, k+wordlen);
                		while (!sameword.equals(word)) 
                		{
                			map1.put(sameword, map1.get(sameword) - 1);        			
                			wsize -= wordlen;
                			k += wordlen;
                			sameword = s.substring(k, k+wordlen);
                		}
                    }
  
                }
                
                else if (!map1.isEmpty())
                {
                    
                    map1.clear();
                    wsize = 0;
                }
            }
            
        }
        return res;
    }
}