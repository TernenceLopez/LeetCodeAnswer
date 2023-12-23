package com.ustc.difficult;

import java.util.Stack;

public class LeeCode1671 {
    public static void main(String[] args) {
        // 代码逻辑存在问题
        int result = minimumMountainRemovals(new int[]{23,47,63,72,81,99,88,55,21,33,32});

        System.out.println(result);
    }
    public static int minimumMountainRemovals(int[] nums) {
        // 以nums[i]为山顶时，前半部分元素个数的最大值
        int[] prefix = new int[nums.length];
        // 以nums[i]为山顶时，后半部分元素个数的最大值
        int[] suffix = new int[nums.length];
        Stack<int[]> stack1 = new Stack<>(); // int[]{元素值，下标}

        getPrefix(stack1, nums, prefix);
        int[] reversedNums = reverse(nums); // 反转数组
        while(!stack1.isEmpty()) stack1.pop(); // 清空栈
        getPrefix(stack1, reversedNums, suffix);
        suffix = reverse(suffix);

        // result为山的最长长度
        int result = 0;
        for(int i=0; i<nums.length; i++){
            if(prefix[i]>1 && suffix[i]>1){
                result = Math.max(result, prefix[i]+suffix[i]-1);
            }
        }

        return nums.length - result;
    }

    public static void getPrefix(Stack<int[]> stack1, int[] nums, int[] prefix){
        for(int i=0; i<nums.length; i++){
            while(!stack1.isEmpty() && stack1.peek()[0] >= nums[i]){
                stack1.pop();
            }
            if(stack1.isEmpty()){
                prefix[i] = 1;
            }else{
                prefix[i] = prefix[stack1.peek()[1]] + 1;
            }
            stack1.push(new int[]{nums[i] ,i});
        }
    }

    public static int[] reverse(int[] nums) {
        int n = nums.length;
        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = nums[n - 1 - i];
        }
        return reversed;
    }
}
