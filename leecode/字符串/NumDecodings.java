/*

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.

*/

//　递归解法，　方法超时
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        return calnum(s, 0);
    }
    public int calnum(String s, int pos) {

        if (pos >= s.length()) return 1;
        int right = 0, left = 0;
        if (s.charAt(pos) == '0') {
            return 0;
        }
        left = calnum(s, pos + 1);
        if (pos + 1 < s.length()) {
            if (Integer.parseInt(s.substring(pos, pos + 2)) <= 26) {
                right += calnum(s, pos + 2);
            }
        }
        return left + right;
    }
}
	

／／非递归的做法，　需要参考跳台阶问题，只是需要添加一些限制条件
／／　反着来写，可以参考上面的递归的方式


class Solution {
    public int numDecodings(String s) {

 		// number[i] = number[i-1] + number[i-2]
        // s[i-1]不能是0，如果s[i-1]是0的话，number[i]就只能等于number[i-2]
        // s[i-2,i-1]中的第一个字符不能是0，而且Integer.parseInt(s.substring(i-2,i))获得的整数必须在0到26之间。

        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;        
        int[] nums = new int[s.length() + 1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            int tmp = s.charAt(i - 1) - '0';
            if (tmp != 0) nums[i] += nums[i - 1];
            if (s.charAt(i - 2) != '0') {
                tmp = Integer.parseInt(s.substring(i - 2, i));
                if (tmp >0 && tmp <= 26) {
                    nums[i] += nums[i - 2];
                }
            }
        }
        return nums[s.length()];
    }
}

