/*
leetcode 187. Repeated DNA Sequences

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, 
for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify 
repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

*/

// 思路基本相同，　但是映射的空间需要还可以优化
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> pool = new HashSet<>();
        Set<String> set = new HashSet<>();
        for (int i = 10, j = 0; i <= s.length(); i++, j++) {
            String tmp = s.substring(j, i);
            if (pool.contains(tmp)) set.add(tmp);
            else pool.add(tmp);
        }

        return new ArrayList<>(set);
    }
}


// 普遍的做法，　看不太懂

class Solution{//11ms
    public List<String> findRepeatedDnaSequences(String s){
        
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return res;
        }
        
        //对字母进行编码
        char[] map = new char[256];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;
        int mask = 0xfffff;　//20bit,10个字母，每个字母占2bit
        int val = 0;
        
        char[] schar = s.toCharArray();
        for (int i = 0;i < 9 ;i ++ ) {//对前9位进行编码
            val = (val << 2) | (map[schar[i]] & 3);
        }

        byte[] bytes = new byte[1 << 20]; // 设置映射集合
        for (int i = 9;　i < schar.length;　i++) {
            val = ((val << 2) & mask) | ((map[schar[i]]) & 3);//编码
            if (bytes[val] == 1) {
                res.add(String.valueOf(schar,i - 9,10));
            }
            if (bytes[val] < 2) {
                bytes[val]++;
            }
        }
        return res;
    }
}

//　另一种　更清晰的做法
// 四个字母用两位就能表示，　十个字符就只需要用 20 个字符进行表示
public class Solution {  
    public List<String> findRepeatedDnaSequences(String s) {  
        List<String> res = new ArrayList<String>();  
        if(s　==　null || s.length() < 11) return res;  
        int hash = 0;  
        Map<Character, Integer> map = new HashMap<Character, Integer>();  
        map.put('A', 0);  
        map.put('C', 1);  
        map.put('G', 2);  
        map.put('T', 3);  
        Set<Integer> set = new HashSet<Integer>();　//　只出现一次
        for(int i　=　0; i　<　s.length(); i++) {  
            char c = s.charAt(i);  
            if(i　<　9) hash = (hash　<<　2) + map.get(c);  
            else {  
            	
                hash = (hash　<<　2) + map.get(c);  
                hash &= (1　<<　20) - 1;　// 取后二十位  

                if(set.contains(hash))　res.add(s.substring(i　-　9, i　+　1));   
                else set.add(hash);  
            }  
        }  
        return res;  
    }  
}