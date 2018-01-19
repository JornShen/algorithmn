/*
leetcode 179. Largest Number

Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

*/

// 将数组进行重新排序，　核心代码在于理解排序的方式，　compare 方法
class Solution {
    public String largestNumber(int[] nums) {
        
        List<String> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i + "");
        }
        
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                String tmp1 = s + t1;
                String tmp2 = t1 + s;
                return tmp1.compareTo(tmp2);
            }
        });

        StringBuffer buffer = new StringBuffer();
        for (int i = nums.length - 1; i >= 0; i--) {
            buffer.append(list.get(i));
        }
        
        // 去除 0, 0的这种情况
        int pos;
        for (pos = 0; pos < buffer.length() - 1 && buffer.charAt(pos) == '0'; pos++) ;
        
        return buffer.toString().substring(pos);
    }
}

