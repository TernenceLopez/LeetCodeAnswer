package com.ustc.normal;

import java.util.*;

public class LeeCode522 {
    public static void main(String[] args) {
//        System.out.println(ifAContainsB("aabbcc","cb"));
        System.out.println(ifAContainsB_v2("aabbcc","cb"));
        findLUSlength(new String[]{"aabbcc", "aabbcc","cb"});
    }

    public static int findLUSlength(String[] strs) {
        Arrays.sort(strs, (a, b)->{return b.length()-a.length();});
        int result = -1;

        for(int i=0; i<strs.length; i++){
            for(int j=0; j<strs.length; j++){
                if(i!=j && strs[i].equals(strs[j])) break;
                else if(strs[i].length() < strs[j].length()
                        && ifAContainsB(strs[j], strs[i])){
                    break;
                }
                if(j==strs.length-1)
                    result = Math.max(result, strs[i].length());
            }
        }
        return result;
    }

    // 检查a字符串是否包含b字符串
    public static boolean ifAContainsB_v2(String a, String b){
        int pos_b = 0;
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i)==b.charAt(pos_b)) pos_b++;
            if(pos_b==b.length()-1) return true;
        }
        return false;
    }

    // 检查a字符串是否包含b字符串
    public static boolean ifAContainsB(String a, String b){
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // 计算a中每个字母的索引位置
        for(int i=0; i<a.length(); i++){
            int char_a = a.charAt(i) - 'a';
            if(map.containsKey(char_a)) map.get(char_a).add(i);
            else{
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i); map.put(char_a, indexList);
            }
        }
        // 计算a是否包含b
        int lastCharMinIndex = -1;
        for(int i=0; i<b.length(); i++){
            int char_b = b.charAt(i) - 'a';
            if(!map.containsKey(char_b)) return false;
            // 判断当前字符首次出现的位置是否大于上一个字符首次出现的位置
            for(int index=0; index<map.get(char_b).size(); index++){
                if(map.get(char_b).get(index) > lastCharMinIndex){
                    lastCharMinIndex = map.get(char_b).get(index);
                    break;
                }
                if(index==map.get(char_b).size()-1) return false; // 找不到匹配字符
            }
        }
        return true;
    }
}
