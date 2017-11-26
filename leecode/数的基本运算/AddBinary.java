/*
leetcode 67:

Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".

*/

// 正规的运算方法
class Solution {
    public String addBinary(String a, String b) {
        if (a.equals("")) return b;
        if (b.equals("")) return a;
        StringBuffer buffer = new StringBuffer();
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        int pa = a.length() - 1, pb = b.length() - 1;
        int carry = 0;
        while (pa >= 0 || pb >= 0) {
            int da = pa < 0 ? 0 : a1[pa] - '0';
            int db = pb < 0 ? 0 : b1[pb] - '0';
            int tmp = da + db + carry;
            if (tmp <= 1) {　// 加和小于　1 
                carry = 0; // 没有进位
                buffer.append(tmp);
            } else {
                carry = 1;　//　有进位
                buffer.append(tmp % 2);
            }
            pa--;
            pb--;
        }
        if (carry != 0) {
            buffer.append(carry);
        }
        return buffer.reverse().toString();
    }
}