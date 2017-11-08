/*

The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

*/

class Solution {
    public String countAndSay(int n) {
      if (n <= 0) return "";
        String s = "1";
        for (int i = 1; i < n; i++) {
            StringBuffer buffer = new StringBuffer();
            char[] c = s.toCharArray();
            char temp = c[0];
            int count = 1;
            for (int j = 1; j < c.length; j++) {
                // 统计数字
                if (c[j] == temp) count++;
                // another one
                else {
                    buffer.append(count).append(temp);
                    temp = c[j];
                    count = 1;
                }
            }
            buffer.append(count).append(temp);
            s = buffer.toString();
        }	
        return s;
    }
}