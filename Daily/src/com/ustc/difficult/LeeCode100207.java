package com.ustc.difficult;

import java.util.ArrayList;
import java.util.List;

public class LeeCode100207 {
    public static void main(String[] args) {
//        beautifulIndices("isawsquirrelnearmysquirrelhouseohmy","my","squirrel",15);
        beautifulIndices("abcd","a","a",4);
    }
    public static List<Integer> beautifulIndices(String s, String a, String b, int k) {
        char[] text = s.toCharArray();
        List<Integer> posA = kmp(text, a.toCharArray());
        List<Integer> posB = kmp(text, b.toCharArray());

        // posA和posB一定都是有序的
        // 找出符合条件的i（二分查找）
        ArrayList<Integer> result = new ArrayList<>();
        for(int i: posA){
            int lowerIndex = lower(posB, i); // 返回小于i且最接近i的下标
            // int lowerIndex = 0;
            if ((lowerIndex < posB.size()-1 && i - posB.get(lowerIndex) <= k) ||
                    (lowerIndex < posB.size()-1 && posB.get(lowerIndex) - i <= k) ||
                    (lowerIndex == posB.size()-1 && i - posB.get(lowerIndex) <= k)
            ) { // 下标i满足条件
                result.add(i);
            }
        }

        return result;
    }

    // pattern: 子字符串；text: 文本字符串
    // 将pattern在text中匹配，返回pattern匹配成功的所有起始位置
    public static List<Integer> kmp(char[] text, char[] pattern){
        int m = pattern.length;
        int[] next = new int[m]; // pattern字符串的前缀表 (next[i]存放[0,i]区间最长相同前后缀的长度)

        // 求pattern字符串的next数组 : 定义两个指针i和j，j指向前缀末尾位置，i指向后缀末尾位置
        int j=0;
        for(int i=1; i<m; i++){
            while(j>0 && pattern[i]!=pattern[j]){ // 直到pattern[i]==pattern[j]或者j=0
                j = next[j-1];
            }

            if(pattern[i]==pattern[j]){ // 排除j=0这种情况
                j++;
            }

            next[i] = j; // 记录[0,i]区间最长相同前后缀的长度
        }

        // 使用next数组，将pattern在text中匹配
        List<Integer> result = new ArrayList<>();
        j = 0;
        for(int i=0; i<text.length; i++){
            while(j>0 && pattern[j]!=text[i]){
                j = next[j-1];
            }
            if(pattern[j]==text[i]){
                j++;
            }
            if(j==pattern.length){ // 匹配成功
                result.add(i-j+1); // 记录匹配成功的起始位置
                j = next[j-1]; // j指向最长公共前缀的下一个字母
            }
        }
        return result;
    }

    // nums单调递增：返回小于target且最接近target的下标
    private static int lower(List<Integer> nums, int target) {
        int left = 0, right = nums.size()-1;
        while(left<=right){
            int mid = (left+right)/2; // 向下取整
            if(nums.get(mid) >= target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }

        return right;
    }
}
