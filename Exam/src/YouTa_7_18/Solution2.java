package YouTa_7_18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 友塔游戏提前批笔试第二题
 * */
public class Solution2 {
    static int[] maxTime = new int[6];
    static int[] minTime = new int[6];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        List<Integer> num = new ArrayList<>();
        int item=0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            item = in.nextInt();
        }
        while(item>0){
            num.add(item%10);
            item = item/10;
        }
        // System.out.println(num.size());
        Arrays.fill(maxTime, 0);
        Arrays.fill(minTime, 100);
        int[] cur = new int[6];
        int[] used = new int[6];
        backtracking(cur, 0, num, used);

        for(int j=0; j<6; j++){
            System.out.print(maxTime[j]);
        }
        System.out.print(",");
        for(int j=0; j<6; j++){
            System.out.print(minTime[j]);
        }
    }

    public static void backtracking(int[] cur, int index, List<Integer> num, int[] used){
        if(index == 6){
            // for(int j=0; j<6; j++){
            //     System.out.print(cur[j]);
            // }
            // System.out.println(" ");
            if(aIsBiggerThanb(cur, maxTime)==1){
                for(int i=0; i<6; i++) maxTime[i] = cur[i];
            }
            if(aIsBiggerThanb(minTime, cur)==1){
                for(int i=0; i<6; i++) minTime[i] = cur[i];
            }
            return;
        }

        for(int i=0; i<6; i++){
            if(used[i]==1) continue;
            if(index==0 && num.get(i)<=2){
                int record = cur[index];
                cur[index] = num.get(i);
                used[i] = 1;
                backtracking(cur, index+1, num, used);
                cur[index] = record;
                used[i] = 0;
            }else if((index==1 && cur[index-1]==2 && num.get(i)<=3)
                    || (index==1 && cur[index-1]<2 && num.get(i)<=9)){
                int record = cur[index];
                cur[index] = num.get(i);
                used[i] = 1;
                backtracking(cur, index+1, num, used);
                cur[index] = record;
                used[i] = 0;
            }else if((index==2 || index==4) && num.get(i)<=6){
                int record = cur[index];
                cur[index] = num.get(i);
                used[i] = 1;
                backtracking(cur, index+1, num, used);
                cur[index] = record;
                used[i] = 0;
            }else if(((index==3 || index==5) && cur[index-1]<6 && num.get(i)<=9)
                    || ((index==3 || index==5) && cur[index]==6 && num.get(i)==0)){
                int record = cur[index];
                cur[index] = num.get(i);
                used[i] = 1;
                backtracking(cur, index+1, num, used);
                cur[index] = record;
                used[i] = 0;
            }else continue;
        }
    }

    public static int aIsBiggerThanb(int[] a, int[] b){
        for(int i=0; i<6; i++){
            if(a[i] < b[i]){
                return -1;
            }else if(a[i] > b[i]){
                return 1;
            }else continue;
        }
        return 0;
    }
}
