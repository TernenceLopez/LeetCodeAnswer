package com.ustc.normal;

public class LeeCode55 {
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{3,2,1,0,4}));
    }
    public static boolean canJump(int[] nums) {
        int curMax = nums[0];
        for (int i = 0; i <= curMax; i++){
            if(curMax >= nums.length-1) return true;
            curMax = Math.max(curMax, i + nums[i]);
        }
        return curMax >= nums.length-1;
    }
}
