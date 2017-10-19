/***
给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： 
{[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
**/
import java.util.*;
public class Solution {
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        // 使用双向队列, 对于每一次循环， 从队列尾比较，如果比当前元素小，出队列，同时
        // 比较对列头是否已经到末尾， 如果到末尾的话， 出队列，由于是每次插入一个元素
        // 所以每一次仅仅比较一个元素 并且队列头是最大的值，因为每一次都会比较元素的值
        // 先删除队列头, 从队列的尾部开始比较，
        ArrayList<Integer> list = new ArrayList<>();
        if(num == null || size > num.length || size < 1) return list;
        int begin;
        LinkedList<Integer> array = new LinkedList<>(); // 存放index
        for(int i = 0; i < num.length; i++){
            begin = i - size + 1;
            if (array.isEmpty())
                array.addLast(i);
            else if (array.getFirst() < begin) { // 一次仅仅添加一个元素，所以仅仅从队列头弹出一个元素
                array.removeFirst();
            }
            while (!array.isEmpty() && num[i] >= num[array.getLast()]) {
                array.removeLast();
            }
            array.addLast(i);　// 剩下的 array 都是比　ｉ　位置来得大的值
            if (begin >= 0) list.add(num[array.getFirst()]);
        }
        return list;
    }
    
}