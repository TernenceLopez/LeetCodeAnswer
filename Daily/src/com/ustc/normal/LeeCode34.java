package com.ustc.normal;

import java.util.Arrays;

public class LeeCode34 {
    public static void main(String[] args) {
        searchRange(new int[]{1}, 0);
    }

    public static int[] searchRange(int[] nums, int target) {
        int left = -1, right = nums.length;
        int[] result = new int[2]; Arrays.fill(result, -1);
        if(nums.length==0) return result;
        // 寻找左边界
        while(left<=right && right>=0 && left<=nums.length-1){
            int mid = (left+right)/2;
            if(nums[mid] >= target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        // left左边一定是小于target的，right右边一定是大于等于target的
        if(right==nums.length || nums[right+1]!=target) return result;
        else result[0] = right+1;

        // 寻找右边界
        left = -1;
        right = nums.length;
        while(left<=right && right>=0 && left<=nums.length-1){
            int mid = (left+right)/2;
            if(nums[mid] <= target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        // left左边一定是小于等于target的，right右边一定是大于target的
        result[1] = left-1;

        return result;
    }
}
