/*
leetcode 66:

Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

*/

//　注意处理进位的结果.
class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int carry = 1;
        for (int i = len - 1; i >= 0; i--) {
            int tmp = digits[i] + carry;
            carry = tmp / 10;
            digits[i] = tmp % 10;
            if (carry == 0) return digits;
        }
        if (carry != 0) {
            int[] result = new int[len + 1];
            System.arraycopy(digits, 0, result, 1, len);
            result[0] = carry;
            return result;
        }
        return digits;
    }
}