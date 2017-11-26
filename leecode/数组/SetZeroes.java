/*
leetcode 73:

Given a m x n matrix, if an element is 0,
set its entire row and column to 0. Do it in place.

*/

//　两层 for 循环. 　O(n ^ 3)

class Solution {
    public void setZeroes(int[][] matrix) {
        Set<Integer> col = new HashSet<>(), row = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    col.add(j);
                    row.add(i);
                    for (int k = 0; k < j; k++) matrix[i][k] = 0;
                    for (int k = 0; k < i; k++) matrix[k][j] = 0;
                    continue;
                }
                if (row.contains(new Integer(i)) || col.contains(new Integer(j))) {
                    matrix[i][j] = 0;
                    continue;
                }
            }
        }
    }
}

// 别人的代码　O(m + n)

class Solution {
    public void setZeroes(int[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return;
        }

        boolean row = false, col = false;
       
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                row = true;
                break;
            }
        }
        
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                col = true;
                break;
            }
        }
         //  首行作为记录行是否为 0 的记录．
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
       　// 列　为　0 
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 行　为　0
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        if (row) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }

        if (col) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][0] = 0;
            }
        }
    }
}


