package com.ustc.normal;

import java.util.*;

public class LeeCode100251 {
    public static void main(String[] args) {
        String[] arr = shortestSubstrings(new String[]{"gfnt","xn","mdz","yfmr","fi","wwncn","hkdy"});
        for(String item: arr)
            System.out.println(item);
    }
    public static String[] shortestSubstrings(String[] arr) {
        // 存放{子字符串，包含该子字符串的arr下标}，默认按照键进行升序排序
        TreeMap<String, Set<Integer>> sortedMap = new TreeMap<>();

        for(int i=0; i<arr.length; i++) {
            // 获取arr[i]的所有字符子串
            for (int j = 0; j < arr[i].length(); j++) {
                for (int k = j; k < arr[i].length(); k++) {
                    String subString = arr[i].substring(j, k + 1);
                    if (sortedMap.containsKey(subString)) {
                        sortedMap.get(subString).add(i);
                    } else {
                        Set<Integer> indexSet = new HashSet<>();
                        indexSet.add(i);
                        sortedMap.put(subString, indexSet);
                    }
                }
            }
        }

        // 求解结果
        String[] result = new String[arr.length];
        Arrays.fill(result, "");

        for(Map.Entry<String, Set<Integer>> entry: sortedMap.entrySet()){
            if(entry.getValue().size() > 1) continue; // 重复包含子串
            else{
                for(int index: entry.getValue()){
                    if(result[index].equals("") || result[index].length()>entry.getKey().length()){
                        result[index] = entry.getKey();
                    }
                }
            }
        }
        return result;
    }
}
