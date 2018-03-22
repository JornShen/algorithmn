/*
leetcode 240. Search a 2D Matrix II

Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.

*/

// 我的写法，借助一些特点
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length - 1, col = matrix[0].length - 1;
        if (matrix[0][0] > target || target > matrix[row][col]) return false;

        while (row >= 0 && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) {row--; col--;}
            else break;
        }

        row++;
        col++;

        while (row < matrix.length && col < matrix[0].length) {
            if (matrix[0][col] > target && matrix[row][0] > target) break;
            for (int i = 0; i < col; i++) {
                if (matrix[row][i] == target) return true;
                else if (matrix[row][i] > target) break;
            }

            for (int i = 0; i < row; i++) {
                if (matrix[i][col] == target) return true;
                else if (matrix[i][col] > target) break;
            }
            row++;
            col++;
        }
        return false;
    }
}


// 别人的写法，　写法比较精妙！

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        if (target > matrix[m-1][n-1]) return false;
        int row = m - 1, col = 0;
        boolean searchRow = true;
        while (row >= 0 && col < n) {
            if (searchRow) {
                if (matrix[row][col] == target) {
                    return true;
                } else if (matrix[row][col] > target) {
                    row--;
                } else {
                    searchRow = false;
                }
            } else {
                if (matrix[row][col] == target) {
                    return true;
                } else if (matrix[row][col] < target) {
                    col++;
                } else {
                    searchRow = true;
                }
            }
        }
        return false;
    }
}
