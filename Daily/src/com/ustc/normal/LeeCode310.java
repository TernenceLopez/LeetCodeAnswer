package com.ustc.normal;

import java.util.ArrayList;
import java.util.List;

public class LeeCode310 {
    static int longestNode = -1, deepth = -1;

    public static void main(String[] args) {
        findMinHeightTrees(8, new int[][]{
                {0,1},{1,2},{2,3},{0,4},{4,5},{4,6},{6,7}
        });
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if(n == 1){
            result.add(0); // 只有一个节点，0号节点就是最小高度树的根节点
            return result;
        }

        // 求解无向树中的最长路径
        List<Integer>[] connect = new ArrayList[n];
        for(int i=0; i<n; i++) connect[i] = new ArrayList<Integer>();
        for(int[] edge: edges){
            connect[edge[0]].add(edge[1]);
            connect[edge[1]].add(edge[0]);
        }

        // 以 0 为起点找到最远节点 x
        findLongestNode(0, -1, connect, 0);
        int x = longestNode;
        longestNode = -1; deepth = -1;
        // 以 x 为起点找到最远节点 y
        findLongestNode(x, -1, connect, 0);
        int y = longestNode;
        // 记录 x 到 y 的沿途节点
        List<Integer> path = new ArrayList<>();
        dfs(x, -1, connect, y, path);
        // 寻找最小高度树的节点
        int m = path.size();
        System.out.println(x);
        System.out.println(y);
        System.out.println(path);
        if(m%2==0){
            result.add(path.get(m/2-1)); result.add(path.get(m/2));
        }else{
            result.add(path.get(m/2));
        }

        return result;
    }

    // 返回当前节点是否能够到达 y 节点
    public static boolean dfs(int cur, int parent, List<Integer>[] connect, int destination, List<Integer> path){
        if(cur==destination){
            path.add(cur);
            return true;
        }
        for(int child: connect[cur]){
            if(child==parent) continue;
            if(dfs(child, cur, connect, destination, path)){ // 能够到达 destination
                path.add(cur);
                return true;
            }
        }
        return false;
    }

    public static void findLongestNode(int cur, int parent, List<Integer>[] connect, int deep){
        if(deep > deepth){
            longestNode = cur;
            deepth = deep;
        }
        for(int child : connect[cur]){
            if(child == parent) continue;
            findLongestNode(child, cur, connect, deep+1);
        }
    }
}
