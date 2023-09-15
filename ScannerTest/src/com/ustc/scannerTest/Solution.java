package com.ustc.scannerTest;

class Solution {
    public static void main(String[] args) {
        int[] gem = {3,1,2};
        int[][] operations = {{0,2},{2,1},{2,0}};

        System.out.println(giveGem(gem, operations));
    }

    public static int giveGem(int[] gem, int[][] operations) {
        for(int i = 0; i <= operations.length - 1; i++){
             calGive(gem, operations[i]);
        }

        // int max = Arrays.stream(gem).max();
        int max_index = 0;
        int min_index = 0;
        for(int i = 0; i<=gem.length - 1; i++){
            max_index = gem[i]>gem[max_index]?i:max_index;
            min_index = gem[i]<gem[min_index]?i:min_index;
        }

        return gem[max_index]-gem[min_index];
    }

    public static void calGive(int[] gem, int[] operate){ // 接收的是数组的地址值
        int giver = gem[operate[0]]/2;
        //receiver = operate[1];

        gem[operate[0]] = gem[operate[0]] - giver;
        gem[operate[1]] = gem[operate[1]] + giver;
    }
}
