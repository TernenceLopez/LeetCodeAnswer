package com.ustc.normal;

import java.util.PriorityQueue;
import java.util.TreeSet;

public class LeeCode2975 {
    public static void main(String[] args) {
        int m=3;
        int n=9;
        int[] hFences = new int[]{2};
        int[] vFences = new int[]{8,6,5,4};

        int maxArea = maximizeSquareArea(m,n,hFences,vFences);
        System.out.println(maxArea);
    }
    public static final int MOD_VALUE = 1000000007;
    public static int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // 记录所有可能出现的行列长度
        TreeSet<Integer> row = new TreeSet<>((a, b)->b-a); // 获取元素顺序为由大到小
        TreeSet<Integer> col = new TreeSet<>((a,b)->b-a);
        row.add(m-1);
        col.add(n-1);

        // 获取所有可能出现的行
        for(int i=0; i<hFences.length; i++){
            row.add(hFences[i]-1);
            row.add(m-hFences[i]);
            for(int j=0; j<i; j++){
                row.add(hFences[i] - hFences[j]);
            }
        }

        // 获取所有可能出现的列
        for(int i=0; i<vFences.length; i++){
            col.add(vFences[i]-1);
            col.add(n-vFences[i]);
            for(int j=0; j<i; j++){
                col.add(vFences[i] - vFences[j]);
            }
        }

        // 计算最大面积
        PriorityQueue<Integer> rowPq = new PriorityQueue<>((a, b)->b-a);
        PriorityQueue<Integer> colPq = new PriorityQueue<>((a,b)->b-a);
        row.forEach(s->rowPq.add(s));
        col.forEach(s->colPq.add(s));

        // while(!rowPq.isEmpty()){System.out.println(rowPq.poll());}


        while(!rowPq.isEmpty()){
            Integer height = rowPq.peek(); // 获取当前行的高度
            // System.out.println(height);
            while(!colPq.isEmpty()){
                Integer width = colPq.peek(); // 获取当前列的宽度
                if(width>height){
                    colPq.poll();
                    continue; // 继续缩小width
                }else if(width<height){
                    rowPq.poll();
                    break; // 继续缩小height
                }else{
                    return width*height; // width==height
                }
            }
        }

        return -1;
    }
}
