package com.ustc.normal;

import java.util.Arrays;
import java.util.HashMap;

public class LeeCode698 {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println(canPartitionKSubsets(nums, k));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if(sum % k != 0) return false;
        int target = sum / k;
        // 记忆化搜索：存放status状态下能否将剩余元素组合成m个target集合 (m<=k)
        HashMap<Integer, Boolean> memo = new HashMap<>();
        return dfs(0, target, (1<<nums.length)-1, nums, memo);
    }

    public static boolean dfs(int cur, int target, int status, int[] nums, HashMap<Integer, Boolean> memo){
        if(status==0 && cur==0) return true;
        if(memo.containsKey(status)) return memo.get(status);

        for(int i=0; i<nums.length; i++){
            if(cur + nums[i] > target) continue;
            if((status>>i & 1) == 1){
                // 当前位的num[i]可以使用, status^(1<<i)将第i位置为0
                if(cur + nums[i] == target){
                    // 已经组合到了target，继续组合下一个target
                    if(dfs(0, target, status^(1<<i), nums, memo)) return true;
                }else {
                    if(dfs(cur+nums[i], target, status^(1<<i), nums, memo)) return true;
                }
            }
        }
        memo.put(status, false);
        return false;
    }
}
