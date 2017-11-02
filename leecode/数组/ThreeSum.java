/*

leetcode 15:

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

*/

// 超时, 尽管做了很多的剪枝
public List<List<Integer>> threeSum(int[] nums) {
    // 三层 for 循环,比较低效, 三层 for 循环暴力解 + 减去
    // 不能重复
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
        if (nums[i] > 0) break; // 减枝
        if (i != 0 && nums[i] == nums[i - 1]) continue;
        for (int j = i + 1; j < nums.length - 1; j++) {
            if (nums[i] + nums[j] > 0) break;
            if (j != i + 1 && nums[j] == nums[j - 1]) continue;
            for (int k = j + 1; k < nums.length; k++) {
                int temp = nums[i] + nums[j] + nums[k];
                if (temp > 0) break;
                if (k != j + 1 && nums[k] == nums[k - 1]) continue;
                if (temp == 0) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[j]);
                    l.add(nums[k]);
                    list.add(l);
                }
            }
        }
    }
    return list;
}


1. 解法一: 排序，二分法查找，前后双指针O(n2*logn)

// 排序，二分法查找，前后双指针O(n2*logn)
public List<List<Integer>> threeSum(int[] nums) {

    Set<List<Integer>> set = new HashSet<>();
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 1 && nums[i] < 0; i++) { // 前指针
        if (i != 0 && nums[i] == nums[i - 1]) continue;
        for (int j = nums.length - 1; j > i; j--) { // 后指针
            int remain = 0 - (nums[i] + nums[j]);
            int third = Arrays.binarySearch(nums, i + 1, j, remain); // 二分查找
            if (third >= 0) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(nums[i]);
                list.add(nums[third]);
                list.add(nums[j]);
                set.add(list);
            }
        }
    }

    return new ArrayList<>(set);
}

2. 排序，一次外循环+前后指针 O(n2)

public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) { // 固定一个 数字,
        if (i != 0 && nums[i] == nums[i - 1]) continue;
        // 建立双指针
        int left = i + 1, right = nums.length - 1;
        while (left < right) {s
            int sum = nums[left] + nums[right] + nums[i];
            if (sum == 0) {
                // 记录情况
                List<Integer> l = new ArrayList<>();
                l.add(nums[i]);
                l.add(nums[left]);
                l.add(nums[right]);
                list.add(l);
                //  往两边扩展
                do{
                    left++;
                }while(left < right && nums[left] == nums[left - 1]);

                do{
                    right--;	
                }while(left < right && nums[left] == nums[right + 1]);

            } else if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }
    }
    return list;
}

//  碰到数组有序, 查找, 需要想到 二分查找 或 双指针 的思路.