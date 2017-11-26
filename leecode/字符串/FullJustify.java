/***********
leetcode 68:

Given an array of words and a length L, format the text 
such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach;
that is, pack as many words as you can in each line. 
Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible.
If the number of spaces on a line do not divide evenly between words, 
the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

***********/

public class Solution {
    private List<String> list = new ArrayList<>(); // result
    private List<String> pool = new ArrayList<>(); // record the number has been deal with
    public List<String> fullJustify(String[] words, int maxWidth) {
        int start = 0, sum = 0;
        int i;
        for (i = 0; i < words.length; i++) {
            sum += words[i].length();
            if (sum + i - start > maxWidth) { // 贪心策略
                packWord(words, start, i - 1, sum - words[i].length(), maxWidth);
                start = i;
                sum = words[i].length();
            }
        }
        if (pool.size() < words.length) packWord2(words, start, i - 1, sum, maxWidth);
        return list;
    }

    // words 单词数据， start: 起始单词位置, end: 终止单词位置，　所有单词拼凑起来的长度，　width: 句子的长度
    public  void packWord(String[] words, int start, int end, int sum, int width) {
        if (start > end) return;
        StringBuffer sb = new StringBuffer();
        int interval = 0, mod = 0;
        if (start == end) {
            interval = width - sum;
        } else {
            interval = (width - sum) / (end - start);
            mod = (width - sum) % (end - start);
        }
        char[] temp = new char[interval];
        Arrays.fill(temp, ' ');
        for (int i = start; i <= end; i++) {
            sb.append(words[i]);
            pool.add(words[i]);
            if (i != end || start == end) { // 末尾不填
                sb.append(temp);
                if (mod != 0 && i - start < mod) {
                    sb.append(' ');
                }
            }
        }
        list.add(sb.toString());
    }

    
    public void packWord2(String[] words, int start, int end, int sum, int width) {
        StringBuffer sb = new StringBuffer();
        for (int i = start; i <= end; i++) {
            sb.append(words[i]);
            pool.add(words[i]);
            if (sb.length() < width) {
                sb.append(' ');
            }
        }
        int left = width - sb.length();
        char[] temp = new char[left];
        Arrays.fill(temp, ' ');
        sb.append(temp);
        list.add(sb.toString());
    }
}


// 需要注意末尾的处理，有比较特殊的情况
