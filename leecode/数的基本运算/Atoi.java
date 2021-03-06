/*
leetcode 8:

Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

*/

class Solution {
    public int myAtoi(String str) {
        if (str == null || "".equals(str)) return 0; // 最好使用 .equals 方法
        boolean flag = true;
        str = str.trim(); // 去掉空格
		if (str.charAt(0) == '-') {
			flag = false;
			str = str.substring(1, str.length());
		} else if (str.charAt(0) == '+') {
            flag = true;
            str = str.substring(1, str.length());
        }
		long sum = 0;
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			int temp = c[i] - '0';
			if (temp > 9 || temp < 0) {
				if (sum > Integer.MAX_VALUE) {
					return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
				} else {
					return (int) (flag ? sum : -sum); 
				}
			} else {
				sum = sum * 10 + temp;
			}
            if (sum > Integer.MAX_VALUE) {
				return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
		}
		
	    return (int)(flag ? sum : -sum);  // 负数  
    }
}

// 需要注意 一些特殊情况，比如 超过 最大的4个字节的的整数需要按其最大值来输出
// 取出空格 ， 当有不同的字符的时候， 输出已经转化的字符
