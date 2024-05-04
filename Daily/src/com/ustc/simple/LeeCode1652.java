package com.ustc.simple;

public class LeeCode1652 {
    public static void main(String[] args) {
        decrypt(new int[]{2,4,9,3}, -2);
    }

    public static int[] decrypt(int[] code, int k) {
        int n = 3 * code.length;
        int[] result = new int[n/3];  // 直接创建一个3倍的循环数组，避免考虑复杂的越界问题
        int[] newCode = new int[n];
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        // 创建循环数组
        for(int i=0; i<n/3; i++){
            newCode[i] = code[i];
            newCode[i+n/3] = code[i];
            newCode[i+2*n/3] = code[i];
        }
        // 计算newCode的前后缀和
        prefix[0] = newCode[0];
        for(int i=1; i<n; i++){
            prefix[i] = prefix[i-1] + newCode[i];
        }
        suffix[n-1] = newCode[n-1];
        for(int i=n-2; i>=0; i--){
            suffix[i] = suffix[i+1] + newCode[i];
        }
        // 计算result，直接截取newCode中的[n/3, 2*n/3]区间计算
        if(k==0){
            return result;
        }else if(k>0){
            for(int i=n/3; i<2*n/3; i++){
                result[i-n/3] = suffix[i+1] - suffix[i+1+k];
            }
        }else{
            for(int i=n/3; i<2*n/3; i++){
                result[i-n/3] = prefix[i-1] - prefix[i-1+k];
            }
        }

        return result;
    }
}
