package com.ustc.difficult;

import java.util.Arrays;

public class LCP24 {
    public static void main(String[] args) {
        numsGame(new int[]{3,4,5,1,6,7});
    }
    public static int[] numsGame(int[] nums) {
        int n = nums.length;
        // 先对数组nums作预处理，即令 nums[i]=nums[i]−i
        for(int i=0; i<nums.length; i++){
            nums[i] = nums[i] - i;
        }
        for(int num: nums)
            System.out.print(""+num+",");
        System.out.println(" ");

        // 寻找中位数
        Arrays.sort(nums);
        int target = nums[nums.length/2];
        System.out.println(target);

        // 计算步数
        int[] result = new int[n];
        result[0] = Math.abs(nums[0]-target)%1_000_000_007;

        for(int i=1; i<nums.length; i++){
            result[i] = result[i-1] + Math.abs(nums[i]-target)%1_000_000_007;
        }

        return result;
    }
}
