/*
leetcode 134. Gas Station

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel 
from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

// 我的做法，算法不是最优的，　虽然做了点优化的地方 
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] diff = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            diff[i] = gas[i] - cost[i];
        }
        for (int i = 0; i < diff.length; i++) {
            if (diff[i] >= 0) {
                int start = i, from = i;
                int tank = diff[start];
                from++;
                from = from == diff.length ? 0 : from;
                while (from != start) {
                    tank += diff[from];
                    if (tank < 0) break;
                    from++;
                    from = from == diff.length ? 0 : from;
                }
                if (from == start) return from;
                else {
                    i = from > i ? from : i; // 从不满足条件的地方
                }
            }
        }
        return -1;
    }
}

//　标准的做法，　非常简洁, O(n) 的复杂度
class Solution {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        
        int tank = 0;
        int start = 0;
        int total = 0;
        
        for (int i = 0; i < gas.length; i++) {
            tank = tank + gas[i] - cost[i]; //　累加计算的结果
            if (tank < 0) {
                start = i + 1;
                total = total + tank;
                tank = 0;
            }
        }

        return (total + tank < 0) ? -1 : start;　// 判断从　start 开始走的时候，是否能卖满足条件的回到原点 
    }
}

total 这边的记录非常巧妙，非常精妙。
