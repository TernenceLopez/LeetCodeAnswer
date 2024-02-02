package com.ustc.normal;

import java.util.HashMap;
import java.util.Map;

public class LeeCode447 {
    public static void main(String[] args) {
        System.out.println(numberOfBoomerangs(new int[][]{{0,0},{1,0},{2,0}}));
    }
    public static int numberOfBoomerangs(int[][] points) {
        int count = 0;

        // 记录每个起点 i 到其他点的距离和出现次数
        Map<Integer, Integer>[] map = new HashMap[points.length];
        for(int i=0; i<points.length; i++){
            map[i] = new HashMap<>();
            for(int j=0; j<points.length; j++){
                int distance = countDistance(points[i], points[j]);
                map[i].put(distance, map[i].getOrDefault(distance, 0)+1); // 出现次数加一
            }
            // 计算起点 i 对应的回旋镖数目
            for(Map.Entry<Integer, Integer> entry: map[i].entrySet()){
                int occurTimes = entry.getValue();
                System.out.println(""+entry.getKey()+"--"+entry.getValue());
                if(occurTimes>1){
                    count += occurTimes*(occurTimes-1);
                }
            }
        }

        return count;
    }

    public static int countDistance(int[] x, int[] y){
        int delta_x = Math.abs(x[0]-y[0]);
        int delta_y = Math.abs(x[1]-y[1]);

        return delta_x*delta_x + delta_y*delta_y;
    }
}
