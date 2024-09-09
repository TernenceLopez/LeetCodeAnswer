package TencentMusic;

import sun.security.rsa.RSASignature;

import java.security.PublicKey;
import java.util.*;

public class Exam003 {
    /**
     * 给你一个n×n的方阵。第i行和第j列的元素是r，e，d中的一个，定义一个矩阵权值为这个矩阵中出现r，e，d数量最小的值现在有一个是myval。
     * 现在想请你计算出有多少个子方阵的权值不小于myval。
     * 其中行数与列数相等的矩阵叫方阵。
     * 比如输入[“red”，“red”，“red”]，myval=2，输出为1
     * */
    public static void main(String[] args) {
        /**
         * 后续可以改进添加记忆化搜索，包含权值大于val的方阵时，该方阵的权值也一定大于val
         * */
        System.out.println(matrixCount(new String[]{"red","red","red"}, 2));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 返回一个整数，表示答案
     * @param a int整型ArrayList<ArrayList<>>
     * @param myval int整型
     * @return int整型
     */
    public static int matrixCount (String[] a, int myval) {
        int n = a.length;
        // 二维数组前缀和，每一行都对应一个前缀和，大小为(n+1)*(n+1)用来简化边界条件
        int[][] rPrefix = new int[n+1][n+1];
        int[][] ePrefix = new int[n+1][n+1];
        int[][] dPrefix = new int[n+1][n+1];

        // 计算r, e, d前缀和
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                char c = a[i-1].charAt(j-1);
                rPrefix[i][j] = rPrefix[i-1][j] + rPrefix[i][j-1] - rPrefix[i-1][j-1] + (c=='r'?1:0);
                ePrefix[i][j] = ePrefix[i-1][j] + ePrefix[i][j-1] - ePrefix[i-1][j-1] + (c=='e'?1:0);
                dPrefix[i][j] = dPrefix[i-1][j] + dPrefix[i][j-1] - dPrefix[i-1][j-1] + (c=='d'?1:0);
            }
        }

        // 遍历所有方阵
        int result = 0;
        // 记忆化搜索，包含权值大于myval的方阵时，不搜索
        // {size, i, j}
        List<int[]> memo = new ArrayList<>();
        for(int size = 1; size<=n; size++){
            for(int i=0; i<=n-size; i++){
                for(int j=0; j<=n-size; j++){
                    if(getFit(size, i, j, memo)){
                        result++;
                    }
                    // 统计方阵内的r, e, d
                    int rCount = getSum(rPrefix, i, j, i+size-1, j+size-1);
                    int eCount = getSum(ePrefix, i, j, i+size-1, j+size-1);
                    int dCount = getSum(dPrefix, i, j, i+size-1, j+size-1);

                    int minCount = Math.min(rCount, Math.min(eCount, dCount));
                    if(minCount>=myval){
                        memo.add(new int[]{size, i, j});
                        result++;
                    }
                }
            }
        }
        return result;
    }

    // 计算前缀和矩阵中(i1,j1)到(i2,j2)的子矩阵的和
    public static int getSum(int[][] prefix, int i1, int j1, int i2, int j2){
        return prefix[i2+1][j2+1] - prefix[i2+1][j1] - prefix[i1][j2+1] + prefix[i1][j1];
    }

    // 判断当前方阵是否包含memo中的有效方阵
    public static boolean getFit(int size, int i, int j, List<int[]> memo){
        for(int[] fitMatrix: memo){
            int fit_i = fitMatrix[1], fit_j = fitMatrix[2];
            int diffSize = size - fitMatrix[0];
            if(fit_i-i<=diffSize && fit_j-j<=diffSize){
                return true;
            }
        }
        return false;
    }
}
