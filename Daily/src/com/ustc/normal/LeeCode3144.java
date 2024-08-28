package com.ustc.normal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeeCode3144 {
    public static void main(String[] args) {
        // DFS解法 + 记忆化搜索
        System.out.println(minimumSubstringsInPartition("bcc"));

        // 动态规划解法
        System.out.println(minimumSubstringsInPartitionDP("bcc"));
    }

    /**
     * 动态规划解法
     * */
    public static int minimumSubstringsInPartitionDP(String s) {
        // dp[i]: [0,i]区间内最少平衡字符串数目
        int[] dp = new int[s.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 1;
        for(int i=1; i<s.length(); i++){
            HashMap<Character, Integer> map = new HashMap<>(); // 统计[i,j]区间内每个字符出现的次数
            int maxOccur = 0; // 记录[i,j]区间内出现次数最多的字符
            for(int j=i; j>=0; j--){
                map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0)+1);
                maxOccur = Math.max(maxOccur, map.get(s.charAt(j)));
                // map.size()为区间[i,j]之间的字符种类数，maxOccur * map.size()等于区间长度(i-j+1), 说明s[i,j]平衡
                if(maxOccur * map.size() == (i-j+1)){
                    // dp[j-1]!=Integer.MAX_VALUE 说明 s[0,j-1]也可以分割成多个平衡字符串
                    if(j>=1 && dp[j-1]!=Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[j-1]+1);
                    }else{
                        // [0, i]区间本身就是平衡的
                        dp[i] = 1;
                    }
                }
            }
        }
        return dp[s.length()-1];
    }

    /**
     * 暴力搜索 + 记忆化搜索
     * */
    public static int minSplitNum = Integer.MAX_VALUE;

    public static int minimumSubstringsInPartition(String s) {
        // 前缀和，计算索引[0,i]区间每个元素出现的次数
        int[][] prefix = new int[26][s.length()];
        char[] chars = s.toCharArray();
        prefix[chars[0]-'a'][0] = 1;
        for(int i=1; i<chars.length; i++){
            for(int j=0; j<26; j++) {
                if(j==(chars[i]-'a')) prefix[j][i] = prefix[j][i-1]+1;
                else prefix[j][i] = prefix[j][i-1];
            }
        }

        Map<String, Boolean> isBalance = new HashMap<>();
        backtracking(prefix, -1, 0, isBalance);
        return minSplitNum;
    }

    public static void backtracking(int[][] prefix, int lastIndex, int splitNum, Map<String, Boolean> isBalance){
        if(lastIndex==prefix[0].length-1){
            minSplitNum = Math.min(minSplitNum, splitNum);
            return;
        }

        for(int nextIndex = lastIndex+1; nextIndex < prefix[0].length; nextIndex++){
            if(isBalance.containsKey(lastIndex+"-"+nextIndex)){
                if(!isBalance.get(lastIndex+"-"+nextIndex)) {
                    continue;
                }
                else{
                    backtracking(prefix, nextIndex, splitNum+1, isBalance);
                }
                continue;
            }

            // 检查(lastIndex,nextIndex]之间是否平衡
            int count = 0;
            int balanceFlag = 0;
            for(int i=0; i<26; i++){
                int temp = 0;
                if(lastIndex == -1) temp = prefix[i][nextIndex];
                else temp = prefix[i][nextIndex] - prefix[i][lastIndex];

                if(temp==0) continue;

                if(count==0) {
                    count = temp;
                }else if(count != temp){
                    balanceFlag = 1;
                    break;
                }else continue;
            }
            // 不平衡则寻找下一个nextIndex
            if(balanceFlag!=0 && nextIndex-lastIndex>1) {
                isBalance.put(lastIndex+"-"+nextIndex, false);
                continue;
            }
            // 平衡
            isBalance.put(lastIndex+"-"+nextIndex, true);
            backtracking(prefix, nextIndex, splitNum+1, isBalance);
        }
    }
}
