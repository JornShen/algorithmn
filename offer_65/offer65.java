
/***
地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），
因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
**/
public class Solution {
    /****
    private int wholeNum = 0;
    public int movingCount(int threshold, int rows, int cols)
    {
        // 计算数的范围
        int out = 0, inner = 0, down = 0, right = 0;
        for(int i = 0; i * 10 < rows; i++){
            out = count(i);
            down = (i * 10 + 9 <= rows) ? 9 : rows - i * 10;
            for(int j = 0; j * 10 < cols; j++){
                right = (j * 10 + 9 <= cols) ? 9 : cols - j * 10;
                inner = count(j);
                movingCount1(threshold - out - inner, down, right);
            }
        }
        return wholeNum;
    }

    public void movingCount1(int threshold, int down, int right)
    {
        if(threshold < 0) return;
        int mid = threshold / 2, l = 0, r = 0;
        int low = down > right ? right : down;
        if(mid > low) return;
        if(down == 9 && right == 9){ //此处可以进行优化，有点复杂
            if(threshold % 2 == 0){
                wholeNum += (9 - mid + 1) * ((9 - mid + 1));
            }else{
                wholeNum += (9 - mid + 1) * ((9 - mid + 1)) + 2;
            }
        }else{
            // 零散矩阵直接进行遍历
            for(int i = 0; i <= down; i++){
                for(int j = 0; j <= right; j++){	
                    if(i + j <= threshold) wholeNum++;
                }
            }
        }
    }

    public int count(int num){
        int sum = 0;
        while(sum != 0){
            sum += sum % 10;
            sum /= sum;
        }
        return sum;
    }
    // 采用分块的统计方法，但是发现效率还是不高。
    **/ 
    public int movingCount(int threshold, int rows, int cols)
    {
        int[][] flag = new int[rows][cols]; // 记录是否走过
        //递归下降进行遍历
        return helper(0, 0, rows, cols, flag, threshold);
    }
    public int helper(int x, int y, int rows, int cols, int[][] flag, int threshold){
        //仅仅遍历符合条件的节点， 就像在走节点一样
        if(x < 0 || x >= rows || y < 0 || y >= cols 
            || count(x) + count(y) > threshold || flag[x][y] == 1)
            return 0; //终止遍历
        flag[x][y] = 1;
        return 1 + helper(x + 1, y, rows, cols, flag, threshold) 
            	+ helper(x - 1, y, rows, cols, flag, threshold) 
        		+ helper(x, y - 1, rows, cols, flag, threshold) 
        		+ helper(x, y + 1, rows, cols, flag, threshold);  
    } 
    public int count(int num){
        int sum = 0;
        while(num != 0){
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}	