package com.ustc.normal;

import java.time.chrono.MinguoChronology;
import java.util.HashMap;
import java.util.Map;

public class LeeCode2244 {
    public static void main(String[] args) {
        minimumRounds(new int[]{2,2,3,3,2,4,4,4,4,4});
    }
    public static int minimumRounds(int[] tasks) {
        HashMap<Integer, Integer> itemCount = new HashMap<>();
        int result = 0;
        for(int item: tasks){
            int count = itemCount.getOrDefault(item, 0) + 1;
            itemCount.put(item, count);
        }
        for(Map.Entry<Integer, Integer> entry: itemCount.entrySet()){
            int occurTimes = entry.getValue();
            int triPair = occurTimes/3;
            int twoPair = occurTimes%3;
            if(twoPair==2){
                result = result + triPair + 1;
            }else if(twoPair==0){
                result = result + triPair;
            }else{ // 余数为1
                if(triPair>=1){
                    result = result + triPair + 1; // 拆出一个2
                }else{
                    return -1;
                }
            }
        }
        return result;
    }
}
