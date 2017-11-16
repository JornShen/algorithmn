/*

leetcode 59:

Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

*/

class Solution {
    public int[][] generateMatrix(int n) {
        if (n <= 0) return new int[0][0];
        int[][] result = new int[n][n];
        if (n == 0) return result;
        int l = 0, r = n, t = 0, d = n;
        int pos = 1;
        while (l < r || t < d) {

            for (int i = l; i < r; i++) {
                result[t][i] = pos++;
            }
            t++;
            if (t >= d) break;

            for (int i = t; i < d; i++) {
                result[i][r - 1] = pos++;
            }
            r--;
            if (r <= l) break;

            for (int i = r - 1; i >= l; i--) {

                result[d - 1][i] = pos++;
            }
            d--;
            if (t >= d) break;

            for (int i = d - 1; i >= t; i--) {
                result[i][l] = pos++;
            }
            l++;
            if (r <= l) break;
        }
        return result;    
    }
}

