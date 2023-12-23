package com.ustc.normal;

import java.util.Arrays;

public class LeeCode100138 {
    public static void main(String[] args) {
        int n=2;
        int m=3;
        int[] hBars = new int[]{3,2};
        int[] vBars = new int[]{4,2};

        System.out.println(maximizeSquareHoleArea(n,m,hBars,vBars));
    }
    public static int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int Square1 = 0;
        int Square2 = 0;
        int Square3 = 0;
        int Square4 = 0;

        Arrays.sort(hBars);
        Arrays.sort(vBars);

        int[] nhBars = new int[hBars.length+1];
        int[] nvBars = new int[vBars.length+1];
        System.arraycopy(hBars, 0, nhBars, 0, hBars.length);
        System.arraycopy(vBars, 0, nvBars, 0, vBars.length);
        nhBars[nhBars.length-1] = n+2;
        nvBars[nvBars.length-1] = m+2;

        boolean findFlag = false;
        for(int i=nhBars.length-1; i>=0; i--){
            // for(int j=vBars.length-1; j>=0 && hBars[i]<=vBars[j]; j--){
            for(int j=nvBars.length-1; j>=0; j--){
                if(nhBars[i]==nvBars[j]){
                    Square1 = (nhBars[i]-1)*(nvBars[j]-1);
                    findFlag = true;
                    break;
                }
            }
            if(findFlag) break;
        }

        findFlag = false;
        for(int i=nhBars.length-1; i>=0; i--){
            for(int j=0; j<nvBars.length; j++){
                if((nhBars[i]-1)==(m+2 - nvBars[j])){
                    Square2 = (nhBars[i]-1)*(m+2 - nvBars[j]);
                    findFlag = true;
                    break;
                }
            }
            if(findFlag) break;
        }

        findFlag = false;
        for(int i=0; i<nhBars.length; i++){
            for(int j=0; j<nvBars.length; j++){
                if((n+2 - nhBars[i])==(m+2 - nvBars[j])){
                    Square3 = (n+2 - nhBars[i])*(m+2 - nvBars[j]);
                    findFlag = true;
                    break;
                }
            }
            if(findFlag) break;
        }

        findFlag = false;
        for(int i=0; i<nhBars.length; i++){
            for(int j=nvBars.length-1; j>=0; j--){
                if((n+2 - nhBars[i])==(nvBars[j]-1)){
                    Square4 = (n+2 - nhBars[i])*(nvBars[j]-1);
                    findFlag = true;
                    break;
                }
            }
            if(findFlag) break;
        }

        return Math.max(Math.max(Square1,Square2), Math.max(Square3,Square4));
    }
}
