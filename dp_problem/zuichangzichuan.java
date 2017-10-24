/****

要求：求两个字符串的最长公共子串，如“abcdefg”和“adefgwgeweg”的最长公共子串为“defg”（子串必须是连续的）

方法一: 暴力解法

方法二: 动态规划 

[java 实现](http://blog.csdn.net/xiaoyi357/article/details/70209164)

****/

// 方法一

public static String maxSubstring1(String strOne, String strTwo) {

    if (strOne == null &&  strTwo == null) return null;

    if (strOne == "" && strTwo == "")  return null;

    String maxStr = "";

    String minStr = "";

    if (strOne.length() > strTwo.length()) {
        maxStr = strOne;
        minStr = strTwo;
    } else {
        maxStr = strTwo;
        minStr = strOne;
    }

    // 切割较长的字符串，来匹配较短的字符串
    // 长度从最小串的长度往后递减
    // 两层循环遍历,  从最小的串的长度 n 到 1, 此处必须从后面进行遍历.
    for (int i = 0; i < minStr.length(); i++) {
        // 固定长度, 然后剪切最小的串, 判断大串是否含小串
        for (int begin = 0, end = minStr.length() - i; end <= minStr.length(); begin++, end++) {
            String temp = minStr.substring(begin, end);
            if (maxStr.contains(temp)) {
                return temp;
            }
        }
    }
    return null;
}

// 方法二: 两层数组, 节省空间  

public static String maxSubString2(String strOne, String strTwo) {
    if (strOne == null && strTwo == null) return null;
    if (strOne == "" && strTwo == "") return null;
    int len1 = strOne.length();
    int len2 = strTwo.length();
    int[] topLine = new int[len1];
    int[] newLine = new int[len1];
    int pos = 0, maxLen = 0;
    char c;
    for (int i = 0; i < len2; i++) {
        c = strTwo.charAt(i);
        for(int j = 0; j < strOne.length(); j++) {
            if (strOne.charAt(j) == c) {
                if (i == 0) {
                    newLine[j] = 1;
                } else {
                    newLine[j] = topLine[j - 1] + 1; //  此处需要使用 j - 1
                }
                if (newLine[j] > maxLen) {
                    pos = j;
                    maxLen = newLine[j];
                }
            }
        }
        topLine = newLine;
        newLine = new int[len1];
    }
     
    if (maxLen == 0) return null;
    return strOne.substring(pos-maxLen+1, pos+1);
}

// 方法三, 使用数组进行直接遍历, 动态规划.

public static String maxSubString3(String strOne, String strTwo) {
    if (strOne == null && strTwo == null) return null;
    if (strOne == "" && strTwo == "") return null;
    int[][] sum = new int[strOne.length()][strTwo.length()];
    int maxLen = 0, pos = 0;
    for (int i = 0; i < strOne.length(); i++) {
        for (int j = 0; j < strTwo.length(); j++) {
            if (strOne.charAt(i) == strTwo.charAt(j)) {
                if (i == 0 || j == 0) {
                    sum[i][j] = 1;
                } else {
                    sum[i][j] = sum[i - 1][j - 1] + 1;
                }
                if (sum[i][j] > maxLen) {
                    maxLen = sum[i][j];
                    pos = i;
                }
            }
        }
    }
    if (maxLen == 0) return null;
    return strOne.substring(pos - maxLen + 1, pos + 1);
}
