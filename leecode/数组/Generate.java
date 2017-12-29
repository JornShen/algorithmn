/*
leetcode 118. Pascal's Triangle

Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
         [1],  0
        [1,1],  1
       [1,2,1],  2
      [1,3,3,1],  3
     [1,4,6,4,1],  4
    [1,5,10,10,5,1] 5
  [1,6,15,20,15,6,1] 6  
*/

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) return result;
        List<Integer> l = new ArrayList<>();
        l.add(1);
        result.add(l);
        List<Integer> before = l;
        for (int i = 1; i < numRows; i++) {
            l = new ArrayList<>();
            l.add(1);
            for (int j = 1; j < i; j++) {
                l.add(before.get(j - 1) + before.get(j));
            }
            l.add(1);
            result.add(l);
            before = l;
        }
        return result; 
    }
}