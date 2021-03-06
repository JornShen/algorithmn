/*****************

输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。

******************/

// 采用 循环移位的思想, 带上 & 操作, 判断 1 的个数

public class Solution {
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >>> 1; // 注意此处需要使用 >>> 操作符号
        }
        return count;
    }
}


补充:

java中有三种移位运算符

<<      :     左移运算符，num << 1,相当于num乘以2

>>      :     右移运算符，num >> 1,相当于num除以2

>>>    :     无符号右移，忽略符号位，空位都以0补齐	

区别:

>>表示右移，如果该数为正，则高位补0，若为负	数，则高位补1；

>>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0。

