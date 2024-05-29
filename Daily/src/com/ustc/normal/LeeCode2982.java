package com.ustc.normal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeeCode2982 {
    public static void main(String[] args) {
        maximumLength("aaaaa");
    }
    public static int maximumLength(String s) {
        int n = s.length();
        // 记录相同字符的特殊字符串长度列表
        Map<Character, List<Integer>> cnt = new HashMap<>();
        for(int i=0; i<n;){
            int j=i;
            for(; j<n;){
                if(s.charAt(i)==s.charAt(j)) j++;
                else break;
            }
            cnt.computeIfAbsent(
                    s.charAt(i),
                    k -> new ArrayList<Integer>()
            ).add(j-i);
            i=j;
        }

        int result = 0;
        for(Map.Entry<Character, List<Integer>> entry: cnt.entrySet()){
            List<Integer> lenList = entry.getValue();
            // 二分查找
            int left = -1, right = n-2;
            while(left<=right){
                int mid = (left+right)/2; // 寻找当前字符对应的最长特殊字符串
                int count = 0; // 能取到count个长度为mid的特殊字符串
                for(Integer len: lenList){
                    if(len>=mid) count += len-mid+1;
                }
                if(count>=3){ // 符合要求
                    result = Math.max(result, mid);
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
        }

        return result==0?-1:result;
    }
}
