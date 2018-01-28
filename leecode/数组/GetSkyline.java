/*
leetcode 218. The Skyline Problem
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city 
when viewed from a distance. Now suppose you are given the locations and height of 
all the buildings as shown on a cityscape photo (Figure A), write a program to output 
the skyline formed by these buildings collectively (Figure B).

Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, 
and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. 
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], 
[5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of 
[ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
A key point is the left endpoint of a horizontal line segment. 
Note that the last key point, where the rightmost building ends, 
is merely used to mark the termination of the skyline, and always has zero height. 
Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented 
as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. 
For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; 
the three lines of height 5 should be merged into one in the final output 
as such: [...[2 3], [4 5], [12 7], ...]

*/

// 方法比较土，　空间超过限制
class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings.length == 0) return new ArrayList<>();
        if (buildings.length == 1) {
            List<int[]> l = new ArrayList<>();
            l.add(new int[]{buildings[0][0], buildings[0][2]});
            l.add(new int[]{buildings[0][1], 0});
            return l;
        }
        int left = buildings[0][0];
        int right = 0;
        // 寻找最右的边际，优化创建的空间
        for (int i = 0; i < buildings.length; i++) {
            right = Math.max(right, buildings[i][1]);
        }

        int[] height = new int[right-left];

        for (int i = 0; i < buildings.length; i++) {
            for (int j = buildings[i][0] - left; j < buildings[i][1] - left; j++) {
                height[j] = Math.max(height[j], buildings[i][2]);
            }
        }

        List<int[]> list = new ArrayList<>();
        list.add(new int[]{left, height[0]});
        for (int i = 1; i < height.length; i++) {
            if (height[i] != height[i-1]) {
                list.add(new int[]{i+left, height[i]}); 
            }
        }
        list.add(new int[]{right, 0});
        return list;
    }
}


// 常规的解法

class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        //　优先队列,　直接插入, 方便获得当前最大的高度
        PriorityQueue<Integer> queue = new PriorityQueue<>(11, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1 - integer;
            }
        });

        List<int[]> bl = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            bl.add(new int[]{buildings[i][0], buildings[i][2]});
            bl.add(new int[]{buildings[i][1], -buildings[i][2]}); // 记录终点
        }

        // 按 横坐标　进行排序
        Collections.sort(bl, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });
        int pre = 0, cur = 0;
        for (int i = 0; i < bl.size(); i++) {
            int[] tmp = bl.get(i);
            if (tmp[1] > 0) {
                queue.add(tmp[1]); // queue 第一个元素永远存放的是最大的高度
                cur = queue.peek();
            } else {
                // 清除队列中高度,　有可能清除最高的高度。
                queue.remove(-tmp[1]);
                cur = queue.size() == 0 ? 0 : queue.peek();
            }

            // 判断高度是否发生变化
            if (pre != cur) {
                res.add(new int[]{tmp[0], cur});
                pre = cur;
            }
        }
        return res;
    }
}

// 排名比较靠前的解法，　比较复杂
class Solution {
    class KeyPoint {
        public int key;
        public int height;
        public KeyPoint next = null;

        public KeyPoint(int key, int height) {
            this.key = key;
            this.height = height;
        }

    }

    public static int[] getKeyPoint(int key, int height) {
        int[] kp = new int[2];
        kp[0] = key;
        kp[1] = height;
        return kp;
    }

    public List<int[]> getSkyline(int[][] buildings) {
        KeyPoint head = new KeyPoint(-1,0);
        KeyPoint prevKP = head;
        // 用链表进行实现
        for (int[] building:buildings) {
            int l = building[0], r = building[1], h= building[2];
            // insert left point
            while (prevKP.next != null && prevKP.next.key <= l) prevKP = prevKP.next;
            int preHeight = prevKP.height;
            if (prevKP.key == l) prevKP.height = Math.max(prevKP.height, h);
            else if (prevKP.height < h) {
                KeyPoint next = prevKP.next;
                prevKP.next = new KeyPoint(l, h);
                prevKP = prevKP.next;
                prevKP.next = next;
            }
            // insert right point and update points in between
            KeyPoint prev = prevKP, cur = prevKP.next;
            while (cur != null && cur.key < r) {
                preHeight = cur.height;
                cur.height = Math.max(cur.height, h);
                if (cur.height == prev.height)
                    prev.next = cur.next;
                else
                    prev = cur;
                cur = cur.next;
            }
            if (prev.height != preHeight && prev.key != r && (cur == null || cur.key != r)) {
                KeyPoint next = prev.next;
                prev.next = new KeyPoint(r, preHeight);
                prev.next.next = next;
            }
        }
        // convert to List<int[]>
        List<int[]> list = new ArrayList<int[]>();
        KeyPoint prev = head, cur = head.next;
        while (cur != null) {
            if (cur.height != prev.height)
                list.add(getKeyPoint(cur.key, cur.height));
            prev = cur;
            cur = cur.next;
        }
        return list;
    }
}