/*
leetcode 120. Triangle
Given a triangle, find the minimum path sum from top to bottom. 
Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, 
where n is the total number of rows in the triangle.

*/

//　动态规划，自底向上
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> bottom = triangle.get(i + 1);
            List<Integer> curr = triangle.get(i);
            for (int j = 0; j < curr.size(); j++) {
                int tmp = curr.get(j);
                curr.set(j, tmp + Math.min(bottom.get(j), bottom.get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }
}




