/***
请实现一个函数用来找出字符流中第一个只出现一次的字符。
例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。 
*/
import java.util.*;
public class Solution {
    //Insert one char from stringstream
   /* private StringBuffer sb = new StringBuffer();
    public void Insert(char ch)
    {
        sb.append(ch);
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        char[] c = sb.toString().toCharArray();
        List<Integer> q = new LinkedList<>();
        int num = 0;
        for (int i = 0; i < c.length; i++) {
            num = c[i];
            s[num]++;
            if (s[num] == 1) {
                q.add(Character.getNumericValue(num));// char -> Integer
            }
            if (s[num] == 2) {
                q.remove(Character.getNumericValue(num));
            }
        }
        if (q.isEmpty()) return '#';
        return Character.highSurrogate(q.get(0)); //Integer  -> char
    }
    // 以上是第一次做的时候，无法通过，数组越界
    */
    private int[] count = new int[256];
    private int index = 1;
    public void Insert(char ch)
    {
        if(count[ch] == 0){
            count[ch] = index++; // 统计出现的次数
        }else{
            count[ch] = -1;// 再次出现
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        int temp = Integer.MAX_VALUE;
        char first = '#';
        for(int i = 0; i < 256; i++){
             if(count[i] != 0 && count[i] != -1 && count[i] < temp){//------- 重点
                 first = (char)i;
                 temp = count[i];
             }
        }
        return first;
    }
}