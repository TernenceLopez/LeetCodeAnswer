package YouTa_7_18;

import java.util.Arrays;

/**
 * 友塔游戏笔试提前批第三题
 * */
public class Solution3 {
    public static void main(String[] args) {
        int target = 31;
        int[] minus = new int[]{30, 15, 7, 5, 2, 1};
        int[] cost = new int[]{25, 22, 10, 6, 4, 1};

//        System.out.println(findmincost(target, minus, cost));
        System.out.println(dpSolution(target, minus, cost));
    }

    // 暴力回溯方法
    static int minCost = Integer.MAX_VALUE;
    public static int findmincost(int target, int[] minus, int[] cost){
        backtracking(0, target, minus, cost, 0);
        return minCost;
    }
    public static void backtracking(int cur, int target, int[] minus, int[] cost, int curCost){
        if(cur>=target){
            minCost = Math.min(minCost, curCost);
            return;
        }
        for(int i=0; i<minus.length; i++){
            backtracking(cur+minus[i], target, minus, cost, curCost+cost[i]);
        }
    }

    // 使用背包问题解决
    public static int dpSolution(int target, int[] minus, int[] cost){
        int[] dp = new int[target+1]; // dp[i] 表示消除i秒cd需要消耗的最少钻石数
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1; i<=target; i++){
            for(int j = 0; j < minus.length; j++){
                if(i>=minus[j])
                    dp[i] = Math.min(dp[i], dp[i-minus[j]]+cost[j]);
                else
                    dp[i] = Math.min(dp[i], cost[j]); // minus[j]大于总cd时，可以消耗cost[j]直接消除cd
            }
        }
        return dp[target];
    }
}
