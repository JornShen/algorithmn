/*****


题目描述: 

字典序在s1和s2之间的，长度在len1到len2的字符串的个数，结果mod 1000007。

输入描述:

每组数据包涵s1（长度小于100），s2（长度小于100），len1（小于100000），len2（大于len1，小于100000）


*********/ 

/****

解题思路: 字典序列是按照字母的顺序,需要根据长度来定.

26 * 上一轮(长度的距离) + 这一轮的距离 : 迭代方程式 

只有s1的长度和s2的长度相等时，才会多加一个s2,否则不会多加，也就不用减1。
以下测试用例:

a ac 1 2 -->aa, ab -->2

aa ac 2 3 --> ab, aa(a-z), ab(a-z)-->53, s1 == s2 的时候,此种方式会不一样.

***/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        calculateString();
    }
    
    public static void calculateString() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String from = in.next();
            String to = in.next();
            int len1 = in.nextInt();
            int len2 = in.nextInt();
            int max = from.length() > to.length() ? to.length() : from.length();
            char[] s = new char[max], t = new char[max];
            Arrays.fill(s, 'a');
            Arrays.fill(t, 'a');
            System.arraycopy(from.toCharArray(), 0, s, 0, from.length());
            System.arraycopy(to.toCharArray(), 0, t, 0, to.length());
            int sum = 0;
            int result = 0;
            for (int i = 0; i < len2; i++){
                if (i < max) {
                    result = result * 26 + t[i] - s[i];
                } else {
                    result = result * 26;
                }
                result %= 1000007;
                if (i >= len1 - 1) {
                    sum += result;
                    sum %= 1000007;
                }
            }
            // 此处需要这样处理是因为 只有s1的长度和s2的长度相等时，
            // 才会多加一个s2,否则不会多加，也就不用减1
            if (from.length() == to.length()) {
                sum = (sum - 1)  % 1000007;
            }
            System.out.println(sum);
        }
    }
    
    
}

