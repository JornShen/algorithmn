

/***
请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
*/

public class Solution {
    

    // 正则表达式：[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?

    public boolean isNumeric(char[] str) {
        boolean lFlag =  true,rFlag = true,mFlag = true;
        int left = 0, right = 0;
        for(int i = 0; i < str.length; i++){
            if(str[i] == 'e'||str[i] == 'E'){ // 是否含有e
                left = isNum(str,0,i - 1);
                right = isNum(str,i + 1,str.length - 1);
                if(left >= 1 && right == 1){ // 左侧必须是数， 右侧必须是整数
                    return true;
                }else{
                    return false;
                }
            }
            if(str[i] == '.' || str[i] == '+' || str[i] == '-') continue;
            if(str[i] > '9' || str[i] < '0')  return false;
        }
        return isNum(str, 0, str.length - 1) == 0 ? false : true;
    }
    
    public int isNum(char[] str,int from, int to){
            // 判断是否是数字 0 不是数字 1 整数 2 小数
            if(from > to) return 0;
            if(str[from] == '+' || str[from] == '-') from++;
            int floatFlag = 0;
            int i = 0;
            for(i = from; i <= to; i++){
                if(str[i] == '.'){
                    floatFlag++;
                    continue;
                }
                if(str[i] > '9' || str[i] < '0')  return 0;
            }
            if(from > to || floatFlag > 1) return 0;
            return floatFlag == 0 ? 1 : 2;
    }
}