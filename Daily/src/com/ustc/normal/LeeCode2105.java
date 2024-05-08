package com.ustc.normal;

public class LeeCode2105 {
    public static void main(String[] args) {
        minimumRefill(new int[]{2,2,3,3}, 3, 4);
    }
    public static int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int curVolumeA = capacityA, curVolumeB = capacityB;
        int result = 0;
        int IndexA = 0, IndexB = plants.length-1;
        while(IndexA <= IndexB){
            if(IndexA==IndexB){
                if(Math.max(curVolumeA, curVolumeB) < plants[IndexA]) result++;
                break;
            }

            if(capacityA >= plants[IndexA]){ // Alice 水桶能够灌溉 plants[IndexA]
                if(curVolumeA >= plants[IndexA]){
                    curVolumeA -= plants[IndexA];
                    IndexA++;
                }else{
                    result++;
                    curVolumeA = capacityA - plants[IndexA]; // 由于是立刻装满水，所以装满水之后直接灌溉
                    IndexA++;
                }
            }
            if(capacityB >= plants[IndexB]){ // Bob 水桶能灌溉 plants[IndexB]
                if(curVolumeB >= plants[IndexB]){
                    curVolumeB -= plants[IndexB];
                    IndexB--;
                }else{
                    result++;
                    curVolumeB = capacityB - plants[IndexB];
                    IndexB--;
                }
            }
        }

        return result;
    }
}
