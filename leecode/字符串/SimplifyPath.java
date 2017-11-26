/*

leetcode 71:

Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".


*/

// 栈的思想 
class Solution {

    public String simplifyPath(String path) {
        if (path == null || path.equals("")) return "";
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");
        for (int i = 0; i < paths.length; i++) {
            if (paths[i].equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!paths[i].equals("") && !paths[i].equals(".")) {
                stack.push(paths[i]);
            }
        }
        if (stack.isEmpty()) return "/";
        StringBuffer buffer = new StringBuffer();
        for (String s : stack) {
            buffer.append("/").append(s);
        }
        return buffer.toString();
    }
}

//　别人的做法, 算法比较巧妙．
class Solution {
    public String simplifyPath(String path) {
        char [] chs = path.toCharArray();
        int i = 0, j=0; // i j用来确定// 中间词
        int end = 0;　//　关键指针
        while(i< chs.length){
            while(j < chs.length && chs[j]　!=　'/' ) j++; // 截出一个单词
            String str = "";
            if(j　-　i　>　1) str = path.substring(i　+　1, j);
            if(str.equals("..")){
                if(end>=1) {
                   end--;
                   while(end　>=　0 && chs[end]　!=　'/') end--;
                }
            }else if(str.equals(".")){
            }else if(!str.equals("")){
                int temp = i;
                while(temp　<　j){
                    chs[end++] = chs[temp++];
                }
            }
            i = j; 
            j= i　+　1;
        }
        if(end == 0) return "/";
        return new String(chs).substring(0, end);
    }
}