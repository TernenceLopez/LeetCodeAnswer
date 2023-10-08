package com.ustc.difficult;

public class LeeCode123 {
    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};

        System.out.println(maxProfit(prices));
    }

    //
    public static int maxProfit(int[] prices) {
        int n = prices.length; // 状态转移次数

        // 状态转移初始状态
        int buy1 = -prices[0];
        int buy2 = -prices[0];
        int sell1 = 0, sell2 = 0;

        // 状态转移过程，最终的sell2就是最大值
        for(int i=1; i<n; i++){
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);

            System.out.println("buy1:"+buy1+" sell1:"+sell1+" buy2:"+buy2+" sell2:"+sell2 + " price:"+prices[i]);
        }

        return sell2;
    }
}

