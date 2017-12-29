/*
leetcode  119. Pascal's Triangle II
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

*/

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> l = new ArrayList<>();
        if (rowIndex == 0) { l.add(1);  return l;}
        l.add(1);
        l.add(1);
        for (int i = 2; i <= rowIndex; i++) {
            int left = 1, right = l.size() - 1;
            while (left <= right) {
                l.set(left, l.get(left) + l.get(right));
                left++;
                right--;
            }
            while (left < i) {
                l.set(left, l.get(i - left));
                left++;
            }
            l.add(1);
        }
        return l;
    }
}

// 别人的写法，更加精妙，　惊叹

class Solution {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> row = new ArrayList<>(); 
        long last = 1; 
        row.add((int)last); 
        for (int i=1; i<= rowIndex; i++) {
            last = last * (long)(rowIndex + 1 - i)/i; 
            row.add((int)last); 
        }
        
        return row; 
    }
}

