/****

汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,
要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！

****/


public class Solution {
    public String LeftRotateString(String str,int n) {
        
       /*** char[] s = str.toCharArray();
        
        if(s.length == 0) return "";// 考虑特殊情况
        
        int from = n % s.length;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < s.length; i++){
            sb.append(s[from]);
            from++;
            from = from % s.length;
        }
        return sb.toString();
       ***/
        // 以上为第一次做法,偏离考点
        // ----------------------------
        // 巧解 1
        /***
        int len = str.length();
        if(len == 0) return "";
        str += str;
        n = n % len;
        return  str.substring(n, len+n); //substring 左闭右开
        ***/ 
        // --------------------- 
        
        //巧解 2  翻转
        /***
        
            (1)reverse(0,i-1);
            (2)reverse(i,n-1);
            (3)reverse(1,n-1);
            例如原序列：abcdefg，循环左移3位：
            (1) cba defg 左边翻转
            (2) cba gfed 右边翻转
            (2) defgabc  整体翻转
            
        ***/
        int len = str.length();
        if(len == 0) return "";
        n = n % len;
        StringBuffer sb1 = new StringBuffer(str.substring(0,n));
        StringBuffer sb2 = new StringBuffer(str.substring(n,len));
        return sb1.reverse().append(sb2.reverse().toString()).reverse().toString();  
    }
}