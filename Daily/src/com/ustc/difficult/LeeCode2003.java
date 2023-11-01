package com.ustc.difficult;

import java.util.*;

public class LeeCode2003 {
    public static void main(String[] args) {
        int[] parents = new int[]{-1,0,1,0,3,3};
        int[] nums = new int[]{5,4,6,2,1,3};

        int[] result = smallestMissingValueSubtree(parents, nums);
        for(int i: result){
            System.out.println(i);
        }
    }

    public static int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        // 将parents数组转化为可用的数据结构
        int n = parents.length;
        List<Integer>[] children = new List[n]; // List数组
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<Integer>();
        }
        for (int i = 1; i < n; i++) {
            children[parents[i]].add(i); // children记录每个节点的所有子节点
        }

        // res记录返回结果，默认缺失最小值1
        int[] res = new int[n];
        Arrays.fill(res, 1);

        // geneSet记录每个节点对应的基因集合
        Set<Integer>[] geneSet = new Set[n];
        for (int i = 0; i < n; i++) {
            geneSet[i] = new HashSet<Integer>();
        }

        // 深度优先搜索（首先遍历整个树的根节点，index=0）
        dfs(0, res, nums, children, geneSet);
        return res;
    }

    public static int dfs(int node, int[] res, int[] nums, List<Integer>[] children, Set<Integer>[] geneSet) {
        // 节点node的基因集合加上node自己的值
        geneSet[node].add(nums[node]);

        // 遍历节点node的所有子节点child
        for (int child : children[node]) {
            // dfs返回node子节点child缺失的最小值，res[node]表示当前缺失的最小值。取两者的最大值
            res[node] = Math.max(res[node], dfs(child, res, nums, children, geneSet));

            // 将小集合合并到大集合，避免超时（加不加if这一段效果一样，但是执行效率会降低）
            if (geneSet[node].size() < geneSet[child].size()) {
                Set<Integer> temp = geneSet[node];
                geneSet[node] = geneSet[child];
                geneSet[child] = temp;
            }

            // node节点的基因集合加上子节点的基因集合
            geneSet[node].addAll(geneSet[child]);
        }

        // 终止条件
        while (geneSet[node].contains(res[node])) {
            res[node]++;
        }

        // 返回node节点缺失的最小值
        return res[node];
    }
}
