package com.ustc.normal;
import java.util.*;

public class LeeCode826 {
    public static void main(String[] args) {
        System.out.println(maxProfitAssignment(new int[]{2,4,6,8,10},
                new int[]{10,20,30,40,50},
                new int[]{4,5,6,7}));
    }

    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        // 求解小于等于difficulty的所有任务能够取得的最大profit
        ArrayList<int[]> zip = new ArrayList<>();
        int n = difficulty.length;
        for(int i=0; i<n; i++){
            zip.add(new int[]{difficulty[i], profit[i]});
        }
        Collections.sort(zip, (a,b)->{
            if(a[0]!=b[0]){
                return a[0] - b[0]; // difficulty按照升序排列
            }else{
                return b[1] - a[1]; // profit按照降序排列
            }
        });
        int curMaxProfit = -1;
        for(int[] element: zip){
            element[1] = Math.max(element[1], curMaxProfit);
            curMaxProfit = element[1];
        }

        // 计算每个工人能够实现的最大利润
        // Arrays.sort(worker);
        int result = 0;
        for(int i=0; i<worker.length; i++){
            result += binarySearch(zip, worker[i]);
        }
        return result;
    }
    // 寻找小于target的最大difficulty
    public static int binarySearch(ArrayList<int[]> zip, int target){
        int left = -1, right = zip.size();
        while(left<=right && right!=-1 && left<zip.size()){
            int mid = (left+right)/2;
            if(zip.get(mid)[0] > target){
                right = mid-1; // (right, len-1] > target
            }else{
                left = mid+1; // [0, left) <= target
            }
        }
        if(right==-1) return 0; // 找不到difficulty比target小的任务
        if(left==zip.size()) return zip.get(right-1)[1]; // 所有任务的difficulty都比target小
        return zip.get(right)[1];
    }
}
