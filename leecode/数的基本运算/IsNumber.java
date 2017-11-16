/*

leetcode 65:

Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. 
You should gather all requirements up front before implementing one.

*/

// 我的解法，　按照 e 来进行区分,　判断左右半边的数是否是正数或者数字　
// 本题目，　包括了 .2 , 1. 这也是数字的情况．
public class Solution {
    public boolean isNumber(String s) {
        boolean lFlag =  true,rFlag = true,mFlag = true;
        int left = 0, right = 0;
        s = s.trim();
        if (s.equals(".") || s.equals("")) return false;
        char[] str = s.toCharArray();
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
            if (from > to) return 0;
            if(str[from] == '+' || str[from] == '-') from++;
            if (from > to) return 0;
            int floatFlag = 0;
            int i = 0;
            if (str[from] == '.' && from == to) return 0;
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

// 别人的解法,　好代码，　设计非常巧妙！

/*We start with trimming.
    If we see [0-9] we reset the number flags.
    We can only see . if we didn't see e or ..
   We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
    We can only see + and - in the beginning and after an e
    any other character break the validation.
*/
public class Solution {
    public boolean isNumber(String s) {
        
        s = s.trim();// 切除首尾的空格

        boolean beforeE = false;
        boolean afterE = true;
        boolean eSeen = false;
        boolean dotSeen = false;

        for(int i = 0; i < s.length(); ++i) {

            if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                beforeE = true;
                afterE = true;
            } else if(s.charAt(i) == 'e') {
                if (eSeen || !beforeE) {　// 　e1　这种情况
                    return false;
                }
                eSeen = true;
                afterE = false;
            } else if(s.charAt(i) == '.') {
                if(dotSeen || eSeen) {　// 前面已经有一个　., .　不能放在 e 的后面
                    return false;
                }
                dotSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i == 0) {
                    continue;
                } else if(s.charAt(i - 1) == 'e') {
                    continue;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        }

        return beforeE && afterE;
    }
}

