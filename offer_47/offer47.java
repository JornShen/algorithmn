
/***
求1+2+3+...+n，要求不能使用乘除法、for、while、
if、else、switch、case等关键字及条件判断语句（A?B:C）。
*/

public class Solution {
    
    public int Sum_Solution(int n) {
	    int sum = n;
        boolean ans = (n>0)&&((sum+=Sum_Solution(n-1))>0);// 借用 && 实现递归的终止条件
        return sum; 
    }

    /***
	private int sum = 0;
    public int Sum_Solution(int n) {

        calculateSum(n);
        return sum;

    }

    public int calculateSum(int n){

        boolean i = (n != 0) && (calculateSum(n - 1) == 0);
        return sum += n;
    }
    */
    
}