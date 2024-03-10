package com.ustc.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class LeeCode2581 {
    public static void main(String[] args) {
        rootCount(new int[][]{{0,1},{1,2},{1,3},{4,2}},
                new int[][]{{1,3},{0,1},{1,0},{2,4}},
                3);
    }
    public static int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length+1; // 节点数为n，边的数目为n-1
        // 存放所有guess的哈希值
        HashSet<Long> guessHash = new HashSet<>();
        for(int[] guess: guesses){
            guessHash.add((long)guess[0]<<32 | guess[1]); // guess转化为hash值
        }

        // 将edges转化为无向图结构
        ArrayList<Integer>[] tree = new ArrayList[n]; // 存放n个节点连接的节点
        Arrays.setAll(tree, i -> new ArrayList<>());
        for(int[] edge: edges){
            int father = edge[0], child = edge[1];
            tree[father].add(child);
            tree[child].add(father);
        }
        // 假设0号节点为根节点，计算guess中猜对的个数
        int count = dfs(-1, 0, tree, guessHash, 0);

        // 换根
        int countCase = changeRoot(-1, 0, 0, 0, k, tree, guessHash);

        // System.out.println(count);

        return countCase;
    }

    public static int dfs(int father ,int cur, ArrayList<Integer>[] tree, HashSet<Long> guessHash, int count){
        for(int child: tree[cur]){
            if(child != father){
                if(guessHash.contains((long)cur<<32 | child)) count++; // 猜对了
                count = dfs(cur, child, tree, guessHash, count);
            }
        }
        return count;
    }

    // countCase记录可能的根节点数目
    public static int changeRoot(int father, int cur, int countCase, int count, int k, ArrayList<Integer>[] tree, HashSet<Long> guessHash){
        // 判断当前猜对个数count是否大于k，如果大于k，则countCase+1
        if(count>=k) countCase++;

        for(int child: tree[cur]){
            if(child != father){
                int tempCount = count;
                if(guessHash.contains((long)cur<<32 | child)) tempCount--; // cur->child，颠倒后，猜对次数减1
                if(guessHash.contains((long)child<<32 | cur)) tempCount++; // child->cur，颠倒后，猜对次数加1
                countCase = changeRoot(cur, child, countCase, tempCount, k, tree, guessHash);
            }
        }
        return countCase;
    }
}
