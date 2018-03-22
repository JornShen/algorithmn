/*
leetcode 273. Integer to English Words

Convert a non-negative integer to its english words representation. 
Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

*/


//　我的写法
class Solution {
   
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String[] multiple = {"", "Thousand", "Million", "Billion"};
        String res = "";
        int i = 0;
        while (num != 0) {
            int tmp = num % 1000;
            if (tmp != 0) {
                if (i == 0 && res.equals("")) res = getNum(tmp);
                else if (res.equals("")) res = getNum(tmp) + " " + multiple[i];
                else res = getNum(tmp) + " " + multiple[i] + " " + res;
            }
            i++;
            num /= 1000;
        }
        return res;
    }

    public String getNum(int num) {

        String res = "";
        //　拼凑百位部分
        if (num >= 100) res = matchNum1(num / 100) + " Hundred";
        if (num > 100 && num % 100 > 0) res += " ";
        // 拼凑十位和个位部分
        if (num % 100 == 0) ;
        else if (num % 100 < 10) res += matchNum1(num % 100);
        else if ((num % 100) < 20 && (num % 100) >= 10) res += matchNum2(num % 100);
        else {
            res += matchNum3((num % 100) / 10);
            if (num % 10 != 0) res += " " + matchNum1(num % 10);
        }
        return res;
    }
    
    public String matchNum1(int n) {
        switch (n) {
            case 1 : return "One";
            case 2 : return "Two";
            case 3 : return "Three";
            case 4 : return "Four";
            case 5 : return "Five";
            case 6 : return "Six";
            case 7 : return "Seven";
            case 8 : return "Eight";
            case 9 : return "Nine";
            default:  return "Zero";
        }
    }

    public String matchNum2(int n) {
        switch (n) {
            case 10 : return "Ten";
            case 11 : return "Eleven";
            case 12 : return "Twelve";
            case 13 : return "Thirteen";
            case 14 : return "Fourteen";
            case 15 : return "Fifteen";
            case 16 : return "Sixteen";
            case 17 : return "Seventeen";
            case 18 : return "Eighteen";
            case 19 : return "Nineteen";
            default:  return "Zero";
        }
    }

    public String matchNum3(int n) {
        switch (n) {
            case 2 : return "Twenty";
            case 3 : return "Thirty";
            case 4 : return "Forty";
            case 5 : return "Fifty";
            case 6 : return "Sixty";
            case 7 : return "Seventy";
            case 8 : return "Eighty";
            case 9 : return "Ninety";
            default:  return "Zero";
        }
    }   
}


／／　别人的写法，更加简洁

class Solution {
    /*
    static string array need to add space
    */
    static String[] TWENTIES = new String[] {"", " One", " Two", " Three", " Four", " Five", " Six",
                                                " Seven", " Eight", " Nine", " Ten", " Eleven", " Twelve",
                                                " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen",
                                                " Eighteen", " Nineteen"};
    static String[] TENS = new String[] {"", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty",
                                        " Seventy", " Eighty", " Ninety", " Hundred"};
    static String[] THOUSANDS = new String[] {"", " Thousand", " Million", " Billion"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        int BASE = 1000;
        String res = "";
        int i = 0;
        while (num != 0) {
            int cur = num % BASE;
            res = helper(cur) + (cur == 0? "": THOUSANDS[i]) + res;
            num = num / BASE;
            i++;
        }
        return res.substring(1, res.length()); // 将空格去除 
    }
    public String helper(int num) {
        if (num < 20) {
            return TWENTIES[num];
        }
        if (num < 100) {
            return TENS[num / 10] + TWENTIES[num % 10];
        }
        return TWENTIES[num / 100] + " Hundred" + helper(num % 100);
    }
}