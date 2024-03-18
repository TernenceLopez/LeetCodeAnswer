package com.ustc.normal;

import java.util.Arrays;

public class LeeCode3085 {
    // 只有26个字母，所以复杂度很低
    class Solution {
        public int minimumDeletions(String word, int k) {
            // 统计每个单词出现的频率
            int[] freq = new int[26];
            for(int i=0; i<word.length(); i++){
                freq[word.charAt(i)-'a']++;
            }
            Arrays.sort(freq); // 升序
            for(int fre: freq) System.out.println(fre);

            // 求解
            int result = Integer.MAX_VALUE;
            for(int i=0; i<26; i++){ // 以频率为freq[i]的字母为基准
                if(freq[i]==0) continue;
                int curCount = 0; // 当前需要删除的次数
                for(int j=0; j<i; j++){ // 频率更小的字母全部删除
                    curCount += freq[j];
                }
                for(int m=i+1; m<26; m++){
                    if(freq[m] - freq[i] > k) curCount += (freq[m] - (freq[i]+k));
                }
                result = Math.min(result, curCount);
            }

            return result;
        }
    }
}
