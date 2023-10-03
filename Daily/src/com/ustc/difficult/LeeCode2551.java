package com.ustc.difficult;

public class LeeCode2551 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,4,7,8};
        int target = 5;

        System.out.println(binarySearch(arr, target));
    }

    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int result = arr.length-1;
        int right = arr.length -1;
        while(left<=right){
            int mid = (left+right)>>1;
            if(arr[mid]==target){
                return mid;
//                return mid;
            } else if (arr[mid]>target) {
                result = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return result;

    }
}
