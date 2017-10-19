/************
搜狐员工小王最近利用假期在外地旅游，在某个小镇碰到一个马戏团表演，
精彩的表演结束后发现团长正和大伙在帐篷前激烈讨论，小王打听了下了解到，
马戏团正打算出一个新节目“最高罗汉塔”，即马戏团员叠罗汉表演。考虑到安全因素，
要求叠罗汉过程中，站在某个人肩上的人应该既比自己矮又比自己瘦，或相等。 
团长想要本次节目中的罗汉塔叠的最高，由于人数众多，正在头疼如何安排人员的问题。
小王觉得这个问题很简单，于是统计了参与最高罗汉塔表演的所有团员的身高体重，
并且很快找到叠最高罗汉塔的人员序列。现在你手上也拿到了这样一份身高体重表，
请找出可以叠出的最高罗汉塔的高度，这份表中马戏团员依次编号为1到N。

输入描述:

首先一个正整数N，表示人员个数。 
之后N行，每行三个数，分别对应马戏团员编号，体重和身高。

输出描述:

正整数m，表示罗汉塔的高度。

输入

6
1 65 100
2 75 80
3 80 100
4 60 95
5 82 101
6 81 70
输出

4

*************/
import java.util.*; 
public class Main {
    public static void main(String[] args) {
        circus();
    }
    public static void circus() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            Arhat[] arhats = new Arhat[n];
            for (int i = 0; i < n; i++) {
                int f = in.nextInt();
                int w = in.nextInt();
                int h = in.nextInt();
                arhats[i] = new Arhat(h, w);
            }
            // 排序, 按照先身高后体重进行排序
            Arrays.sort(arhats, new Comparator<Arhat>() {
                @Override
                public int compare(Arhat arhat, Arhat t1) {
                   /* if (arhat.height > t1.height) {
                        return 1;
                    } else if (arhat.height == t1.height){
                        if (arhat.weight > arhat.weight) {
                            return 1;
                        } else if (arhat.weight == arhat.weight) {
                            return 0;
                        } else {
                            return -1;
                        }
                    } else {
                        return -1;
                    }*/
                    // 学习别人的代码
                    int result = Integer.compare(arhat.height, t1.height);
                    if (result != 0) {
                        return result;
                    }
                    return Integer.compare(arhat.weight, t1.weight);
                }
            });
            int maxHeight = Integer.MIN_VALUE;
            // dp 
            int[] dp = new int[n];
            for (int i = 0; i < n; i++) {
                dp[i] = 1;
                for (int j = i - 1; j >= 0; j--) { // 优化的地方
                    if (arhats[i].compareTo(arhats[j])) {
                        dp[i] = Math.max(dp[j] + 1, dp[i]); // 递归子结构, 比较清晰
                    }
                }
                maxHeight = Math.max(maxHeight, dp[i]);
            }
            System.out.println(maxHeight);
        }
        in.close();
    }
    static class Arhat{
        int height;
        int weight;
        public Arhat(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }
        public boolean compareTo(Arhat t1) {
            if (this.weight >  t1.weight) {
                return true;
            }
            if (this.height == t1.height 
            	&& this.weight == t1.weight) {
                return true;
            } else {
                return false;
            }
        }
    }
}


