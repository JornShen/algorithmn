/*

leetcode 28: 

Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

*/

// 暴力算法
class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        if (haystack.length() == 0) return needle.length() == 0 ? 0 : -1;
        if (needle.length() == 0) return 0;
        if (needle.length() > haystack.length()) return -1;
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        for (int i = 0; i < haystack.length(); i++) {
            if (h[i] == n[0]) {
                int t = i + 1;
                for (int j = 1; j < needle.length(); j++) {
                    if (t >= haystack.length()) break;
                    if (n[j] != h[t++]) {
                        t--;
                        break;
                    }
                }
                if (t == i + needle.length()) {
                    return i;
                }
            }
        }
        return -1;
        
    }
}

// 调用函数

class Solution {
    public int strStr(String haystack, String needle) {
        int index = haystack.indexOf(needle);
        return index;
    }
}
