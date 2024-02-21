package com.ustc.normal;

public class LeeCode215 {
    public static void main(String[] args) {
        findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4);
    }
    // 左闭右闭区间
    public static void quickSort(int[] arr, int startIndex, int endIndex, int k){
        if(startIndex > endIndex) return;

        int baseLine = arr[startIndex]; // 基准元素
        int left = startIndex; // left不能直接赋值为startIndex+1，因为要保证[startIndex, left]全是小于等于baseline的元素，此时还不确定startIndex+1是否小于等于baseline；
        int right = endIndex;  // 后续保证[right, endIndex]全是大于baseline的元素，但此时不能确定right指向的元素一定大于baseline
        while(left <= right){
            while((left <= right) && arr[left] <= baseLine) left++;  // left指向大于baseline的元素
            while((left <= right) && arr[right] > baseLine) right--; // right指向小于等于baseline的元素

            // 交换left、right指向的元素
            if(left<right){
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
            }
        }
        // 结束循环后，一定满足下面两点：
        // 1. left一定等于right+1
        // 2. 由于最先确定left的位置，所以left-1指向的元素一定是小于等于baseline的元素
        //    left指向的一定是大于baseline的元素(当[startIndex,endIndex]区间存在大于baseline的元素) or left指向是endIndex+1指向的元素(当[startIndex,endIndex]区间不存在大于baseline的元素)
        // 3. 当[startIndex,endIndex]区间存在大于baseline的元素：left指向的是大于baseline的元素，right指向的是小于等于baseline的元素。
        //    所以[startIndex, left-1]一定全部小于等于baseline，[left,endIndex]一定全部大于baseline
        // 4. 当[startIndex,endIndex]区间不存在大于baseline的元素：left指向的是endIndex+1指向的元素，right指向的是小于等于baseline的元素。
        //    所以[startIndex, left-1]一定全部小于等于baseline，[left,endIndex]是一个无效区间
        // 5. 结合第四点，就可以得到基准元素在原始数组arr中的位置为right = left-1
        // 交换基准元素与arr[right]
        arr[startIndex] = arr[right];
        arr[right] = baseLine;

        //根据基准元素分两部分进行递归排序(right索引位置处的元素已经确定了)
        if(k<=right){
            quickSort(arr, startIndex, right-1, k);
        }else{ // k索引位置的元素在right索引的左边，所以right右半区间元素不需要排序了
            quickSort(arr, right+1, endIndex, k);
        }
    }

    public static int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        quickSort(_nums, 0, n - 1, n-k); // 返回第n-k小的元素

        return _nums[n-k];
    }
}
