/*

leetcode 54:

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return list;
        int l = 0, r = matrix[0].length, t = 0, d = matrix.length;
        while (l < r || t < d) {
            for (int i = l; i < r; i++) {
                list.add(matrix[t][i]);
            }
            t++;
            if (t >= d) break;

            for (int i = t; i < d; i++) {
                list.add(matrix[i][r - 1]);
            }
            r--;
            if (r <= l) break;

            for (int i = r - 1; i >= l; i--) {
                list.add(matrix[d - 1][i]);
            }
            d--;
            if (t >= d) break;

            for (int i = d - 1; i >= t; i--) {
                list.add(matrix[i][l]);
            }
            l++;
            if (r <= l) break;
        }
        return list;
    }
}
