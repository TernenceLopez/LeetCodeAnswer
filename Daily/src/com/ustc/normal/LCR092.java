package com.ustc.normal;

public class LCR092 {
    public static void main(String[] args) {
        System.out.println(minFlipsMonoIncr("00110"));
    }

    public static int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[][] dp = new int[2][n]; // 以0结尾 or 以1结尾的子字符串最小翻转次数
        if(s.charAt(0)=='0'){
            dp[0][0] = 0; dp[1][0] = 1;
        }else{
            dp[0][0] = 1; dp[1][0] = 0;
        }

        for(int i=1; i<n; i++){
            if(s.charAt(i)=='0'){
                dp[0][i] = dp[0][i-1];
                dp[1][i] = Math.min(dp[1][i-1]+1, dp[0][i-1]+1);
            }else{ // s.charAt(i)=='1'
                dp[1][i] = Math.min(dp[1][i-1], dp[0][i-1]);
                dp[0][i] = dp[0][i-1]+1;
            }
        }
        return Math.min(dp[0][n-1], dp[1][n-1]);
    }
}
