package com.ustc.normal;

public class LeeCode75 {
    public static void main(String[] args) {
        sortColors(new int[]{2,0,2,1,1,0});
    }

    public static void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length-1;

        for(int index = left; index<=right; ){
            // 保证index左边没有2
            while(index<=right && nums[index]==2){
                swap(nums, index, right);
                right--;
            }
            // 保证当前遇到的0全部移到最左端
            if(nums[index]==0){
                swap(nums, index, left);
                left++;
            }
            index++;
        }
    }

    public static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
