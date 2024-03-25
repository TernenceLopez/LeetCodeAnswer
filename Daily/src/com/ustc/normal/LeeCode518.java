package com.ustc.normal;

import java.util.Arrays;
import java.util.HashSet;

public class LeeCode518 {
    public static void main(String[] args) {
        System.out.println(change(5, new int[]{1,2,5}));
    }

    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;

        for(int coin: coins){
            for(int i=coin; i<=amount; i++){
                dp[i] += dp[i-coin];
            }
        }

        return dp[amount];
    }
}
