package com.ustc.difficult;

import java.util.Arrays;
import java.util.*;

public class LeeCode3267 {
    public static void main(String[] args) {
        int[] nums = {3,3,20,3,3,13,13,13,3,3,12,8,3,3,3,3,4};
        System.out.println(countPairs(nums));
    }

    /**
     * 1. 随机交换 nums 中元素的位置对最后的统计结果没有影响，因为交换后，两个数仍然满足近似条件
     * */
    public static int countPairs(int[] nums) {
        Arrays.sort(nums);
        // 记录当前已经遇到的所有数字
        Map<Integer, Integer> count = new HashMap<>();
        int result = 0;
        for(int num: nums){
            // 不交换就可以找到相似数, 不能在这里直接加result。
            // 避免交换两次之后，num又变回自身，导致重复计数
            // result += count.getOrDefault(num, 0);

            // 交换
            char[] array = Integer.toString(num).toCharArray();
            HashSet<Integer> set = new HashSet<>(); // 存储交换两次可以取得的所有可能情况
            for(int i=0; i<array.length; i++){
                for(int j=i+1; j<array.length; j++){
                    // 1. 第一次交换
                    swap(array, i, j);
                    int ex = Integer.parseInt(new String(array));
                    set.add(ex);
                    for(int k=0; k<array.length; k++){
                        for(int l=k+1; l<array.length; l++){
                            // 2. 第二次交换
                            swap(array, k, l);
                            ex = Integer.parseInt(new String(array));
                            set.add(ex);
                            swap(array, k, l); // 交换回去
                        }
                    }
                    swap(array, i, j); // 交换回去
                }
            }
            // 不交换
            set.add(num);

            // 遍历num交换两次所有可能情况
            for(int ex: set){
                result += count.getOrDefault(ex, 0);
            }
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        return result;
    }

    public static void swap(char[] array, int i, int j){
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
