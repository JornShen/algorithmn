/******
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如 a b c e s f c s a d e e 
矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
**/

public class Solution {
    int[][] flag = null;
    int row = 0, col = 0;
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        row = rows;
        col = cols;
        if(str.length > matrix.length || matrix == null || str == null) return false;
        flag = new int[rows][cols];
        for(int i = 0; i  < rows ; i++){
            for(int j = 0; j < cols ; j++){
                if(matrix[i * cols + j] == str[0]){ // 寻找起点
                    flag[i][j] = 1;
                    if(searchPath(matrix, i, j, str, 1)){
                        return true;
                    }
                    flag[i][j] = 0;
                }
            }
        }
        return false;
    }
    
    public boolean searchPath(char[] matrix, int rows, int cols, char[] str, int index){
        if(str.length - 1 < index) return true;
        boolean left = false, right = false, up = false, down = false;
         if( rows - 1 >= 0 && flag[rows - 1][cols] == 0
                && matrix[(rows - 1) * col + cols] == str[index]){
            flag[rows - 1][cols] = 1;
            if(index == str.length - 1){
                flag[rows - 1][cols] = 0;//调试了一个多个小时 ********** 返回，应该在返回前置0，否则会影响其他的递归项
                return true;
            }
            up =searchPath(matrix, rows - 1, cols, str, index + 1);
            flag[rows - 1][cols] = 0;
        }
        // 下
        if(rows + 1 < row && flag[rows + 1][cols] == 0
                && matrix[(rows + 1) * col + cols] == str[index]){
            flag[rows + 1][cols] = 1;
            if(index == str.length - 1){
                flag[rows + 1][cols] = 0;
                return true;
            }
            down =searchPath(matrix, rows + 1, cols, str, index + 1);
            flag[rows + 1][cols] = 0;
        }
        // 左
        if(cols - 1 >= 0 && flag[rows][cols - 1] == 0
                && matrix[rows * col + cols - 1] == str[index]){
            flag[rows][cols - 1] = 1;
            if(index == str.length - 1){
                flag[rows][cols - 1] = 0;
                return true;
            }
            down =searchPath(matrix, rows, cols - 1, str, index + 1);
            flag[rows][cols - 1] = 0;
        }
        // 右边
        if(cols + 1 < col && flag[rows][cols + 1] == 0
                && matrix[rows * col + cols + 1] == str[index]){
            flag[rows][cols + 1] = 1;
            if(index == str.length - 1){
                flag[rows][cols + 1] = 0;
                return true;
            }
            down =searchPath(matrix, rows, cols + 1, str, index + 1);
            flag[rows][cols + 1] = 0;
        }
        return up || right || left || down;
    }

    /****
    // 别人的代码更加简单

     public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        int flag[] = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (helper(matrix, rows, cols, i, j, str, 0, flag))
                    return true;
            }
        }
        return false;
    }
 
    private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
            return false;
        if(k == str.length - 1) return true;
        flag[index] = 1;
        if (helper(matrix, rows, cols, i - 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i + 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j - 1, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)) {
            return true;
        }
        flag[index] = 0;
        return false;
    }

    */

}