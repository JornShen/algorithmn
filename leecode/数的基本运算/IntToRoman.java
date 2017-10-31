/*

Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.

*/
//　我的做法, 比较繁琐, 简洁见下文
class Solution {
    // 阿拉伯数字转化为罗马数字
    public String intToRoman(int num) {
        int div = 1000;
        StringBuffer buffer = new StringBuffer();
        while (div > 0) {
            int temp = num / div;
            // 4 5 6　的地方重点处理
            if (temp < 4 && temp > 0) {
                char c = getChar(div);
                for (int i = 0; i < temp; i++) {
                    buffer.append(c);
                }
            } else if (temp == 4) {
                buffer.append(getChar(div));
                buffer.append(getChar(div * 5));
            } else if (temp >= 5 && temp < 9) {
                buffer.append(getChar(div * 5));
                char c = getChar(div);
                temp -= 5;
                for (int i = 0; i < temp; i++) {
                    buffer.append(c);
                }
            } else if (temp == 9) {
                buffer.append(getChar(div));
                buffer.append(getChar(div * 10));
            }
            num %= div;
            div /= 10;
        }
        return buffer.toString();
    }
    public char getChar(int num) {
        switch (num) {
            case 1:  return 'I';
            case 5:  return 'V';
            case 10: return 'X';
            case 50: return 'L';
            case 100: return 'C';
            case 500: return 'D';
            case 1000: return 'M';
            default:return 'I';
        }

    }
}

//　别人的做法

class Solution {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<values.length;i++){
            while(num >= values[i]){
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }
}