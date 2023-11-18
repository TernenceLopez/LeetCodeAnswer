package com.ustc.normal;

import java.util.*;

public class LeeCode1334 {
    public static void main(String[] args) {
        int[][] edges = new int[][]
                {{0,1,2},{0,4,8},{1,2,10000},{1,4,2},{2,3,10000},{3,4,1}};
        System.out.println(findTheCity(5, edges, 10000));
    }
    // 返回在路径距离小于distanceThreshold的条件下，能到达城市最少的城市

    static List<Map<Integer,Integer>> pathSum = new ArrayList<>();
    static List<Set<Integer>> result = new ArrayList<>(); // 记录每个城市在distanceThreshold限制下能到达的城市

    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // 统计每个节点直接连接的城市和距离
        for(int i=0; i<n; i++){
            pathSum.add(new HashMap<>());
            result.add(new HashSet<>());
        }
//        int sumWeight = 0;
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            pathSum.get(from).put(to, weight); // 双向连接，所以两边都要添加
            pathSum.get(to).put(from, weight);
//            sumWeight = sumWeight + weight;
        }
//        distanceThreshold = Math.min(distanceThreshold, sumWeight); // 缩小distanceThreshold，避免迭代过深

        // 回溯
        for(int i=0; i<n; i++){
            backtracking(i,i,0,distanceThreshold,n,0);
            result.get(i).remove(i);
        }

        // 处理结果
        int minSize = Integer.MAX_VALUE;
        for(Set<Integer> city: result) // 得到最小值
            if(city.size()<minSize) minSize = city.size();
        int resCity = 0;
        for(int i=0; i<result.size(); i++)
            if(result.get(i).size()==minSize) resCity = i;

        return resCity;
    }

    // startCity：起始城市，curCity：当前城市，sum：startCity到curCity的距离
    // deep记录迭代深度
    public static void backtracking(int startCity, int curCity, int sum, int distanceThreshold, int n, int deep){
        if(sum>distanceThreshold || deep > n)
            return;

        for (Map.Entry<Integer, Integer> entry : pathSum.get(curCity).entrySet()) {
            Integer toCity = entry.getKey();
            Integer distance = entry.getValue();

            boolean flag = (result.get(startCity).size()==n) ||
                    (result.get(startCity).size()==n-1 && !result.get(startCity).contains(startCity));
            if(sum + distance <= distanceThreshold && !flag){
                result.get(startCity).add(toCity); // startCity能够到达toCity

                backtracking(startCity, toCity, sum+distance, distanceThreshold,n, deep+1);
            }
        }
    }

}
