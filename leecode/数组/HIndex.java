/*
leetcode 274. H-Index
Given an array of citations (each citation is a non-negative integer) of a researcher, 
write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have 
at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total 
and each of them had received 3, 0, 6, 1, 5 citations respectively. 
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each,
 his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases

*/

／／　我的写法，　排序后进行从头进行遍历
class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        Arrays.sort(citations);
        int len = citations.length;
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= len - i) return len - i;// len 从大到小
        }
        return 0;
    }
}

／／　别人的代码
//Time: O(n), Space: O(n)
class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length < 1) {
            return 0;
        }
        // h-index wouldn't exceed the total number of articles
        // for example, even it is cited[100, 98, 0, 1, 5], 
        // we will only care about the number of cites including or below 5
        int n = citations.length; //n: number of articles
        int[] count = new int[n + 1]; // 数字统计
        //　大于 n　的数和不大于 n　的数分分开进行统计
        for (int i : citations) {
            if (i < citations.length) {
                count[i]++;
            } else {
                count[n]++;   
            }
        }

        int sum = 0;
        int max = 0;
        for (int i = n; i >= 0; i--) {
        	// 从后进行遍历, i 从大到小
            sum += count[i];
            if (sum >= i) {
                max = i; 
                break;
            }
        }

        return max;
    }
}

