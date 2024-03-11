package com.ustc.simple;

public class LeeCode2129 {
    public static void main(String[] args) {
        capitalizeTitle("ZW Cl pyR uoC");
    }
    public static String capitalizeTitle(String title) {
        char[] str = title.toCharArray();

        for(int slow=0; slow<str.length; slow++){
            for(int fast=slow; fast<str.length; fast++){
                if(str[fast] !=' ' && fast!=str.length-1) continue;

                if((fast-slow <= 2 && fast!=str.length-1 )||
                        (fast-slow < 2 && fast==str.length-1)){
                    // 首字母小写
                    if(str[slow]>='A' && str[slow]<='Z') str[slow] -= ('A' - 'a');
                }else{
                    // 首字母大写
                    if(str[slow]>='a' && str[slow]<='z') str[slow] += ('A' - 'a');
                }
                // 剩余字母变成小写
                slow++;
                while(slow<=fast){
                    if(str[slow]>='A' && str[slow]<='Z') str[slow] -= ('A' - 'a');
                    slow++;
                }
                slow--;
                break;
            }
        }

        return new String(str);
    }
}
