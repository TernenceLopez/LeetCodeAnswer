package com.ustc.normal;

import java.util.Arrays;

public class LeeCode2471 {
    // 记忆化搜索 + DFS搜索 + 状态压缩(类似于自定义hash算法)
    public static void main(String[] args) {
        System.out.println(specialPerm(new int[]{1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192}));
    }

    public static int specialPerm(int[] nums) {
        long result = 0;
        int n = nums.length;
        // memo[S][i]：可取元素集合S + 上一个选取元素的索引为i时对应的特别排列个数
        long[][] memo = new long[(1<<n)-1][n];
        for(long[] each: memo) Arrays.fill(each, -1);

        for(int i=0; i<nums.length; i++){
            int[] used = new int[nums.length]; // used[i]记录已经选择的元素
            used[i] = 1;
            result += dfs(nums, used, i, 1, memo);
            result = result % 1000_000_007;
        }

        return (int)result;
    }

    public static long dfs(int[] nums, int[] used, int lastIndex, int countUsed, long[][] memo){
        if(countUsed==nums.length){
            return 1; // 找到一个特别排序
        }
        int hash = hashCode(used);
        if(memo[hash][lastIndex]!=-1) return memo[hash][lastIndex];

        long count = 0;
        for(int i=0; i<nums.length; i++){
            if(used[i]==0 && (nums[i]%nums[lastIndex]==0 || nums[lastIndex]%nums[i]==0)){
                used[i] = 1;
                count += dfs(nums, used, i, countUsed+1, memo) % 1000_000_007;
                used[i] = 0;
            }
        }
        // 记忆化
        memo[hash][lastIndex] = count;
        return count;
    }

    // 自定义哈希算法
    public static Integer hashCode(int[] used){
        int n = used.length;
        int hashResult = (1<<n) - 1;  // 表明n位集合中所有元素都可取
        for(int i=0; i<used.length; i++){
            if(used[i]==1) hashResult ^= (1<<i);  // 将该位置为0，表示已经使用过了
        }
        return hashResult;
    }
}
