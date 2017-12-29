/*
leetcode 131. Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]

*/

class Solution {
    
    private List<List<String>> allPal = new ArrayList<>();
        public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) return allPal;
        subPalindrome(new ArrayList<>(), s, 0);
        return allPal;
    }
    
    public void subPalindrome(List<String> l, String str, int start) {
        if (start >= str.length()) {
            allPal.add(new ArrayList<>(l));
            return;
        }
        for (int i = start + 1; i <= str.length(); i++) {
            String tmp = str.substring(start, i);
            if (i - start == 1 || isPal(tmp)) {
                l.add(tmp);
                subPalindrome(l, str, i);
                l.remove(l.size() - 1);
            }
        }
    }

    public boolean isPal(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}

// 另外一种写法, 思路差不多，只是我的方法在遍历的时候进行判断是否是回文的字符串
public class Solution {  

    public ArrayList<ArrayList<String>> partition(String s) {  
        int[][] dp=new int[s.length()][s.length()];  
        ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();  
        ArrayList<String> r=new ArrayList<String>();  
        for(int i=0;i<s.length();i++) {  
            for(int j=i;j<s.length();j++) {  
                int k=0;  
                for(;k<(j-i+1)/2;k++) {  
                    if(s.charAt(i+k)!=s.charAt(j-k)) break;  
                }  
                  
                if(k == (j-i+1)/2) {  
                    dp[i][j]=1;  
                }  
            }  
        }  
        dfs(0,s,dp,r,result);  
        return result;  
    }  
      
	public void dfs(int i,String s,int[][] dp,ArrayList<String> r,ArrayList<ArrayList<String>> result) {  
	        
	        if(i==s.length()) {  
	            ArrayList<String> t=new ArrayList<String>(r);  
	            Collections.reverse(t);  
	            result.add(t);  
	            return;  
	        }  
	          
	        for(int j=i;j<s.length();j++) {  
	            if(dp[i][j]==1) {  
	                r.add(0,s.substring(i, j+1));  
	                dfs(j+1,s,dp,r,result);  
	                r.remove(0);  
	            }  
	        }  
	    }  
	} 
}