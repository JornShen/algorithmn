/*
leetcode 208. Implement Trie (Prefix Tree)

Implement a trie with insert, search, and startsWith methods.

*/

//　有参考网上的一篇博客。理解了前缀树
class Trie {

    class Node {
        Node[] child = new Node[26];
        boolean isWordEnd = false;
    }

    private Node root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        word = word.toLowerCase();
        Node tmp = root;
        for (char c : word.toCharArray()) {
            if (tmp.child[c - 'a'] == null) {
                tmp.child[c - 'a'] = new Node();
            }
            tmp = tmp.child[c - 'a'];
        }
        tmp.isWordEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node tmp = root;
        for (char c : word.toCharArray()) {
            if (tmp.child[c - 'a'] == null) return false;
            tmp = tmp.child[c - 'a'];
        }
        if (tmp.isWordEnd) return true;
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node tmp = root;
        for (char c : prefix.toCharArray()) {
            if (tmp.child[c - 'a'] == null) return false;
            tmp = tmp.child[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */


