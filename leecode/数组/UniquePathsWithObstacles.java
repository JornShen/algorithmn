/*

leetcode 63:

Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]

The total number of unique paths is 2.

Note: m and n will be at most 100.

*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] grid = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    grid[i][j] = 0; // 不可走
                    continue;
                }
                if (i == 0 && j == 0) {
                    grid[i][j] = 1;
                    continue;
                }
                
                if (i == 0) {
                    grid[i][j] = grid[i][j - 1];
                    continue;
                }

                if (j == 0) {
                    grid[i][j] = grid[i - 1][j];
                    continue;
                }
                
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }
        return grid[row - 1][col - 1];
    }
}

