/*
leetcode 130. Surrounded Regions:

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/

// 逆向思维
// 从边缘开始走, DFS遍历, 可达的说明不可以被包围,采用另外的一个标记进行标记

class Solution {

    public void solve(char[][] board) {

        if (board == null || board.length == 0 || board[0].length == 0) return;
        int row = board.length, col = board[0].length;
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i == 0 || j == 0 || i == row - 1 || j == col - 1) 
                    && board[i][j] == 'O'){
                    solveDFS(board, i, j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '$') board[i][j] = 'O';
            }
        }
    }
    
    public void solveDFS(char[][] board, int i, int j) {   
        if (board[i][j] == 'O') {

            board[i][j] = '$';
            
            if (i > 0 && board[i - 1][j] == 'O') solveDFS(board, i - 1, j);
            
            if (j > 0 && board[i][j - 1] == 'O') solveDFS(board, i, j - 1);
            
            if (i < board.length - 1 && board[i + 1][j] == 'O') solveDFS(board, i + 1, j);
            
            if (j < board[0].length - 1 && board[i][j + 1] == 'O') solveDFS(board, i, j + 1);
        }
    }
}