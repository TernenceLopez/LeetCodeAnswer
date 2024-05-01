package com.ustc.normal;

import javax.sound.midi.MidiChannel;
import java.util.ArrayList;
// 题意理解错了
public class LeeCode3133 {
    public static void main(String[] args) {
        System.out.println(minEnd(4, 1));
    }

    public static long minEnd(int n, int x) {
        // 第一个元素必然是 x
        // 1. 计算x中0的个数和位置
        String binary = Integer.toBinaryString(x);
        ArrayList<Integer> zeroIndex = new ArrayList<>();
        for(int i=binary.length()-1; i>=0; i--){
            if(binary.charAt(i)=='0') zeroIndex.add(binary.length()-1-i);
        }
        // 1. 计算应该添加的 1 的位置
        if(n-1 > zeroIndex.size()){
            return x | (1<<(n-1-zeroIndex.size() + binary.length()-1));
        }else{
            return x | (1<<(zeroIndex.get(n-1-1)));
        }
    }
}
