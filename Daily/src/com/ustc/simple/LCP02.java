package com.ustc.simple;

public class LCP02 {
    public static void main(String[] args) {
        fraction(new int[]{3, 2, 0, 2});
    }

    public static int[] fraction(int[] cont) {
        int n = cont.length;
        int fenzi = cont[n-1], fenmu = 1;
        for(int i=n-1; i>0; i--){
            // 分子分母颠倒
            int temp = fenmu;
            fenmu = fenzi;
            fenzi = temp;
            // 计算分式
            fenzi = cont[i-1]*fenmu + 1;
            fenmu = fenmu;
        }

        return new int[]{fenzi, fenmu};
    }
}
