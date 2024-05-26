package com.ustc.normal;

import java.util.Arrays;

public class LeeCode2028 {
    public static void main(String[] args) {
        missingRolls(new int[]{1,1,1,1,1,1,1,1,1,1},
                1,
                1);
    }
    public static int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int sum = mean*(m+n);
        int sum_m = 0, sum_n = 0;
        for(int i=0; i<m; i++) sum_m += rolls[i];
        sum_n = sum - sum_m;

        if(sum_n<n || 1.0*sum_n/n>6.0){
            return new int[0];
        }

        int[] result = new int[n];
        Arrays.fill(result, 1); // 先给每一位赋值为1
        sum_n = sum_n - n;
        for(int i=0; i<n; i++){
            if(sum_n==0) return result;
            if(sum_n>5){
                result[i] += 5;
                sum_n -= 5;
            }else{
                result[i] += sum_n;
                return result;
            }
        }
        return result;
    }
}
