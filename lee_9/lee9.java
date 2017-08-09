/*******
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.


*******/


public class Solution {
    public void solveSudoku(char[][] board) {
        searchBoard(board, 0, 0);
    }
    
    public boolean searchBoard(char[][] board, int x, int y) {
        if (y >= 9) return searchBoard(board, x + 1, 0); // check next line
        if (x >= 9) return true; // finished 
        if (board[x][y] != '.') return searchBoard(board, x, y + 1); // check next one
       
        // 此处可以进行优化
        for (char i = '1'; i <= '9'; i++) {
            board[x][y] = i;
            if (isValid(board, x, y, i) && searchBoard(board, x, y + 1)) {
                return true;
            }
            board[x][y] = '.';
        }
        return false;
    }
    
    public boolean isValid(char[][] board, int x, int y, char k) {
        // check row and column 
         for (int i = 0; i < 9; i++) {
            if (y != i && k == board[x][i]) {
                 return false;
            }
            if (x != i && k == board[i][y]) {
                return false;
            }
         }
        // check block
        int startX = x / 3 * 3; // 此处小心
        int startY = y / 3 * 3;
        for (int i = 0; i < 9; i++) {
            int X = startX + i / 3;
            int Y = startY + i % 3;
            // BUG X == startX && Y == startY 
            if (X == x && Y == y) {
                continue;
            }
            // BUG board[X][Y] == i
            if (board[X][Y] == k) return false;
        }
        return true;
    }
}



// 高效的优化的解法

public class Solution {
    
    static final int size = 9;
    
    static final int size2 = size * size;
    
    static final int bitMask = (1 << size) - 1;
    
    static final char EmptyFlag = '.';
    
    int[] hasNumRow = new int[size];
    int[] hasNumCol = new int[size];
    int[] hasNumSq = new int[size];
    
    char[][] board;
    
    public void solveSudoku(char[][] board) {
        this.board = board;
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                if(board[y][x] != EmptyFlag) {
                    setFilled(y, x, board[y][x]);
                }
            }
        }
        solveSudoku0();
    }

    private boolean solveSudoku0() {
        int y0 = -1, x0 = -1, minFillCnt = size;
        Loop:
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                if(board[y][x] != EmptyFlag) {
                    continue;
                }
                int bit = getCanFillBit(y, x);
                int count = getBitCount(bit);
                if(count == 0) {
                    return false;
                }
                if(count < minFillCnt) {
                    minFillCnt = count;
                    y0 = y;
                    x0 = x;
                    if(count == 1) {
                        break Loop;
                    }
                }
            }
        }
        if(y0 < 0) {
            return true;
        }
        int bit = getCanFillBit(y0, x0);
        for(int i = 0; i < size; i++) {
            if(hasBit(bit, i)) {
                char num = (char)(i + '1');
                setFilled(y0, x0, num);
                if(solveSudoku0()) {
                    return true;
                }
                setUnfilled(y0, x0, num);
            }
        }
        return false;
    }

    private int getSquareId(int y, int x) {
        return y / 3 * 3 + x / 3;
    }

    private int getBitCount(int bit) {
        int count = 0;
        for(; bit > 0; bit -= bit & -bit) {
            count++;
        }
        return count;
    }

    private void setFilled(int y, int x, char num) {
        board[y][x] = num;
        int bit = 1 << (num - '1');
        hasNumCol[y] |= bit;
        hasNumRow[x] |= bit;
        hasNumSq[getSquareId(y, x)] |= bit;
    }
    
    private void setUnfilled(int y, int x, char num) {
        board[y][x] = EmptyFlag;
        int bit = ~(1 << (num - '1'));
        hasNumCol[y] &= bit;
        hasNumRow[x] &= bit;
        hasNumSq[getSquareId(y, x)] &= bit;
    }

    private int getCanFillBit(int y, int x) {
        int hasFilled = hasNumCol[y] | hasNumRow[x] | hasNumSq[getSquareId(y, x)];
        return hasFilled ^ bitMask;
    }

    private boolean hasBit(int bit, int index) {
        return ((bit >> index) & 1) == 1;
    }
}




