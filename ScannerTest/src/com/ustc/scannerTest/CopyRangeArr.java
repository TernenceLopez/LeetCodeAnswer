package com.ustc.scannerTest;

public class CopyRangeArr {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        int[] range = {3,6};
        int[] subArr = copyArr(arr, range);
        // 打印
        for (int i = 0; i < subArr.length; i++) {
            System.out.print(subArr[i]+" ");
        }
//        System.out.println(subArr);
    }

    public static int[] copyArr(int[] arr, int[] range){
        int[] subArr = new int[range[1]-range[0]+1];

        for (int i = 0; i <= range[1]-range[0]; i++) {
            subArr[i] = arr[range[0]+i];
        }
        return subArr;
    }
}
