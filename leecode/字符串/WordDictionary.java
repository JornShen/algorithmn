/*
leetcode 211. Add and Search Word - Data structure design
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing 
only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

*/
//　我的写法，　是之前的字典前缀的改编　
class WordDictionary {

    class Node {
        Node[] nodes = new Node[26];
        boolean isWordEnd = false;
    }
    private Set<Integer> set = new HashSet<>();
    private Node root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        set.add(word.length());
        Node tmp = root;
        for (char c : word.toCharArray()) {
            if (tmp.nodes[c - 'a'] == null) {
                tmp.nodes[c - 'a'] = new Node();
            }
            tmp = tmp.nodes[c - 'a'];
        }
        tmp.isWordEnd = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character 
    '.' to represent any one letter. */
    public boolean search(String word) {
        if (!set.contains(word.length())) return false; //　直接判断是否有这种长度的单词
        return searchSubWord(word, root);
    }

    public boolean searchSubWord(String word, Node root) {
        if (word == null) return true;
        Node tmp = root;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '.') {
                // 随便挑选一个向下进行递归
                for (int j = 0; j < 26; j++) {
                    if (tmp.nodes[j] != null) {
                        if (searchSubWord(word.substring(i+1), tmp.nodes[j])) return true;
                    }
                }
                return false; //　此处要记得返回错误
            } else {
                char c = word.charAt(i);
                if (tmp.nodes[c - 'a'] == null) return false;
                tmp = tmp.nodes[c - 'a'];
            }
        }
        if (tmp.isWordEnd) return true;
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */





