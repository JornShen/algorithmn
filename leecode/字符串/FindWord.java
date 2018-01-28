/*
leetcode 212. Word Search II

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].

*/

// 我的写法，过于暴力，没有进行要优化
class Solution {
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> list = new ArrayList<>();
        int row = board.length, col = board[0].length;a
        Set<String> set = new HashSet<>();
        for (String w : words) {
            set.add(w);
        }
        for (String w : set) {
            if (isWordExit(board, w, row, col)) list.add(w);
        }
        return list;
    }

    public boolean isWordExit(char[][] board, String word, int row, int col) {
        boolean[][] flag = new boolean[row][col]; //　判断是否走过
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (word.charAt(0) == board[i][j]) {
                    if (searchWord(board, i, j, word, 0, flag)) return true;
                }
            }
        }
        return false;
    }

    public boolean searchWord(char[][] board, int x, int y,
                              String word, int index, boolean[][] flag) {
        if (index == word.length()) return true;
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return false;
        if (flag[x][y]) return false;
        if (word.charAt(index) == board[x][y]) {
            flag[x][y] = true;
            if (searchWord(board, x - 1, y, word, index + 1, flag) || 
                    searchWord(board, x + 1, y, word, index + 1, flag) ||
                    searchWord(board, x, y - 1, word, index + 1, flag) ||
                    searchWord(board, x, y + 1, word, index + 1, flag)) return true;
            flag[x][y] =false;
        }
        return false;
    }
}

// 别人的写法 
class Solution {

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }
    
    // 深度遍历和搜索树进行结合 
    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        //　写得很漂亮！！！
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {  
            res.add(p.word);
            p.word = null;  
        }
        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j ,p, res); 
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
        board[i][j] = c;
    }
    
    // 建立　前缀搜索树
    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
           }
           p.word = w;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
}

