
/*
leetcode 43:

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

*/

// 我的做法，　思路比较简单，　按照普通的乘法的方式

class Solution {
    public String multiply(String num1, String num2) {
        int[] result = new int[num1.length() + num2.length()];
        int[] c1 = new int[num1.length()];
        int[] c2 = new int[num2.length()];
        // 将　串　转化为　数字
        for (int i = 0;  i < c1.length; i++) {
            c1[i] = num1.charAt(i) - '0';
        }
        for (int i = 0;  i < c2.length; i++) {
            c2[i] = num2.charAt(i) - '0';
        }
        // 计算
        int from = result.length - 1;
        for (int i = c2.length - 1; i >= 0; i--) {
            int temp = 0, pos = from;
            for (int j = c1.length - 1; j >= 0; j--) {
                int mul = c2[i] * c1[j] + temp + result[pos];
                result[pos] = mul % 10;
                temp = mul / 10;
                pos--;
            }
            // 将进位补齐
            while (temp != 0) {
                int sum = result[pos] + temp;
                result[pos] = sum % 10;
                temp = sum / 10;
                pos--;
            }
            from--;
        }
        // 转化为串
        StringBuffer buffer = new StringBuffer();
        boolean flag = true;
        for (int i = 0; i < result.length; i++) {
            if (flag && result[i] == 0) continue;
            buffer.append(result[i]);
            flag = false; // 过滤开头的0
        }
        if (buffer.length() == 0) return "0";
        return buffer.toString();
    }
}

// 　别人的做法，　进位的处理方式不同．

class Solution {
    /*
	    5  len1 = 1
	    12 len2 = 2
	    temp1 = 5
	    temp2 = 2
	    res[0 + 0] = 10
	    res[0 + 1] = 5
	    res[0] = 10
    */
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] res = new int[len1 + len2];
        char[] numArr1 = num1.toCharArray();
        char[] numArr2 = num2.toCharArray();
        for(int i = len1 - 1; i >= 0; i--){
            for(int j = len2 - 1; j >= 0; j--){
                int temp1 = numArr1[i] - '0';
                int temp2 = numArr2[j] - '0';
                res[len1 - 1 - i + len2 - 1 - j] += temp1 * temp2;
            }
        }
        int carry = 0;
        for(int i = 0; i < res.length; i++){
            res[i] += carry;
            carry = 0;
            // 此处需要理解一下．　res[i] 不可能大于　100,　只能是　1 ~ 100 之间．
            if(res[i] >= 10){
                carry = res[i] / 10;
                res[i] %= 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        int count = res.length - 1;
        while(count >= 0 && res[count] == 0) count--;
        if(count < 0) return "0";
        for(int i = count; i >= 0; i--){
            sb.append(res[i]);
        }
        return sb.toString();
    }
}