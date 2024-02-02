package com.ustc.normal;

public class LeeCode2182 {
    public static void main(String[] args) {
        String s = repeatLimitedString("robnsdvpuxbapuqgopqvxdrchivlifeepy", 2);
        System.out.println(s);
    }
    public static String repeatLimitedString(String s, int repeatLimit) {
        int[] count = new int[26];
        for(int i=0; i<s.length(); i++){
            count[s.charAt(i)-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        int fast = 24; // 指向次大的字母
        int slow = 25; // 指向最大的字母
        int occur = 0; // 最大字母出现的次数
        boolean alreadyFlag=false;

        while(slow>=0){
            while(slow>=0 && !(count[slow]>0)){ // slow移动到下一个大于0的位置
                if(alreadyFlag){
                    occur=1;
                    alreadyFlag = false;
                }
                else occur=0;
                slow--;
            }
            while(fast>=0 && !(count[fast]>0) || fast >= slow){ // fast移动到下一个大于0的位置
                fast--;
            }
            System.out.println(occur+"-"+slow+"-"+fast);
            if(occur == repeatLimit-1 && slow>=0){
                sb.append((char)('a'+slow));
                if(fast>=0){
                    sb.append((char)('a'+fast));
                    count[fast]--;
                }
                if(slow==0) break; //指向a了，结束
                count[slow]--;
                if(count[slow]>0) occur=0;
                else{
                    occur=1;
                    alreadyFlag=true;
                }
            }else if(occur < repeatLimit-1 && slow>=0){
                sb.append((char)('a'+slow));
                count[slow]--;
                occur++;
            }else if(slow<0){
                break;
            }
        }

        return sb.toString();
    }
}
