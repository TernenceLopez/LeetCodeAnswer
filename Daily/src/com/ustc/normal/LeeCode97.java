package com.ustc.normal;

public class LeeCode97 {
    public static void main(String[] args) {
        isInterleave("aabcc","dbbca","aadbbcbcac");
    }
    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), t = s3.length();
        if (n + m != t)
            return false;

        // dp[i][j]: s1的前i个字符 + s2的前j个字符能否交替组成s3的前(i+j)个字符
        // 由于包含s1、s2任何字符都不选的情况，所以长度设置为[m+1][n+1]
        boolean[][] dp = new boolean[m+1][n+1];

        // 初始化
        dp[0][0] = true; // s1前0个字符 + s2前0个字符

        // 状态转移
        for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(i==0 && j==0) continue;

                if(i==0) dp[0][j] = dp[0][j-1] && s2.charAt(j-1)==s3.charAt(j-1);
                else if(s1.charAt(i-1)==s3.charAt(i+j-1))
                    dp[i][j] = dp[i][j] || dp[i-1][j];

                if(j==0) dp[i][0] = dp[i-1][0] && s1.charAt(i-1)==s3.charAt(i-1);
                else if(s2.charAt(j-1)==s3.charAt(i+j-1))
                    dp[i][j] = dp[i][j] || dp[i][j-1];
            }
        }

        return dp[m][n];
    }
}
