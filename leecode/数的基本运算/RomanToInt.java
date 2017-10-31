/*

Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

*/

// 我的做法, 做法比较笨拙, 缺少灵活性.

class Solution {
    public int romanToInt(String s) {
        char[] c = s.toCharArray();
        int[] v1 = {1000, 500, 100, 50, 10, 5, 1};
        int[] v2 = {900, 400,  90, 40, 9, 4};
        char[] str1 = {'M', 'D',  'C', 'L', 'X', 'V', 'I'};
        String[] str2 = {"CM", "CD", "XC", "XL", "IX", "IV"};
        int sum = 0;
        for (int i = 0; i < c.length; i++) {
            if (i < c.length - 1) {
                StringBuffer buffer = new StringBuffer();// 注意此处如果使用了 c[i] + c[i + 1] + "" 来计算的话,字符会当成数字计算.
                buffer.append(c[i]).append(c[i + 1]);
                String temp = buffer.toString();
                boolean flag = false;
                for (int j = 0; j < str2.length; j++) {
                    if (temp.equals(str2[j])) {
                        sum += v2[j];
                        i++;
                        flag = !flag;
                    }
                }
                if (flag) continue;
            }

            for (int j = 0; j < str1.length; j++) {
                if (str1[j] == c[i]) {
                    sum += v1[j];
                    break;
                }
            }
        }
        return sum;
    }
}

// 别人的做法：
// 思想:　探测一位　, IV　由于 I 比 V 小, 所以 可以看做 先减去1, 后加上 5 .
class Solution {
    public int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int val = getVal(s.charAt(i));
            int temp = getVal(s.charAt(i + 1));
            if (val >= temp) {
                sum += val;
            }　else {
                sum -= val;
            }
        }
        sum += getVal(s.charAt(s.length() - 1));
        return sum;
    }
    
    public int getVal(char c) {
        switch (c) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                return 0;
        }
    }
}





