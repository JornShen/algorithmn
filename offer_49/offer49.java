
/***
将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
 数值为0或者字符串不是一个合法的数值则返回0 
*/

public class Solution {
   public int StrToInt(String str) {
        char[] s = str.toCharArray();
        if(s.length == 0) return 0;
        int sum = 0,flag = 0,low;
        if(s[0] == '-'){
            flag = -1;
        }else if(s[0] == '+'){
            flag = 1;
        }
        low = 0 + (flag == 0 ? 0:1);
        for(int i = low; i < s.length; i++){
            if(s[i] < '0' || s[i] > '9'){
                return 0;
            }
            sum *= 10;
            sum += s[i] - '0';
        }
        sum *= flag < 0 ? -1 : 1;//添加符号
        return sum;
    }
}