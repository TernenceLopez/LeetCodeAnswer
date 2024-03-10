package com.ustc.normal;

import java.lang.foreign.ValueLayout;
import java.util.Arrays;

public class LeeCode2369 {
    public static void main(String[] args) {
        validPartition(new int[]{993335,993336,993337,993338,993339,993340,993341});
    }
    public static boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n]; // dp[i]: [0,i]是否可以有效划分
        Arrays.fill(dp, false);

        for(int i=1; i<n; i++){
            if(nums[i]==nums[i-1]){
                if(i==1) dp[i] = true;
                else{
                    if((nums[i]-nums[i-1] == 0) && (nums[i-1]-nums[i-2] == 0) && i==2){
                        dp[i] = true;
                    }else if((nums[i]-nums[i-1] == 0) && (nums[i-1]-nums[i-2] == 0)){
                        dp[i] = dp[i-3] || dp[i-2];
                    }else if(nums[i]-nums[i-1] == 0) dp[i] = dp[i-2];
                    else dp[i] = false;
                }
            }else{ // nums[i] - nums[i-1] == 1
                if(i==1){
                    dp[i] = false; continue;
                }else if(i==2){
                    if((nums[i]-nums[i-1] == 1) && (nums[i-1]-nums[i-2] == 1)){
                        dp[i] = true;
                    }else{
                        dp[i] = false;
                    }
                }else{ // i>2
                    if((nums[i]-nums[i-1] == 1) && (nums[i-1]-nums[i-2] == 1)){
                        dp[i] = dp[i-3];
                    }else{
                        dp[i] = false;
                    }
                }
            }
        }

        return dp[n-1];
    }
}
