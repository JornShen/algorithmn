/*


Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"

Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.

*/


// 递归解
class Solution {
    private List<String> list = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        // 递归
        if (digits == null || digits.length() == 0) return list;
        String[] str = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        joint(new char[digits.length()], 0, str, digits);
        return list;
    }

    public void joint(char[] s, int pos, String[] keys, String source) {
        if (pos == source.length()) {
            list.add(new String(s));
            return;
        }
        int t = source.charAt(pos) - '2';
        String temp = keys[t];
        for (int i = 0; i < temp.length(); i++) {
            s[pos] = temp.charAt(i);
            joint(s, pos + 1, keys, source);
        }
    }
}
