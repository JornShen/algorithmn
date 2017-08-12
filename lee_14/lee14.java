/************
N 皇后问题

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
***************/


//------------   迭代写法  -----------------
public class Solution {
    private List<List<String>> list = new ArrayList<>();
    private int[] result;
    private char[] line;
    public List<List<String>> solveNQueens(int n) {
        result = new int[n + 1];
        line = new char[n];
        Arrays.fill(line, '.');
        result[1] = 0;
        int k = 1;
        // **核心代码** 
        while (k > 0) {
            while (result[k] <= (n - 1)) { // 到 n - 1 步，相当于到了最后一格
                result[k]++;
                if (isValid(k)) {
                    if (k == n) { // 找到结果
                        addResolution();
                    } else {
                        // 下一层访问
                        k++;
                        result[k] = 0;
                    }
                }
            }
            k--;// 返回上一层
        }
        return list;
    }

    // 验证合法性
    public boolean isValid(int k) {
        for (int j = 1; j <= k - 1; j++) {
        	// 同列或对角线
            if (Math.abs(j - k) == Math.abs(result[j] - result[k])
                    || result[j] == result[k]) 
            	return false;
        }
        return true;
    }

    // 添加正确的解决方案
    public void addResolution() {
        List<String> strings = new ArrayList<>();
        for (int i = 1; i < result.length; i++) {
            line[result[i] - 1] = 'Q';
            strings.add(new String(line));
            line[result[i] - 1] = '.';
        }
        list.add(strings);
    }
}

//--------------   递归写法  --------------------

public class Solution {
    List<List<String>> res=new ArrayList();
    public List<List<String>> solveNQueens(int n) {
        if(n==0) return res;
        char[][] tmp=new char[n][n];
        boolean[] col=new boolean[n];
        boolean[] dia=new boolean[2*n-1];
        boolean[] antidia=new boolean[2*n-1];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                tmp[i][j]='.';
            }
        }
        dfs(tmp,n,col,dia,antidia,0);
        return res;
    }
    public void dfs(char[][] tmp,int n,boolean[] col,boolean[] dia,boolean[] antidia,int row){
        if(row==n){
            List<String> cur=new ArrayList();
            for(int i=0;i<n;i++){
                cur.add(String.valueOf(tmp[i]));
            }
            res.add(cur);
            return;
        }
        for(int j=0;j<n;j++){
            if(col[j]||dia[row+j]||antidia[row-j+n-1]) continue;
            col[j]=dia[row+j]=antidia[row-j+n-1]=true;
            tmp[row][j]='Q';
            dfs(tmp,n,col,dia,antidia,row+1);
            tmp[row][j]='.';
            col[j]=dia[row+j]=antidia[row-j+n-1]=false;
        }
    }
}


