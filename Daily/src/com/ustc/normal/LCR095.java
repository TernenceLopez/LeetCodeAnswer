package com.ustc.normal;

import java.util.*;

public class LCR095 {
    public static void main(String[] args) {
        System.out.println(function("abcde", "ace"));
    }

    public static int function(String text1, String text2) {
        // dp[i][j]: text1长度为i时，text2长度为j时的最长公共子序列
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        // 边界条件: dp[0][...]、dp[...][0] 都为0

        // 状态转移
        for(int i=1; i<=text1.length(); i++){
            for(int j=1; j<=text2.length(); j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
                else{
                    // text1[0~i-1] 和 text2[0~j-1] 最后一个字符不相同
                    // 1. text1 不选择最后一个字符
                    dp[i][j] = dp[i-1][j];
                    // 2. text2 不选择最后一个字符
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
                    // 3. text1, text2都不选择最后一个字符
                    // 上述两种情况一定包含了第三种情况
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
