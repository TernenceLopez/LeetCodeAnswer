package com.ustc.normal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class LeeCode2707 {
    public static void main(String[] args) {
        int res = minExtraChar("eglglxa", new String[]{"rs","j","h","g","fy","l","fc","s","zf","i","k","x","gl","qr","qj","b","m","cm","pe","y","ei","wg","e","c","ll","u","lb","kc","r","gs","p","ga","pq","o","wq","mp","ms","vp","kg","cu"});
        System.out.println(res);
    }
    public static int minExtraChar(String s, String[] dictionary) {
        HashSet<String> dic = new HashSet<>();
        for(String word: dictionary) dic.add(word);

        // dp[i]: 表示s的[0~i]区间中额外字符个数
        // 把s[i]当作额外字符：dp[i] = dp[i-1]+1;
        // 把s[i]不当作额外字符，那么从s[i]往前遍历，直到s的[j～i]区间在dic中：dp[i] = dp[j-1];
        // 把s[i]不当作额外字符，那么从s[i]往前遍历，如果找不到s的[j～i]区间在dic中，那么只能把s[i]当作额外字符：dp[i] = dp[i-1]+1;
        // 因为要找最小值，所以dp[i] = Math.min(dp[i-1]+1, dp[j-1])
        int[] dp = new int[s.length()];

        // 初始化
        Arrays.fill(dp, Integer.MAX_VALUE);
        if(dic.contains(""+s.charAt(0))) dp[0] = 0;
        else dp[0] = 1;

        // 状态转移
        for(int i=1; i<s.length(); i++){
            // 把s[i]当作额外字符
            dp[i] = dp[i-1] + 1;
            // 把s[i]不当作额外字符
            for(int j=i; j>=0; j--){
                // 找到s的[j～i]区间在dic中，可能有多个s的[j～i]区间在dic中
                if(dic.contains(s.substring(j, i+1))){
                    if(j>=1) dp[i] = Math.min(dp[i], dp[j-1]);
                    else dp[i] = 0; // s的[0~i]都在dic中，dp[i]直接赋值为0
                }
            }
        }
        for(int i=0; i<=s.length()-1;i++){
            System.out.println(dp[i]);
        }

        return dp[s.length()-1];
    }
}
