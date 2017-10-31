/*

Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
Note: The solution set must not contain duplicate quadruplets.
For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

*/

// 我的解法, 两层  for 循环 + 双指针, 速度相对比较慢
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            for (int j = nums.length - 1; j > i + 2; j--) {// 固定两个数
                if (j != nums.length - 1 && nums[j] == nums[j + 1]) continue; // 去除重复
                int left = i + 1, right = j - 1;
                int remain = target - nums[i] - nums[j];
                while (left < right) {
                    int temp = nums[left] + nums[right];
                    if (temp == remain) {
                        List<Integer> l = new ArrayList<>();
                        l.add(nums[i]);
                        l.add(nums[left]);
                        l.add(nums[right]);
                        l.add(nums[j]);
                        list.add(l);
                        do{
                            left++;
                        }while(left < right && nums[left] == nums[left - 1]);

                        do{
                            right--;
                        }while(left < right && nums[left] == nums[right + 1]);

                    } else if (temp > remain) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return list;
    }
}

// 别人的代码, 经过优化, 优化剪枝方面还是值得学习的!
class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> ans = new ArrayList<>();
	    if(num.length < 4)return ans;
	    Arrays.sort(num);
	    for(int i = 0; i < num.length - 3; i++) {
	    	// 优化剪枝
	        if(num[i] + num[i+1] + num[i+2] + num[i+3] > target) break; //first candidate too large, search finished
	        
	        if(num[i] + num[num.length-1] + num[num.length-2] + num[num.length-3] < target) continue; //first candidate too small
	         
	        // 去除重复
	        if(i > 0 && num[i] == num[i-1])continue; //prevents duplicate result in ans list
	       
	        for(int j = i + 1; j < num.length - 2; j++){
	            
	            // 优化剪枝
	            if(num[i] + num[j] + num[j + 1] + num[j + 2] > target) break; //second candidate too large
	            // 最大值 
	            if(num[i] + num[j] + num[num.length - 1] + num[num.length - 2] < target) continue; //second candidate too small
	            
	            // 去除重复
	            if(j > i + 1 && num[j] == num[j - 1])continue; //prevents duplicate results in ans list

	            int low = j + 1, high = num.length - 1;

	            while(low < high){
	                
	                int sum = num[i] + num[j] + num[low] + num[high];
	                
	                if(sum == target){
	                    
	                    ans.add(Arrays.asList(num[i], num[j], num[low], num[high])); // 这个地方值得学习.

	                    // 此处可以改成 do ... while
	                    while(low < high && num[low] == num[low+1]) low++; //skipping over duplicate on low
	                    while(low < high && num[high] == num[high - 1]) high--; //skipping over duplicate on high
	                    low++; 
	                    high--;
	                }

	                //move window
	                else if(sum < target)low++; 
	                else high--;
	            }
	        }
	    }
 	   return ans;
	}
}
