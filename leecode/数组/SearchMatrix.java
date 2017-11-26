/*

leetcode 74:

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length, col = matrix[0].length;
        //  直接判断特殊情况
        if (target <= matrix[0][col - 1]) {
            return binarySearch(matrix[0], target);
        }
        if (target > matrix[row - 1][col - 1] || target < matrix[0][col - 1]) return  false;
        int left = 0, right = row - 1;
        // 根据最大值，　二分查找，　确定所在的行
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (matrix[mid][col - 1] == target) {
                return true;
            } else if (matrix[mid][col - 1] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // 将所在的行二分查找
        return  binarySearch(matrix[right], target);
    }
    
    public boolean binarySearch(int[] data, int target) {
        int left = 0, right = data.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (data[mid] == target) {
                return true;
            } else if (data[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
    
}

// 别人的做法, 更加简单
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length ==0 || matrix[0].length == 0) {
            return false;
        }
        int i = matrix.length - 1;
        int j = 0;
        while(i >= 0 && j <= matrix[0].length -1) {
	        if(matrix[i][j] == target) {
	            return true;
	        } else if(matrix[i][j] > target) {
	             i--;
	        } else {
	            j++;
	        }
        }
        return false;
    }
}

// 更加高效的做法, 跟我的做法差不多
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
    	if (matrix.length == 0 || matrix[0].length == 0 || matrix == null) return false;

		int startrow = 0;
		int endrow = matrix.length - 1;
		int row = -1;
		int col = matrix[0].length - 1;

		while (startrow + 1 < endrow) {
			int mid = startrow + (endrow - startrow) / 2;
			if (matrix[mid][col] < target) {
				startrow = mid;
			} else {
				endrow = mid;
			}
		}

		if (matrix[startrow][col] >= target) {
			row = startrow;
		} else if (matrix[endrow][col] >= target) {
			row = endrow;
		} else
			return false;

		int i = 0;
		while (i + 1 < col) {
			int mid = i + (col - i) / 2;
			if (matrix[row][mid] < target) {
				i = mid;
			} else {
				col = mid;
			}
		}

		if (matrix[row][i] == target) {
			return true;
		}
		if (matrix[row][col] == target) {
			return true;
		}
		return false;
    }
}