/*

leetcode 14:

Write a function to find the longest common prefix string amongst an array of strings.

*/

// 思路比较清晰
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return ""; 
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        char temp;
        while (true) {
            if (i < strs[0].length()) {
                temp = strs[0].charAt(i);
            } else {
                break;
            }
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != temp) {
                    return buffer.toString();
                } 
            }
            buffer.append(temp);
            i++;
        }
        return buffer.toString();
    }
}

// 别人的做法, 不断切割字符串, 缩小范围, start with 方法
public String longestCommonPrefix(String[] strs) {
    if(strs.length == 0) return "";
    String pre = strs[0];
    for(int i = 1; i < strs.length; i++){
        while(strs[i].startsWith(pre) != true){
            pre = pre.substring(0, pre.length() - 1);
        }
    }
    return pre;
}
