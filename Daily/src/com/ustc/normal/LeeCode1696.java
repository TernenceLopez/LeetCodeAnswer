package com.ustc.normal;

import java.util.Arrays;

public class LeeCode1696 {
    public static void main(String[] args) {
        maxResult(new int[]{1,-1,-2,4,-7,3}, 2);
    }
    public static int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[n-1] = nums[n-1];

        for(int i=n-2; i>=0; i--){
            for(int j=i+1; j<=Math.min(n-1, i+k); j++){
                dp[i] = Math.max(dp[i], nums[i]+dp[j]);
            }
        }

        return dp[0];
    }
}
