/*
leetcode 79:

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

*/

// 用　dfs　递归下降进行寻找．　类似于走迷宫的做法

class Solution {
    public boolean exist(char[][] board, String word) {
        int row = board.length, col = board[0].length;
        boolean[][] visited = new boolean[row][col];　// 标记是否已经走过
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, visited, i, j, word, 0)) return true;
            }
        }
        return false;
    }
    
    public boolean dfs(char[][] board, boolean[][] visited, int i, int j, String word, int k) {
        if (k == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (visited[i][j]) return false;
        if (word.charAt(k) != board[i][j]) return false;　// 字符不符合，返回失败
        // 递归向下探测	
        visited[i][j] = true;
        if (dfs(board, visited, i + 1, j, word, k + 1)) return true;
        if (dfs(board, visited, i, j + 1, word, k + 1)) return true;
        if (dfs(board, visited, i - 1, j, word, k + 1)) return true;
        if (dfs(board, visited, i, j - 1, word, k + 1)) return true;
        visited[i][j] = false;
        return false;
    }
}




