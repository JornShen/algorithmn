/*

leetcode 48:

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]

Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
*/

// 选　一个点　按照　旋转的路径进行　调换
class Solution {
    public void rotate(int[][] matrix) {
        int len = matrix.length - 1;
        int len1 = matrix.length;
        int row = len1 % 2 == 0 ? len1 / 2 : len1 / 2 + 1;
        int to = len;
        for (int i = 0; i < row; i++) {
            for (int j = i; j < to; j++) {
                // if (i != 0 && i == j) continue;
                // 旋转的起始点
                int lr = i, lc = j, temp1 = 0, temp2 = matrix[i][j];
                for (int k = 0; k < 4; k++) {
                    int r = lc, c = len - lr;
                    temp1 = matrix[r][c];
                    matrix[r][c] = temp2;
                    temp2 = temp1;
                    lc  = c;
                    lr  = r;
                }
            }
            to--;
        }
        return;
    }
}

／／　别人的代码

class Solution {
    public void rotate(int[][] matrix) {
        horizontalFlip(matrix);　// 上下翻转
        diagonalFlip(matrix); // 右上角和右下角翻转
    }
    
    public void horizontalFlip(int[][] matrix){
        int n = matrix.length;
        for(int i = 0; i < n/2; i++){
            int[] tmp = matrix[i];
            matrix[i] = matrix[n - 1 - i];
            matrix[n - 1 - i] = tmp; 
        }
        
    }
    public void diagonalFlip(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}

／／　旋转的一种方式
class Solution {
    public void rotate(int[][] matrix) {
    	// 右上角　和　左下角　交换
        for (int r = 0; r < matrix.length - 1; r++) {
            for (int c = r + 1; c < matrix.length; c++) {
                int tmp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = tmp;
            }
        }
        // 每一行的左右边的左右调换
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length / 2; c++) {
                int tmp = matrix[r][c];
                matrix[r][c] = matrix[r][matrix.length - 1 - c];
                matrix[r][matrix.length - 1 - c] = tmp;
            }
        }
    }
}

