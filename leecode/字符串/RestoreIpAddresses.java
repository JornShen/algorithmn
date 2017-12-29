/*
leetcode 93:

Given a string containing only digits, restore it by returning all possible valid IP address combinations.
For example:
Given "25525511135",
return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

class Solution {
    private List<String> ipAddr = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) return ipAddr;
        cutIpAddr(new ArrayList<>(), s, 0);
        return ipAddr;
    }
    public void cutIpAddr(List<String> list, String s, int pos) {
        if (pos >= s.length() && list.size() == 4) {
            addIp(list);
        }
        if (list.size() > 4 || pos >= s.length()) return;
        if (s.charAt(pos) == '0') {
            list.add("0");
            cutIpAddr(list, s, pos + 1);
            list.remove(list.size() - 1);
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (pos + i <= s.length()) {
                String tmp = s.substring(pos, pos + i);
                if (Integer.parseInt(tmp) <= 255) {
                    list.add(tmp);
                    cutIpAddr(list, s, pos + i);
                    list.remove(list.size() - 1);
                }
            }
        }
    }
    public void addIp(List<String> l) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < l.size(); i++) {
            if (i == 0) {
                buffer.append(l.get(0));
            } else {
                buffer.append(".").append(l.get(i));
            }
        }
        ipAddr.add(buffer.toString());
    }
}

// 别人的代码，　思路基本一致，但是数据结构的选择比较合理．
class Solution {
    public void helper(List<String> ret, String s, int pos, int[] curr, int ind){
        if(ind==4 && pos==s.length()){
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<4;i++){
                if(sb.length()>0) sb.append('.');
                sb.append(curr[i]);
            }
            ret.add(sb.toString());
        }else if(ind<4 && pos<s.length()){
            int n = 0;
            for(int i=pos;i<s.length() && i<pos+3;i++){
                n*=10;
                n+=(s.charAt(i)-'0');
                if(n>255) return;
                curr[ind] = n;
                //　往下递归
                helper(ret,s,i+1,curr,ind+1);
                if(n==0) return;
            }
            curr[ind] = 0;
        }
    }
    
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<>();
        helper(ret,s,0,new int[4], 0);
        return ret;
    }
}