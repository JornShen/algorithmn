/*
leetcode 29:

Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.

*/

// 我的做法，　比较麻烦

class Solution {
    public int divide(int dividend, int divisor) {
        
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == 0) return 0;

        boolean flag = false;
        long divisors = divisor;
        long dividends = dividend;
        long temp = divisor;

        if (dividend < 0) {
            if (dividend == Integer.MIN_VALUE) {
                dividends = Integer.MAX_VALUE;
                dividends++;
            } else {
                dividends = -dividend;
            }
        }

        if (divisor < 0) {
            temp = -temp;
            if (divisor == Integer.MIN_VALUE) {
                divisors = Integer.MAX_VALUE;
                divisors++;
            } else {
                divisors = -divisor;
            }
        }
        //　结果是负数
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            flag = true;
        }

        if (dividends < divisors) return 0;

        //-----------------   前期处理　和 判断 ---------------------

        long div = 1;
        // 获取上限制
        while (dividends > divisors) {
            div += div;
            divisors <<= 1;
        }

        long i = divisors;
        while (i > dividends) {
            i -= temp;
            div--;
        }

        if (div >= Integer.MAX_VALUE) return flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        return (int)(flag ? -div : div);
    }
}


／／　别人的做法

class Solution {
    public int divide(int dividend, int divisor) {
    	// 判断符号
		boolean neg = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
		// 转化为　long 类型
		long divd = Math.abs((long)dividend);
		long divs = Math.abs((long)divisor);

		if(divs == 0) return Integer.MAX_VALUE;
		if(divd == 0 || divd < divs) return 0;

		long lans = ldivide(divd, divs);
		if(lans > Integer.MAX_VALUE) return neg?Integer.MIN_VALUE:Integer.MAX_VALUE;
		return neg ? ((int)lans) * -1 : (int)lans;
    }

    private long ldivide(long divd, long divs){
		if(divd < divs) return 0;
		long sum = divs;
		long count = 1;
		while((sum　+　sum) < divd){
			sum += sum;
			count += count;
		}
		return count + ldivide(divd　-　sum,divs);
    }
}





