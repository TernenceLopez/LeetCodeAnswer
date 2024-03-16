package com.ustc.normal;

import java.util.Arrays;

public class LeeCode2684 {
    public static void main(String[] args) {
        maxMoves(new int[][]{
                {2,4,3,5},
                {5,4,9,3},
                {3,4,2,11},
                {10,9,13,15}
        });
    }

    public static int maxMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n]; // 从当前起点出发能过到达的最大深度
        for(int[] arr: memo) Arrays.fill(arr, -1);

        // DFS
        int result = 0;
        for(int i=0; i<m; i++){
            result = Math.max(result, dfs(i, 0, grid, memo));
        }

        return result;
    }

    // 从(x,y)节点出发能够到达的最大深度
    public static int dfs(int x, int y, int[][] grid, int[][] memo){
        if(memo[x][y] != -1) return memo[x][y];
        if(y == grid[0].length-1) return y; // 达到右边界

        int result = y;
        if(x>0 && grid[x][y] < grid[x-1][y+1]){
            result = Math.max(result, dfs(x-1, y+1, grid, memo));
        }
        if(x<grid.length-1 && grid[x][y] < grid[x+1][y+1]){
            result = Math.max(result, dfs(x+1, y+1, grid, memo));
        }
        if(grid[x][y] < grid[x][y+1]){
            result = Math.max(result, dfs(x, y+1, grid, memo));
        }
        memo[x][y] = result;
        return result;
    }
}
