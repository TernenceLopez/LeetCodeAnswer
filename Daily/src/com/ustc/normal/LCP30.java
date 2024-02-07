package com.ustc.normal;

import java.util.PriorityQueue;

public class LCP30 {
    public static void main(String[] args) {
        magicTower(new int[]{100,100,100,-250,-60,-140,-50,-50,100,150});
    }
    public static int magicTower(int[] nums) {
        int sum = 0, n = nums.length;
        for(int num: nums) sum += num;
        if(sum<0) return -1; // 永远无法访问完所有房间
        System.out.println(sum);

        // 遍历[0,i]区间
        long allSum = 0; // 数值和
        long delay = 0;
        int count = 0; // 需要调整负数的次数
        long maxcount = 0;
        // 小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // int[]{负数值, 索引}
        for(int i=0; i<n; i++){
            if(nums[i]<0) pq.add(nums[i]);
            allSum += nums[i];
            maxcount = Math.max(maxcount, allSum);
            if(allSum <= 0){
                int cnt = count;
                for(int j=0; i<i-cnt+1; j++){ // 遍历到i时，最多可以调整i-count次
                    if(pq.isEmpty()) return -1;
                    allSum = allSum - pq.poll(); // 调整顺序
                    count++;
                    if(allSum > 0) break;
                }
                if(allSum <=0) return -1;
            }
        }

        System.out.println(maxcount);

        return 1;
    }
}
