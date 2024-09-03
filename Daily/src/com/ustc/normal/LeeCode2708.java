package com.ustc.normal;

import java.util.PriorityQueue;

public class LeeCode2708 {
    /**
     * 给你一个下标从 0 开始的整数数组 nums ，它表示一个班级中所有学生在一次考试中的成绩。
     * 老师想选出一部分同学组成一个 非空 小组，且这个小组的 实力值 最大，如果这个小组里的学生下标为 i0, i1, i2, ... , ik ，
     * 那么这个小组的实力值定义为 nums[i0] * nums[i1] * nums[i2] * ... * nums[ik] 。
     * 请你返回老师创建的小组能得到的最大实力值为多少。
     * */
    public static void main(String[] args) {
        System.out.println(maxStrength(new int[]{3,-1,-5,2,5,-9}));
    }
    public static long maxStrength(int[] nums) {
        int countNeg = 0, zeroFlag = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->a-b);
        long res = 0;
        for(int num: nums){
            if(num < 0){
                countNeg++;
                pq.add(num);
            }else if(num > 0){
                if(res == 0) res = num;
                else res *= num;
            }else{
                zeroFlag = 1;
            }
        }
        while(countNeg >= 2){
            if(res == 0) res = 1;
            res *= pq.poll();
            res *= pq.poll();
            countNeg = countNeg - 2;
        }
        if(countNeg == 1 && res == 0 && zeroFlag == 0){
            res = pq.poll();
        }
        return res;
    }
}
