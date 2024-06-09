package com.ustc.difficult;

public class LeeCode312 {
    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{3,1,5,8}));
    }
    // 一个大问题分解多个子问题
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n+2];
        arr[0] = 1; arr[n+1] = 1;
        for(int i=1; i<n+1; i++){
            arr[i] = nums[i-1];
        }

        // dp[i][j]: (i,j)区间的气球都扎破能够取到的最大值
        // 最终返回 arr 数组(0, n+1)区间的气球都扎破能够取到的最大值（即dp[0][n+1]）
        // 为了能将dp[i][j]拆分成更小的子问题，引入一个k，即最后扎破编号为k的气球
        // 最后扎破编号为k的气球，因为(i,j)为开区间，所以气球k的得分为arr[k]*arr[i]*arr[j]。
        // 所以 dp[i][j]可以表示为：
        // (i,j)区间的最大值 = (i,k)区间的最大值 + (k,j)区间的最大值 + 气球k的得分
        // dp[i][j] = dp[i][k] + dp[k][j] + arr[k]*arr[i]*arr[j]
        int[][] dp = new int[n+2][n+2];
        for(int i=n+1; i>=0; i--){ // i∊[0,n+1] 注意i必须是从右边界递减到0的，保证内循环每个dp[k][j]都是正确的值
            for(int j=i+1; j<=n+1; j++){
                for(int k=i+1; k<j; k++){ // k在(i,j)开区间中取值
                    int sum = arr[i] * arr[k] * arr[j];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }

        return dp[0][n+1];
    }
}
