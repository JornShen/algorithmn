/**************

输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
输入描述:
输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。

***************/

import java.util.*;
public class Solution {
    private Set<String> array = new TreeSet<>(); 　// 采用　TreeSet 解决重复问题
    public ArrayList<String> Permutation(String str) {
        char[] s = str.toCharArray(); 
        // 全排列
        permutation(s,　0,　s.length　-　1);
        return new ArrayList<String>(array);
    }
    // 递归思想
    public void permutation(char[] str,int start,int end){
        if(start == end){
            array.add(new String(str));
            return;
        }
        char temp;
        //　字符串的全排列问题
        for(int i　=　start;i <= end; i++){ // 关键是理解这一句
            // 交换 str[start] <--> str[i]
            temp = str[start];
            str[start] = str[i];
            str[i] = temp;
            
            // 向下递归
            permutation(str,　start+1,　end);
            // 恢复
            temp = str[start];
            str[start] = str[i];
            str[i] = temp;  
        }
    }
}


//**********************************************
import java.util.*;
public class Solution {

    private ArrayList<String> list = new ArrayList<>();

    public ArrayList<String> Permutation(String str) {
        char[] s = str.toCharArray();
        Arrays.sort(s);
        perm(s, 0, s.length - 1);
        Collections.sort(list); // collection　排序解决	
        return list;
    }

    public void perm(char[] s, int i, int n) {
        if (i == n) {
            list.add(new String(s));
        } else {
            for (int j = i; j <= n; j++) { //  注意等号
            	// 无法保证　字典序
                if (isSwap(s, i, j)) {
                    // 可以交换
                    swap(s, i, j); // 固定i,　向下递归,　对 i + 1 位到 n　进行递归
                    perm(s, i + 1, n);
                    swap(s, i, j);
                }
            }
        }
    }

    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    // 相同的字符进行了多次交换
    //  在交换时进行判断，如果后面的字符有重复就不交换。
    // 当第i个字符和第j个字符交换位置时，判断范围是[i,j)是否有和j重复的数
    public boolean isSwap(char[] s, int start, int end) {
        //　判断范围是[i,j)是否有和j重复的数,  有就不交换
        // 没有就不交换
        for (int i = start; i < end; i++) {
            if (s[i] == s[end]) {
                return false;
            }
        }
        return true;
    }
}
