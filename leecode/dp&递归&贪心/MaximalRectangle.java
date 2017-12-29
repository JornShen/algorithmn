/*
leetcode 85:

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle 
containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.

*/

// 超时做法，　数据量大的时候过不了．

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int maxOne = 0;
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = i; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    for (int q = k; q < col; q++) {
                        // 剪枝
                        if (judge(matrix, i, j, k, q)) {
                            maxOne = Math.max(maxOne, (j - i + 1) * (q - k + 1));
                        }
                    }
                }
            }
        }
        return maxOne;    
    }
    
    public boolean judge(char[][] matrix, int x, int y, int t, int d) {
        for (int i = x; i <= y; i++) {
            for (int j = t; j <= d; j++) {
                if (matrix[i][j] != '1') return false;
            }
        }
        return true;
    }
}

// dp 的写法，　对于每一行，建立 l, r, h　数组， 
// l: 从0到该j位置， 离 j 最远的连续的　１　的位置，　就是从当前位置开始，　向左边扩展的最长的长度
// r: 从 j 到该行末尾，第一个0的位置, 向右扩展最长的位置
// h: j 位置的高度
// (r[j] - l[j]) * h[j]

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int maxOne = 0;
        int row = matrix.length, col = matrix[0].length;
        int[] l = new int[col];
        int[] r = new int[col];
        int[] h = new int[col];
        Arrays.fill(r, col);
        // Arrays.fill(l, 0);
        // Arrays.fill(h, 0);
        for (int i = 0; i < row; i++) {
            // 统计高度
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') h[j]++;
                else h[j] = 0;
            }
            int leftBound = 0;
            int rightBound = col;
            // 计算左边界
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    l[j] = Math.max(l[j], leftBound);
                } else {
                    leftBound = j + 1;
                    l[j] = 0;
                }
            }
            //计算右边界
            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    r[j] = Math.min(r[j], rightBound);
                } else {
                    r[j] = col;
                    rightBound = j;
                }
            }
            for (int j = 0; j < col; j++) {
                maxOne = Math.max(maxOne, (r[j] - l[j]) * h[j]);
            }
        }
        return maxOne;
    } 
}
