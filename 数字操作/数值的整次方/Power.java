
/******************

给定一个double类型的浮点数base和int类型的整数exponent。
求base的exponent次方。

*******************/

// 采用 累乘思想, 但是还是不够精湛
public class Solution {
    public double Power(double base, int exponent) {
        int i = 1;
        double temp = base;
        while (i <= exponent) {
            i <<= 1;
            temp = temp * temp;
        }
        for (int j = i; j > exponent; j--) {
            if (base != 0) {
                temp /= base;
            }
        }
        return temp;
    }
}

// 根据 乘方 的二进制的位数, 选择 何时进行相乘  
// 举例:10^1101 = 10^0001*10^0100*10^1000 

public class Solution {
    public double Power(double base, int exponent) {
        int i = 1;
        double temp = base;
        double result = 1;
        boolean flag = false;
        if (exponent > 0) {
            flag = true;
        } else {
            exponent = -exponent;
            temp = base == 0 ? 0 : 1 / base;
        }
        while (exponent != 0) {
            if ((exponent & 1) == 1) {
                result = result * temp;
            }
            temp = flag ? temp * base : (base == 0 ? 0 : temp / base);
            exponent >>>= 1;
        }
        return result;
    }
}	