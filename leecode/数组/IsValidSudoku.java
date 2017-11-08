/*****
leetcode 36:

Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

判断数读是否合法

****/

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Integer> rowCheck = new HashSet<>(); // check row
        Set<Integer> columnCheck = new HashSet<>(); // check column
        Set<Integer>[][] lattice = new HashSet[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                lattice[i][j] = new HashSet<>();
            }
        }
        for (int i = 0; i < 9; i++) {
            rowCheck.clear();
            columnCheck.clear();
            for (int j = 0; j < 9; j++) {
                // 行判断
                if (board[i][j] != '.') {
                    int num = Integer.parseInt(Character.toString(board[i][j]));
                    if (rowCheck.contains(num)) return false;
                    // check lattice 
                    if (lattice[i / 3][j / 3].contains(num)) return false;
                    rowCheck.add(num);
                    lattice[i / 3][j / 3].add(num);
                }
                // 列判断
                if (board[j][i] != '.') {
                    int num = Integer.parseInt(Character.toString(board[j][i]));
                    if (columnCheck.contains(num)) return false;
                    columnCheck.add(num);
                }
                
            }
        }
        return true;       
    }
}
// 以上代码使用了 set 集合 

// ------------------- 用位运算来判断是否相同 ------------------
// 代码比较简洁，　采用位标记进行判断
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxs = new int[9];
        for (int i=0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if (num <= 0 || num > 9) return false;
                    num = 1 << num;
                    if ((num & rows[i]) == num) return false;
                    if ((num & cols[j]) == num) return false;
                    int ind = (i / 3) * 3;
                    ind += (j / 3);
                    if ((num & boxs[ind]) == num) return false;
                    rows[i] |= num;
                    cols[j] |= num;
                    boxs[ind] |= num;
                }
            }
        }
        return true;
    }
}
