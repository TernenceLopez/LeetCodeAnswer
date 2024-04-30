package com.ustc.normal;

import java.util.PriorityQueue;
import java.util.Queue;

public class LeeCode2462 {
    public static void main(String[] args) {
        System.out.println(totalCost(new int[]{57,33,26,76,14,67,24,90,72,37,30}, 11, 2));
    }

    public static long totalCost(int[] costs, int k, int candidates) {
        long result = 0;
        int n = costs.length;
        int leftBound = candidates-1; // 候选工人的左区间[0, leftBound] = [0, candidate-1]
        int rightBound = n - candidates;  // 候选工人的右区间[rightBound, n-1]
        PriorityQueue<Integer> leftQueue = new PriorityQueue<>();
        PriorityQueue<Integer> rightQueue = new PriorityQueue<>();

        if(leftBound >= rightBound){
            for(int cost: costs) leftQueue.add(cost);
            for(int i=0; i<k; i++) result += leftQueue.poll();
            return result;
        }

        for(int i=0; i<=leftBound; i++) leftQueue.add(costs[i]);
        for(int i=rightBound; i<n; i++) rightQueue.add(costs[i]);

        int count = 0; // 当前已经招募的员工个数
        while(leftBound < rightBound){
            int leftMinCost = leftQueue.peek();
            int rightMinCost = rightQueue.peek();
            if(leftMinCost <= rightMinCost){ // 选取左区间cost最小的员工
                result += leftQueue.poll();
                count++;
                if(leftBound < rightBound-1){
                    leftBound++;
                    leftQueue.add(costs[leftBound]);
                }else break;
            }else {
                result += rightQueue.poll();
                count++;
                if(leftBound < rightBound-1){
                    rightBound--;
                    rightQueue.add(costs[rightBound]);
                }else break;
            }
            if(count==k) return result;
        }

        // 此处的count一定小于k
        while(count<k){
            int leftMinCost = Integer.MAX_VALUE;
            int rightMinCost = Integer.MAX_VALUE;
            if(!leftQueue.isEmpty()) leftMinCost = leftQueue.peek();
            if(!rightQueue.isEmpty()) rightMinCost = rightQueue.peek();
            if(leftMinCost <= rightMinCost){ // 选取左区间cost最小的员工
                result += leftQueue.poll();
            }else {
                result += rightQueue.poll();
            }
            count++;
        }
        return result;
    }
}
