package com.ustc.normal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeeCode2007 {
    public static void main(String[] args) {
        findOriginalArray(new int[]{0,0,0,0});
    }

    public static int[] findOriginalArray(int[] changed) {
        if(changed.length%2 != 0) return new int[0];
        Arrays.sort(changed);
        Map<Integer, Integer> count = new HashMap<>();
        for(int item : changed){
            count.put(item, count.getOrDefault(item, 0)+1);
        }

        int[] res = new int[changed.length/2];
        int addCount = 0; // 统计已经添加到res中的元素个数
        for(int item: changed){
            if(count.get(item)==0) continue;
            count.put(item, count.get(item)-1);
            if(count.getOrDefault(item*2, 0)==0) return new int[0];
            count.put(item*2, count.get(item*2)-1);
            res[addCount++] = item;
        }
        return res;
    }
}
