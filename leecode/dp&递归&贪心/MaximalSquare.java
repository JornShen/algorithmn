/*
leetcode 221. Maximal Square

Given a 2D binary matrix filled with 0's and 1's, 
find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.

*/

class Solution {
    public int maximalSquare(char[][] matrix) {
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int row = matrix.length, col = matrix[0].length;
        int[][] square = new int[row][col];
        int res = 0;

        for (int i = 0; i < row; i++) {
            square[i][0] = matrix[i][0] - '0';
            res = Math.max(res, square[i][0]);
        }

        for (int i = 0; i < col; i++) {
            square[0][i] = matrix[0][i] - '0';
            res = Math.max(res, square[0][i]);
        }
        
        // dp[i][j]  = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '1') {
                    square[i][j] = Math.min(Math.min(square[i-1][j], square[i][j-1]), square[i-1][j-1]) + 1;
                }

                res = Math.max(square[i][j], res);
            }
        }
        
        return res * res;
    }
}




