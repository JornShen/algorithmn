/*

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

*/


字符串的处理

第一行和最后一行，就是按照2n-2的顺序一点点加的。

其他行除了上面那个填字规则，就是还要处理斜着那条线的字，
可以发现那条线的字的位置永远是当前列j+(2n-2)-2i(i是行的index）。
 
class Solution {
    public String convert(String s, int numRows) {
        //规律 2n - 2
        int step = 0;
        if (s == null || s.length()==0 || numRows <=0) return "";
        if (numRows == 1) return s;
        step = 2 * numRows - 2;
        int len = s.length();
        char[] c = s.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < len; j += step) {
                buffer.append(c[j]);
                if (i != 0 && i != numRows - 1) { // 非最后一行或第一行, j+(2n-2)-2i
                    int temp = j + step - 2 * i;
                    if (temp < len) {
                        buffer.append(c[temp]);
                    }
                }
            }
        }
        return buffer.toString();
    }
}
