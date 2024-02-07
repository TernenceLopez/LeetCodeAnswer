package com.ustc.normal;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LeeCode1696 {
    public static void main(String[] args) {
        maxResult(new int[]{1,-1,-2,4,-7,3}, 2);
    }
    public static int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> queue = new ArrayDeque<>(); // 单调双端队列：队尾是最小值，队首是最大值

        for(int i=n-1; i>=0; i--){
            while(!queue.isEmpty() && dp[queue.peekLast()]<=dp[i]){
                queue.pollLast();
            }
            queue.addLast(i);

            while(queue.peekFirst()>i+k){
                queue.pollFirst();
            }
            dp[i] = dp[queue.peekFirst()] + nums[i];
        }

        return dp[0];
    }
}
