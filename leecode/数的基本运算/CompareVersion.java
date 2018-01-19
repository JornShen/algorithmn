/*
leetcode 165. Compare Version Numbers

Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", 
it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37

*/

//　算法比较简单
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int len1 = s1.length, len2 = s2.length;
        int i = len1 - 1, j = len2 - 1;
        // 过滤 0,　从后往前进行过滤
        while (i >= 0 && Integer.parseInt(s1[i]) == 0) {
            i--;
            len1--;
        }
        while (j >= 0 && Integer.parseInt(s2[j]) == 0) {
            j--;
            len2--;
        }
        i = 0;
        j = 0;
        while (i < len1 && j < len2) {
            int a = Integer.parseInt(s1[i]);
            int b = Integer.parseInt(s2[j]);
            if (a == b) {
                i++;　j++;
            } else {
                return a > b ? 1 : -1;
            }
        }
        if (len1 == len2) return 0;
        return len1 > len2 ? 1 : -1;
    }
}

// 别人的做法，　算法真的写得很好！

class Solution {
    public int compareVersion(String version1, String version2) {
        char[] v1 = version1.toCharArray();
        char[] v2 = version2.toCharArray();
        int i = 0, j = 0; 
        int num1 = 0, num2 = 0;
        while(i < v1.length || j < v2.length){ // 比较巧妙的解决了，　末尾　0 的情况，　而且精确到字符处理，更加高效

            while(i < v1.length && v1[i] != '.'){
                num1 = num1*10 + (v1[i] - '0');
                i++;
            }
            
            while(j < v2.length && v2[j] != '.'){
                num2 = num2*10 + (v2[j] - '0');
                j++;
            }
            
            if　(num1 > num2)　return 1;
            if　(num1 < num2)　return -1;
            
            i++; j++; //　跨越　. 的操作
            num1 = 0; num2 = 0;
        }
        return 0;
    }
}
