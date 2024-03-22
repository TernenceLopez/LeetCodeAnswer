package com.ustc.normal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LeeCode2671 {
    HashMap<Integer, List<Integer>> freq = new HashMap<>(); // 记录相同频率的number
    HashSet<Integer> num = new HashSet<>();
    HashMap<Integer, Integer> curFreq = new HashMap<>(); // 记录元素当前的频率

    public static void main(String[] args) {
        LeeCode2671 leeCode2671 = new LeeCode2671();
        leeCode2671.add(3);
        leeCode2671.add(3);
        leeCode2671.hasFrequency(2);
    }

    public LeeCode2671() {

    }

    public void add(int number) {
        if(num.contains(number)){
            // 增加频率
            int curNumberFreq = curFreq.get(number);
            curFreq.put(number, curNumberFreq+1);
            freq.get(curNumberFreq).remove(number);
            List<Integer> list = freq.getOrDefault(curNumberFreq+1, new ArrayList<Integer>());
            list.add(number);
            freq.put(curNumberFreq+1, list);
        }else{
            num.add(number);
            curFreq.put(number, 1);
            List<Integer> list = freq.getOrDefault(1, new ArrayList<Integer>());
            list.add(number);
            freq.put(1, list);
        }
    }

    public void deleteOne(int number) {
        if(!num.contains(number)) return;
        // 频率减1
        int curNumberFreq = curFreq.get(number);
        if(curNumberFreq == 1){
            num.remove(number);
            freq.get(1).remove(number);
            if(freq.get(1).size()==0) freq.remove(1);
            curFreq.remove(number);
            return;
        }

        curFreq.put(number, curNumberFreq-1);
        freq.get(curNumberFreq).remove(number);
        List<Integer> list = freq.getOrDefault(curNumberFreq-1, new ArrayList<Integer>());
        list.add(number);
        freq.put(curNumberFreq-1, list);
        // freq.getOrDefault(curNumberFreq-1, new ArrayList<Integer>()).add(number);
        return;
    }

    public boolean hasFrequency(int frequency) {
        return freq.containsKey(frequency);
    }
}
