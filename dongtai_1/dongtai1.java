/******
有 n 个学生站成一排，每个学生有一个能力值，
牛牛想从这 n 个学生中按照顺序选取 k 名学生，
要求相邻两个学生的位置编号的差不超过 d，
使得这 k 个学生的能力值的乘积最大，你能返回最大的乘积吗？

输入

3
7 4 7
2 50

输出
49

*******/

package alogithm;

import java.util.Scanner;

/**
 * Created by atmlinux on 17-9-3.
 */
public class Main1 {

    // 理解错了，　顺序这个词理解错了。 不是连续意思，只要顺序满足即可, 错误理解题目的意思
   /* public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] arrayTemp = new int[num];
        for (int i = 0; i < num; i++) {
            arrayTemp[i] = in.nextInt();
        }
        int n = in.nextInt();
        int limit = in.nextInt();

        // 延长数组,　考虑循环的情况
        int[] array;
        if (num <= limit) {
            // 可以循环
            array = new int[num + n - 1];
            System.arraycopy(arrayTemp, 0, array, 0, num);
            System.arraycopy(arrayTemp, 0, array, num, n - 1);
        } else {
            array = arrayTemp;
        }
        int max = Integer.MIN_VALUE, flag = 1, temp = array[0];
        if (n == 1) max = max < temp ? temp : max;
        for (int i = 1; i < array.length; i++) {
            if (n == 1) {
                max = max < array[i] ? array[i] : max;
                continue;
            }
            temp *= array[i];
            flag++;
            if (flag == n) {
                max = max < temp ? temp : max;
                // 处理 0 的特殊情况
                if (array[i - n + 1] == 0) {
                    temp = array[i];
                    for (int j = i - 1; j > (i - n + 1); j--) {
                        temp *= array[j];
                    }
                } else {
                    temp /= array[i - n + 1];
                }
                flag--;
            }
        }
        System.out.println(max);
    }*/

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] arr = new int[num + 1];
        for (int i = 1; i < num; i++) {
            arr[i] = in.nextInt();
        }
        int K = in.nextInt();
        int d = in.nextInt();

        int[][] fmax = new int[K + 1][num + 1];
        int[][] fmin = new int[K + 1][num + 1];
        // fmax[k][i]表示 : 当选中了k个学生，并且以第i个学生为结尾，所产生的最大乘积；
        // fmin[k][i]表示 : 当选中了k个学生，并且以第i个学生为结尾，所产生的最小乘积；
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= num; i++) {
            //　以i为结尾
            fmax[1][i] = fmin[1][i] = arr[i]; // 选择一位学生

            // 从 2　位学开始
            for (int k = 2; k <= K; k++) {
                // 遍历k-1 位学生的位置
                for (int j = i - 1; j > 0 && i - j <= d; j--) {
                	// 状态转移方程，　核心代码
                    fmax[k][i] = Math.max(fmax[k][i], Math.max(fmax[k - 1][j] * arr[i], fmin[k - 1][j] * arr[i]));
                    fmin[k][i] = Math.min(fmax[k][i], Math.min(fmax[k - 1][j] * arr[i], fmin[k - 1][j] * arr[i]));
                }
                max = Math.max(max, fmax[K][i]);
            }
        }
        System.out.println(max);
    }
}

//---------------- 解析 ------------------------
因为有正有负，负负得正，所以要维护两个dp数组，一个存储最大，一个存储最小。
定义fm[k][i]表示当选中了k个学生，并且以第i个学生为结尾，所产生的最大乘积；
        fn[k][i]表示 当选中了k个学生，并且以第i个学生为结尾，所产生的最小乘积；
那么
     fm[k+1][i+1]=max(fm[k][i]*stu[i+1],fn[k][i]*stu[i+1])，

即当选中了k个学生后，再选择第i+1编号学生，所产生的最大乘积；然而，并不能保证上一次选择的就是第i个学生，所以要遍历子数组fm[k][j]，
        令j从i到1，并且j与i+1之间小于间隔D，遍历fm[k][j]，以及fn[k][j]；

同理fn[k+1][i+1]=min(fn[k][i]*stu[i+1],fm[k][i]*stu[i+1])。

最后，遍历一遍fm[K][i]求得最大值（i从1～N）。

// -------------------------------------------




