package com.ustc.normal;

public class LeeCode80 {
    public static void main(String[] args) {
        removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3});
    }
    public static int removeDuplicates(int[] nums) {
        int slow = 0, fast = 1;
        int n = nums.length;
        boolean dup = false;

        for(; fast<n;){
            if(nums[fast]!=nums[slow] && dup == false){
                slow++;
                nums[slow] = nums[fast];
                fast++;
            }else if(nums[fast]!=nums[slow] && dup == true){
                slow++;
                nums[slow] = nums[fast];
                fast++;
                dup = false;
            }else if(nums[fast]==nums[slow] && dup == false){
                slow++;
                nums[slow] = nums[fast];
                fast++;
                dup = true;
            }else{
                fast++;
            }
        }
        return slow;
    }
}
