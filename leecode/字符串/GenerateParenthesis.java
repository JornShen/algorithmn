/*

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

*/
// 错误做法
import java.util.*;
class Solution {
    private Set<String> set = new TreeSet<>();
    public List<String> generateParenthesis(int n) {
        if (n == 0) return new ArrayList<>();
        // 向下递归产生
        generate("()", n - 1);
        return new ArrayList<>(set);
    }
    public void generate(String s, int n) {
        if (n == 0) {
            set.add(s);
            return;
        }
        generate(s + "()", n - 1);
        generate("()" + s, n - 1);
        generate("(" + s + ")", n - 1);
    }	
}


// 


/*

思路:

所以我们定义两个变量left和right分别表示剩余左右括号的个数，
如果在某次递归时，左括号的个数大于右括号的个数，
说明此时生成的字符串中右括号的个数大于左括号的个数，
即会出现')('这样的非法串，所以这种情况直接返回，
不继续处理。如果left和right都为0，
则说明此时生成的字符串已有3个左括号和3个右括号，
且字符串合法，则存入结果中后返回。如果以上两种情况都不满足，
若此时left大于0，则调用递归函数，注意参数的更新，
若right大于0，则调用递归函数，同样要更新参数。

*/
public List<String> generateParenthesis(int n) {
    List<String> list = new ArrayList<>();
    generate(n, n, "", list);
    return list;
}

public void generate(int left, int right, String out, List<String> l) {
    if (left < 0 || right < 0 || left > right) return;
    if (left == 0 && right == 0) {
        l.add(out);
        return;
    }
    generate(left - 1, right, out + "(", l);
    generate(left, right - 1, out + ")", l);

}
