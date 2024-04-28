package com.ustc.normal;

public class LeeCode1017 {
    public static void main(String[] args) {
        System.out.println(baseNeg2(3));;
    }
    // 位运算
    public static String baseNeg2(int n) {
        int res = 0;
        while(res<n){
            // 位运算操作默认按照二进制的方式进行移位：1<<1=2 而不是 10
            res = (res<<2)+1; // 获取当前位数的二进制位能够表示的最大值
        }
        res = res ^ (res-n);  // 需要在最大值的基础上减去(res-n)，使用异或操作来实现减操作
        return Integer.toBinaryString(res);
    }
}
