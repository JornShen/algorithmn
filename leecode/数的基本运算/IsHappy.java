/*
leetcode 202. Happy Number
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: 
Starting with any positive integer, replace the number by the 
sum of the squares of its digits, and repeat the process until 
the number equals 1 (where it will stay), or it loops endlessly 
in a cycle which does not include 1. Those numbers for which 
this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

*/ 

//　我的做法，　比较土
class Solution {
    public boolean isHappy(int n) {
        Set<Double> set = new HashSet<>();
        double sum = 0;
        while (sum != 1) {
            sum = 0;
            while (n != 0) {
                int mod = n % 10;
                sum += mod * mod;
                n /= 10;
            }
            if (set.contains(sum)) return false;
            set.add(sum);
            n = (int)sum;
        }
        return true;
    }
}

// 出现　4  就不可能是快乐数
class Solution {
    public boolean isHappy(int n) {
       	while (n != 1 && n != 4) {
            int t = 0;
            while (n) {
                t += (n % 10) * (n % 10);
                n /= 10;
            }
            n = t;
        }
        return n == 1;
    }
}


