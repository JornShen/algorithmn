/****

题目描述：

请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，]
而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。
例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配

经典问题 

****/

// 初始做法 (of me)

public class Solution {
    public boolean match(char[] str, char[] pattern)
    {
        int i = 0, j = 0;
        int flag = 2; // 2 普通匹配 包括 . 匹配  1 .* 匹配 0 a* 匹配
        if(pattern.length == 0)  return str.length == 0;

        while (i < str.length && j < pattern.length) {

            if (j < pattern.length - 1) { //  预读一位
                if (pattern[j + 1] == '*') {
                    flag = (pattern[j] == '.') ? 1 : 0;
                }
            }
            if (flag == 0) { // 带'*' 匹配
                if (str[i] == pattern[j]) {
                    i++;
                } else {
                    flag = 2;
                    j += 2;
                    if (j < pattern.length && pattern[j] == pattern[j - 2]) j += 1;
                }
            } else if (flag == 2) {
                if (str[i] == pattern[j] || pattern[j] == '.') {
                    i++;
                    j++;
                } else {
                    return false;
                }
            } else { // flag = 1
                // .*a abc
                if (j + 2 >= pattern.length) return true;
                if (pattern[j + 2] == str[i]) {
                    j += 3;
                    i++;
                    flag = 2; // 变回原来的
                } else {
                    i++;
                }
            }
        }
        
        if (flag == 0) {
            j += 2;
            if (j < pattern.length && pattern[j] == pattern[j - 2]) j += 1;
        }

        if (j ==  pattern.length - 2 && pattern[j + 1] == '*') {
            return  true;
        }
        if (i < str.length || j < pattern.length) {
            return false;
        } else {
            return true;
        }
    }
}

// ------------- 没有做出来，原因可以见以下的参考代码 ------------------

  /***
     * 当模式中的第二个字符不是“*”时：
     1、如果字符串第一个字符和模式中的第一个字符相匹配，那么字符串和模式都后移一个字符，然后匹配剩余的。
     2、如果 字符串第一个字符和模式中的第一个字符相不匹配，直接返回false。

     而当模式中的第二个字符是“*”时：
     如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配。如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式：
     1、模式后移2字符，相当于x*被忽略；
     2、字符串后移1字符，模式后移2字符；
     3、字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位；

     */
     public boolean match(char[] str, char[] pattern) {
         if (str == null || pattern == null) {
             return false;
         }
         int startIndex = 0;
         int patternIndex = 0;
         return matchCore(str, startIndex, pattern, patternIndex);
     }

    public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        // 两个匹配到头
        if (str.length == strIndex && pattern.length == patternIndex) return true;
        // 模式匹配到头, 串到头, 但是匹配没有到头.
        if (str.length != strIndex && pattern.length == patternIndex) return false;

        // 模式没有匹配到头
        // 第二个字符是'*'
        if (patternIndex < pattern.length - 1 && pattern[patternIndex + 1] == '*') {
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) ||
                    (pattern[patternIndex] == '.' && strIndex != str.length)) {
                // 核心关键代码 分三种情况,　以上代码没有做出来的关键原因
                return matchCore(str, strIndex, pattern, patternternIndex + 2) // x* 不匹配字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2) // 仅仅匹配一个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex); // 继续的匹配下一个字符
            } else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }
        // 第二个字符不是'*', 直接匹配
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
                || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }
