package com.ustc.normal;

import java.util.*;

public class LeeCode1738 {
    public static void main(String[] args) {
        kthLargestValue(new int[][]{{10,9,5},{2,0,4},{1,0,9},{3,4,8}},10);
    }
    public static int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] prefixXOR = new int[m][n];
        Integer[] record = new Integer[m*n]; // 记录每一个点的异或值
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i!=0 && j!=0){
                    prefixXOR[i][j] = prefixXOR[i-1][j]
                            ^ prefixXOR[i][j-1]
                            ^ prefixXOR[i-1][j-1]
                            ^ matrix[i][j];
                }else if(i!=0){ // j=0
                    prefixXOR[i][j] = prefixXOR[i-1][j] ^ matrix[i][j];
                }else if(j!=0){ // i=0
                    prefixXOR[i][j] = prefixXOR[i][j-1] ^ matrix[i][j];
                }else{ // i=0 && j=0
                    prefixXOR[i][j] = matrix[i][j];
                }

                record[i*n+j] = prefixXOR[i][j];
            }
        }
        Arrays.sort(record, (a,b)->{
            return b-a;
        });
        return record[k-1];
    }
}
