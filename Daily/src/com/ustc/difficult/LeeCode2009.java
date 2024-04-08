package com.ustc.difficult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LeeCode2009 {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{41,33,29,33,35,26,47,24,18,28}));; // 预期结果: 5
    }

    public static int minOperations(int[] nums) {
        int n = nums.length;
        // 去重 + 排序
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums) set.add(num);
        List<Integer> sortedArr = new ArrayList<>(set);
        Collections.sort(sortedArr);

        // 构建一个固定长度为[left, left+n-1]的滑动窗口，判断滑动窗口内包含sortedArr中最多几个点
        // left依次选取sortArr中的每一个值
        int result = 0; // 所有滑动窗口内包含sortedArr中最多几个点
        int rightIndex = 0;
        int maxContain = 0; // 当前滑动窗口内包含sortedArr中最多几个点
        for(int i=0; i<sortedArr.size(); i++){
            int left = sortedArr.get(i);
            int right = left + n - 1;
            while(rightIndex<sortedArr.size() && sortedArr.get(rightIndex)<=right){
                maxContain++; // cur在[left, right]区间内
                rightIndex++;
            }
            result = Math.max(result, maxContain);
            maxContain--; // 下一个循环left左移，会移除一个点
        }

        return n-result;
    }
}
