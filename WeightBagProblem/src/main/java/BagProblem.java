public class BagProblem {
    public static void main(String[] args) {
        int[] weight = {1,3,4};
        int[] value = {15,20,30};
        int bagSize = 4;

        int result = testWeightBagProblem(weight,value,bagSize);
        System.out.println(result);
    }

    public static int testWeightBagProblem(int[] weight, int[] value, int bagSize){
        // 定义dp数组
        // dp[i][j]含义：背包容量为j时，在[0~i]个物品中挑选，能够容纳的最大值
        // j取值范围: 0,1,2,...,bagSize
        int[][] dp = new int[weight.length][bagSize+1];

        // 初始化
        for(int j=0; j<=bagSize; j++){
            if(weight[0]<=j) dp[0][j] = value[0]; // 最大值刚好就是第0个物品的价值
        }

        // 状态转移
        for(int j=0; j<=bagSize; j++){
            for(int i=1; i<weight.length; i++){
                // 如果 j 啥也不装都装不下 i 物品, 那么就继承dp[i - 1][j]的值
                if(weight[i]>j) dp[i][j] = dp[i-1][j];
                else{
                    // 如果 j 啥也不装能装下 i 物品，但是 j 装下 i 物品之后会超出剩余的容量
                    // 情况一：不装 i 物品，只装0～i-1之间的物品：dp[i-1][j]
                    // 情况二：装 i 物品，少装一点0～i-1之间的物品: value[i]+dp[i-1][j-weight[i]]
                    dp[i][j] = Math.max(dp[i-1][j],value[i]+dp[i-1][j-weight[i]]);
                }
            }
        }

        return dp[weight.length-1][bagSize];
    }
}
