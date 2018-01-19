/*
leetcode 166. Fraction to Recurring Decimal
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
*/


class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        long n = numerator, d = denominator;　// 此处需要转化为　long 的类型
        // 确定符号
        String sign = "";
        if (n * d == 0) return "0";
        else sign = (n * d) < 0 ? "-" : "";

        n = Math.abs(n);
        d = Math.abs(d);

        long integer = n / d;
        long left = (n % d) * 10;
        List<String> list = new ArrayList<>();
        boolean flag = false;
        int start = 0;
        StringBuffer buffer = new StringBuffer();

        while (left != 0) {
            String str = left + "/" + d;
            flag = false;
            for (int i = list.size() - 1; i >= 0; i--) {
                //　从后往前进行遍历，看是否相同，　有相同的位置是重复的位置
                String tmp = list.get(i);
                if (tmp.equals(str)) {
                    start = i;
                    flag = !flag;
                    break;
                }
            }
            if (flag) break;
            list.add(str);
            buffer.append(left / d);
            left = (left % d) * 10;
        }

        // 有限小数
        String s = buffer.toString();
        if (left == 0) return sign + (buffer.length() == 0 ? integer + "" : integer + "." + s);
        String noRepeating = s.substring(0, start);
        String repeating = s.substring(start);
        return sign + integer + "." + noRepeating + "(" + repeating + ")";
    }
}

// 思路基本一致，　采用 map 更加快捷

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(denominator == 0) return "";
        if(numerator == 0) return "0";
        boolean neg = numerator < 0 ^ denominator < 0;
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        Map<Integer, Integer> map = new HashMap<>(); //  <余数，　位置> 
        String firstPart = (neg ? "-" : "") + num / den;
        if(num % den == 0) return firstPart;
        StringBuilder sb = new StringBuilder();
        long resi = num % den;
        int count = 0;
        while(resi != 0) {
            if(map.containsKey((int)resi)){
                sb.insert(map.get((int)resi).intValue(), '(');
                sb.append(')');
                break;
            }
            map.put((int)resi, count++);
            resi *= 10;
            sb.append(resi / den);
            resi %= den;
        }
        return firstPart + "." + sb.toString();
    }
}
