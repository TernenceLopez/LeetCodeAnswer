package com.ustc.normal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeeCode2831 {
    public static void main(String[] args) {
        ArrayList<Integer> param = new ArrayList<>();
        param.add(1);
        param.add(2);
        param.add(1);
        System.out.println(longestEqualSubarray(param, 0));
    }
    public static int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        // 记录相同元素出现的位置
        HashMap<Integer, List<Integer>> pos = new HashMap<>();
        for(int i=0; i<n; i++){
            if(pos.containsKey(nums.get(i))){
                pos.get(nums.get(i)).add(i);
            }else{
                ArrayList<Integer> t_list = new ArrayList<>();
                t_list.add(i);
                pos.put(nums.get(i), t_list);
            }
        }
        // 循环每个元素，查看每个元素能够构成的最长子数组
        int result = 0;
        for(List<Integer> numList: pos.values()){ // 元素a在nums中出现的下标列表
            // [numList[i], numList[j]]组成滑动窗口
            for(int i=0, j=0; i<numList.size() && j<numList.size();){
                int windowLen = numList.get(j)-numList.get(i)+1;
                int occur = j-i+1; // 元素a在窗口中出现的次数
                if(windowLen-occur <= k){
                    j++;
                }else{ // 其余元素出现次数已经大于k
                    i++;
                    j=i;
                    continue;
                }
                result = Math.max(result, occur);
            }
        }
        return result;
    }
}
