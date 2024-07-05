package com.ustc.normal;

import java.util.*;

public class LeeCode3201 {
    public static void main(String[] args) {
        System.out.println(maximumLength(new int[]{1,2,3,4}));
    }
    /**
     dp[i]:
     0. "奇奇"循环
     1. "偶偶"循环
     2. "奇偶"循环以偶数结尾
     3. "奇偶"循环以奇数结尾
     */
    public static int maximumLength(int[] nums) {
        int n = nums.length;
        int[] dp = new int[4];

        for(int i=0; i<n; i++){
            if(nums[i]%2==0){
                dp[1] += 1; // 偶偶偶偶长度+1
                dp[2] = dp[3] + 1; // "奇偶交替"偶数结尾 = "奇偶交替"奇数结尾 + 1
            }else{
                dp[0] += 1;
                dp[3] = dp[2] + 1;
            }
        }

        return Math.max(
                Math.max(dp[0], dp[1]),
                Math.max(dp[2], dp[3])
        );
    }
}
