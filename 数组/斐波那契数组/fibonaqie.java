
// 斐波那契 数列及其变形


// 基本的运算规则
public int Fibonacci(int n) {
   		if(n == 0){
            return 0; 
        }else if(n == 1){
            return 1;
        }else{
            int left = 0,right = 1;
            int temp;
            for(int i = 1 ;i<n;i++){
				temp = left + right;
                left = right;
                right = temp;
            }
			return right;
        }
}

// 经典变形

/*******

一只青蛙一次可以跳上1级台阶，也可以跳上2级。
求该青蛙跳上一个n级的台阶总共有多少种跳法。

********/

// 递归做法

public int JumpFloor(int target) {        
        if (target <= 0) return 0;
        if (target == 1) return 1; 
        if (target == 2) return 2;
        
        return JumpFloor(target - 1) + JumpFloor(target - 2);
}

// 从递归改成 循环可以看出是 斐波那契的变形
// 正好是求 f(n), n 表示 步数


public class Solution {
    public int JumpFloor(int target) {
        if (target <= 0) return 0;
        if (target == 1) return 1; 
        if (target == 2) return 2;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= target; i++) {
            int temp = first + second;
            first = second;
            second = temp;
        }
        return second;
    }
}	



//  青蛙跳台阶的变形

/******

一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
求该青蛙跳上一个n级的台阶总共有多少种跳法。

*****/

// 递归做法

public class Solution {
    public int JumpFloorII(int target) {
        if (target <= 0) return 0;
        if (target == 1) return 1; 
        if (target == 2) return 2;
        int sum = 1;
        for (int i = 1; i <= target; i++) {
            
            sum += JumpFloorII(target - i);
            
        }
        return sum;
    }
}

// 非递归做法


/****

f(1) = 1

f(2) = f(2-1) + f(2-2)   //f(2-2) 表示2阶一次跳2阶的次数。

f(3) = f(3-1) + f(3-2) + f(3-3) = f(2) + f(1) + f(0) = 2 * f(2)

****/

// f(n) = 2*f(n-1) 

return  1<<--number; // 直接移位运算

//  移位运算


/********

我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，总共有多少种方法？

******/

// 此题的解法 和 跳台阶 是一样.

// 这里不在贴出代码

/***

f(n) = f(n - 1) + f(n - 2);

f(1) = 1;
f(2) = 2;

***/


