/*

leetcode 207. Course Schedule

There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 
you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, 
is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, 
and to take course 0 you should also have finished course 1. So it is impossible.
*/

// 我的写法，　拓扑排序
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] degree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        int edge = prerequisites.length;

        // 创建邻接表
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            degree[prerequisites[i][0]]++;
            list.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        // 入度为　0 的节点先进入队列 
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        // 拓扑排序核心代码
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            for (int i : list.get(tmp)) {
                edge--;
                if ((--degree[i]) == 0) {
                    queue.add(i);
                }
            }
        }

        if (edge != 0) return false;
        return true;
    }
}

／／　别人的代码，　似乎更加简洁
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 1 || prerequisites == null || prerequisites.length < 1) {
            return true;
        }
        int[] pre_require = new int[numCourses];
        //initial
        for (int i = 0; i < numCourses; i++) {
            pre_require[i] = i;
        }

        for(int i = 0; i < prerequisites.length; ++i){
        	int preCourse = prerequisites[i][1];
			int nowCourse = prerequisites[i][0];
			pre_require[nowCourse] = preCourse;
			// 寻找是否有环
			while(preCourse != pre_require[preCourse]){
				preCourse = pre_require[preCourse];
				if(nowCourse == preCourse)//form a circle means false, 有环存在
					return false;
			}
        }
        return true;
    }
}

