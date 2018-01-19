/*
leetcode  200. Number of Islands
Given a 2d grid map of '1's (land) and '0's (water), 
count the number of islands. An island is surrounded by water 
and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
*/

// 解法类似于求遍历求图的连通性
class Solution {
    
    public boolean[][] isVisted;
    public int islandSum = 0;
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        isVisted = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1' && !isVisted[i][j]) {
                    islandSum++;
                    visitIsland(grid, i, j);
                }
            }
        }
        return islandSum;  
    }
    
    public  void visitIsland(char[][] grid, int i, int j) {
        if (grid[i][j] == '0' || isVisted[i][j]) return; // 此处一定要加上
        isVisted[i][j] = true;
        if (i + 1 < grid.length) visitIsland(grid, i+1, j);
        if (j + 1 < grid[0].length) visitIsland(grid, i, j+1);
        if (i - 1 >= 0) visitIsland(grid, i-1, j);
        if (j - 1 >= 0) visitIsland(grid, i, j-1);
    }
}

