/*
leetcode 89:
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code,
print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2

*/
／／基本解法: 从　0, 1 开始，当只有一位的时候, 由　0, 1 组成，　两位的时候，在一位的基础上，　左边添加　０和１, 
／／　注意对称性，　其他的位数依次类推

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        if (n == 0) {
            list.add(0);
            return list;
        }
        String[] result = genGrayCode(n);
        for (String s : result) {
            list.add(Integer.parseInt(s, 2));
        }
        return list;
    }
    public String[] genGrayCode(int n) {
        String[] str = new String[(int)Math.pow(2, n)];
        if (n == 1) {
            str[0] = "0";
            str[1] = "1";
            return str;
        }
        String[] tmp = genGrayCode(n - 1);
        for (int i = 0; i < tmp.length; i++) {
            str[i] = "0" + tmp[i];
            str[str.length - i - 1] = "1" + tmp[i];
        }
        return str;
    }
}

／／　另一种思路：根据公式: G(n) =  B(n) XOR B(n+1), 写法会更加简单

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < 1<<n; i++) 
        	result.add(i ^ i>>1);
        return result;
    }
}
