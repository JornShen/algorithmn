/******
网易　回文序列

如果一个数字序列逆置之后跟原序列是一样的就称这样的数字序列为回文序列。例如：
{1, 2, 1}, {15, 78, 78, 15} , {112} 是回文序列, 
{1, 2, 2}, {15, 78, 87, 51} ,{112, 2, 11} 不是回文序列。
现在给出一个数字序列，允许使用一种转换操作：
选择任意两个相邻的数，然后从序列移除这两个数，并用这两个数字的和插入到这两个数之前的位置(只插入一个和)。
现在对于所给序列要求出最少需要多少次操作可以将其变成回文序列。

输入为两行，第一行为序列长度n ( 1 ≤ n ≤ 50)
第二行为序列中的n个整数item[i]  (1 ≤ iteam[i] ≤ 1000)，以空格分隔。

4
1 1 1 3

> 2 
*******/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by atmlinux on 17-9-2.
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num  = in.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(in.nextInt());
        }
        int first = 0, last = num - 1;
        int step = 0;
        while (first <= last) {
            if (list.get(first).equals(list.get(last))) {
                first++;
                last--;
            } else if (list.get(first) < list.get(last)){
                int temp = list.get(first) + list.get(first + 1);
                list.remove(first); // 删除位置的数
                list.remove(first);
                list.add(first, temp);
                last--;
                step++;
            } else {
                int temp = list.get(last) + list.get(last - 1);
                list.remove(last); // 删除位置的数
                list.remove(last - 1);
                list.add(last - 1, temp);
                last--;
                step++;
            }
        }
        System.out.print(step);
    }
}