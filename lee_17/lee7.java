
/*******************
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*********************/

class Solution {
    // 考虑个数
    private Map<Character, List<Integer>> map = new HashMap<>();
    private Map<Character, Integer> cal =  new HashMap<>(); // calculate the num of character
    private int finalStart = -1;
    private int len = Integer.MAX_VALUE;
    private int num = 0;
    private List<Integer> list = new ArrayList<>();
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        for (char c : t.toCharArray()) {
            if (map.containsKey(c)) {
                int num = cal.get(c);
                cal.put(c, num + 1);
            } else {
                map.put(c, new ArrayList<>());
                cal.put(c, 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                update(s.charAt(i), i, t.length());
            }
        }
        if (finalStart == -1) return "";
        return s.substring(finalStart, finalStart + len);
    }

    private void update(char c, int i, int n) {
        list.add(i);
        List<Integer> loc = map.get(c); // get c last location in strin
        if (loc.size() ==  cal.get(c)) {
            list.remove(new Integer(loc.get(0))); // update the min one location in string
            loc.remove(0);
        } else {
            // first time
            num++;
        }
        if (num == n) {
            if (i - list.get(0) + 1 < len) {
                len = i - list.get(0) + 1;
                finalStart = list.get(0);
            }
        }
        loc.add(i);
    }
}

//　以上是本人的写法，　算法复杂度有点高，　核心集合是 list, 维护了一个，　关注该集合的最小的位置。
// 

// -------------------　高效的写法 --------------------------

class Solution {
    public String minWindow(String s, String t) {
       int m = s.length(),n=t.length();
        int start=0,len=Integer.MAX_VALUE;
        int[]cnt=new int[128];
        char[]ss=s.toCharArray();
        char[]tt=t.toCharArray();
        for(char c:tt){
            cnt[c]++;
        }
        int begin=0,end=0;
        while(end<m){
            // 到左边的节点，进行统计
            if(cnt[ss[end++]]-- >0)
                n--;
            //　统计长度
            while(n==0){
                if(len>end-begin){
                    start=begin;
                    len=end-begin;
                }
                //-------　核心步骤　---------
                if(++cnt[ss[begin++]]>0) // 找到左边的起始节点
                    n++;
                //----------------
            }
        }
        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
    }	
}

// cnt['.']＝１为当前的左边节点，　<0 部分值为累加的值。　在核心步骤会跳过 
// 算法比较精妙