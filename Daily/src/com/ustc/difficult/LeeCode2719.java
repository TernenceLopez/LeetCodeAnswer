package com.ustc.difficult;

import java.util.Arrays;
public class LeeCode2719 {
    public static final int MOD = 1_000_000_007;
    public static int[] nBit1; // 存放num1的每个数位
    public static int[] nBit2; // 存放num2的每个数位
    public static int min_sum;
    public static int max_sum;
    // 记忆化搜索
    static int[][] memo1;
    static int[][] memo2;

    public static void main(String[] args) {
        int result = count("4179205230","7748704426",8,46);
        System.out.println(result);
    }

    public static int count(String num1, String num2, int min_sum, int max_sum) {
        // 数据初始化工作
        char[] s1 = num1.toCharArray();
        char[] s2 = num2.toCharArray();
        nBit1 = new int[s1.length];
        nBit2 = new int[s2.length];
        for(int i = 0; i<s1.length; i++) nBit1[i] = s1[i] - '0';
        for(int i = 0; i<s2.length; i++) nBit2[i] = s2[i] - '0';
        LeeCode2719.min_sum = min_sum;
        LeeCode2719.max_sum = max_sum;
        memo1 = new int[s1.length][Math.min(max_sum, 9*s1.length)]; // s1.length个数位，最大数位和为9*s1.length
        memo2 = new int[s2.length][Math.min(max_sum, 9*s2.length)];
        for(int[] dp: memo1) Arrays.fill(dp, -1);
        for(int[] dp: memo2) Arrays.fill(dp, -1);

        // 求解好整数数目
        int result = 0;
        result = f(0, 0, true, false, nBit2, memo2);
        result -= f(0, 0, true, false, nBit1, memo1);

        // 单独计算num1是否是好整数
        int sum = 0;
        for(int bit: nBit1) sum = sum + bit;
        if((sum>=min_sum) && (sum<=max_sum)) result++; // num1符合要求

        return result % MOD;
    }

    // 返回值：填充到第i个数位时，前面的数位和为sum。此时继续填充第i个数位, 得到的好整数个数
    public static int f(int i, int sum, boolean isLimit, boolean isNum, int[] nBit, int[][] memo){
        if(sum>max_sum) return 0;
        if(i==nBit.length)
            return (sum>=min_sum) && (sum<=max_sum)?1:0; // 符合要求，返回1

        // !isLimit && isNum 保证两个状态(i, cnt)对应的上下界相同
        // 有可能出现两个状态值(i, cnt)相同，但是(isLimit, isNum)不相同的情况，此时i数位的上下界不一样，不能使用记忆化的值。
        if(!isLimit && isNum && memo[i][sum] != -1) return memo[i][sum];

        int result = 0;
        if(isNum == false){ // 跳过数位i，cnt保持不变
            result += f(i+1, sum, false, false, nBit, memo);
        }

        // 计算数位i符合要求的上下界
        int up = isLimit? nBit[i]: 9;
        int down = isNum? 0: 1;

        for(int bit = down; bit<= up; bit++){
            boolean newIsLimit = isLimit & (bit==up);
            boolean newIsNum = true;
            int newSum = sum + bit;
            if(newSum > max_sum) continue; // 已经大于max_sum，不可能得到好整数了，直接跳过这个bit
            else result += f(i+1, newSum, newIsLimit, newIsNum, nBit, memo);
        }

        if(!isLimit && isNum)
            memo[i][sum] = result;
        return result;
    }
}
