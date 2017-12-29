/*

Given an array of integers, every element appears three times except for one, which appears exactly once. 
Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

*/

// 使用　map　进行标记，　线性时间复杂度，　但是使用了额外的空间
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n, 1);
            } else {
                int tmp = map.get(n);
                if (tmp == 2) {
                    map.remove(n);
                } else {
                    map.put(n, tmp + 1);
                }
            }
        }
        int r = 0;
        for (int i : map.keySet()) {
            r = i;
            break;
        }
        return r;
    }
}

//　别人的写法
class Solution {
    public int singleNumber(int[] nums) {
        // 1. 存入ones
        // 2. 清空ones， 存入twos
        // 3. 清空twos
        int ones = 0, twos = 0;
        for(int i = 0; i < nums.length; i++){
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;    
    }
}

解析：　

我们把数组中数字的每一位累加起来对3取余，剩下的结果就是那个单独数组该位上的数字，由于我们累加的过程都要对3取余，
那么每一位上累加的过程就是0->1->2->0，换成二进制的表示为00->01->10->00，那么我们可以写出对应关系：

00 (+) 1 = 01

01 (+) 1 = 10

10 (+) 1 = 00 ( mod 3)

那么我们用ab来表示开始的状态，对于加1操作后，得到的新状态的ab的算法如下：

b = b xor r & ~a;

a = a xor r & ~b;

我们这里的ab就是上面的三种状态00，01，10的十位和各位，刚开始的时候，
a和b都是0，当此时遇到数字1的时候，b更新为1，a更新为0，就是01的状态；
再次遇到1的时候，b更新为0，a更新为1，就是10的状态；再次遇到1的时候，
b更新为0，a更新为0，就是00的状态，相当于重置了；最后的结果保存在b中。


