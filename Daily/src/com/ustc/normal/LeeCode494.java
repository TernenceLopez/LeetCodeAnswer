package com.ustc.normal;

public class LeeCode494 {
    public static void main(String[] args) {
        System.out.println(Solution.findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1}, 1));;
    }
}

/*
1. pos - neg = target
2. pos + neg = sum
-> pos = (sum+target)/2
背包：从nums中选k个数，使其和等于pos
*/
class Solution {
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int each : nums)
            sum += each;
        int pos = (sum + target) / 2;
        // (sum + target)不能被2整除 || pos大于数组之和 || pos小于0
        if ((sum + target) % 2 != 0 || pos > sum || pos<0) {
            return 0;
        }

        // dp[i][j]：nums前i个数取到j的方案数
        int[][] dp = new int[nums.length][pos+1];
        for(int i=0; i<nums.length; i++){
            for(int j=0; j<=pos; j++){
                /* nums前i个数取到j的方案数 =
                不取num[i], nums前i-1个数取到j的方案数 +
                取nums[i], nums前i个数取到j-nums[i]的方案数
                */
                if(i==0){
                    if(j==0 && nums[0]==0){
                        // 特殊情况：取0或者不取0
                        dp[i][j] = 2;
                        continue;
                    }
                    // j==0表示可以不取num[0]
                    if(j==0 || j==nums[0]) dp[i][j] = 1;
                    else dp[i][j] = 0;
                    continue;
                }
                if(j>=nums[i])
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]];
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[nums.length-1][pos];
    }
}
