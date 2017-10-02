/******
现在有两个好友A和B，住在一片长有蘑菇的由n＊m个方格组成的草地，A在(1,1),B在(n,m)。
现在A想要拜访B，由于她只想去B的家，所以每次她只会走(i,j+1)或(i+1,j)这样的路线，
在草地上有k个蘑菇种在格子里(多个蘑菇可能在同一方格),问：A如果每一步随机选择的话(若她在边界上，则只有一种选择)，
那么她不碰到蘑菇走到B的家的概率是多少？

输入描述:

第一行N，M，K(1 ≤ N,M ≤ 20, k ≤ 100),N,M为草地大小，接下来K行，每行两个整数x，y，代表(x,y)处有一个蘑菇。


输出描述:

输出一行，代表所求概率(保留到2位小数)
示例1
输入

2 2 1
2 1
输出

0.50

******/
import java.util.*;
public class Main {
    public static void main(String[] args) {
        moguzhen();
    }
    public static void moguzhen() {
        Scanner in = new Scanner(System.in);
         while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            boolean[][] mogu = new boolean[n + 1][m + 1]; // 默认是 false
            for (int i = 0; i < k; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                mogu[a][b] = true;
            }
            double[][] m1 = new double[n + 1][m + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    //  思路是将所有走的路径数 除以 总的路径数, 结果是错误的.每一个格子的概率P是不一样的
                   /* if (i == 1 || j == 1) {
                        m1[i][j]  = 1;
                        if (mogu[i][j]) {
                            m2[i][j] = 0;
                            continue;
                        }
                        if (i == 1 && j == 1) {
                            m2[i][j] = 1;
                        } else {
                            m2[i][j] = j == 1 ? m2[i - 1][j] : m2[i][j - 1];
                        }
                    } else {
                        m1[i][j] = m1[i - 1][j] + m1[i][j - 1];
                        if (mogu[i][j]) {
                            m2[i][j] = 0;
                            continue;
                        }
                        m2[i][j] = m2[i - 1][j] + m2[i][j - 1];
                    }*/

                    // 考虑概率进行加权

                    if (i == 1 && j == 1) {
                        m1[i][j] = 1;
                        continue;
                    }

                    if (mogu[i][j]) {
                        // 无法到达
                        m1[i][j] = 0;
                        continue;
                    }

                    if (i == n && j == m) {
                    	// 唯一终点
                        m1[i][j] = m1[i][j - 1] + m1[i - 1][j];
                        continue;
                    }

                    if (i == n) {
                        m1[i][j] = 0.5 * m1[i - 1][j] + m1[i][j - 1];
                        continue;
                    }

                    if (j == m) {
                        m1[i][j] = m1[i - 1][j] + 0.5 * m1[i][j - 1];
                        continue;
                    }

                    if (i == 1) {
                        m1[i][j] = 0.5 * m1[i][j - 1];
                        continue;
                    }

                    if (j == 1) {
                        m1[i][j] = 0.5 * m1[i - 1][j];
                        continue;
                    }

                    m1[i][j] = m1[i - 1][j] * 0.5 + m1[i][j - 1] * 0.5;
                
                }
            }

            System.out.format("%.2f\n", m1[n][m]); // 需要换行进行处理.
         
         }

    }
    
}



