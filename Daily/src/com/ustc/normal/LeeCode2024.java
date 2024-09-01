package com.ustc.normal;

public class LeeCode2024 {
    public static void main(String[] args) {
        System.out.println(maxConsecutiveAnswers("TTFF", 2));
        System.out.println(maxConsecutiveAnswersDP("TTFF", 2));
    }

    /**
     * 动态规划法（本质还是滑动窗口）
     * */
    public static int maxConsecutiveAnswersDP(String answerKey, int k) {
        return Math.max(getMaxContinueRegion('T', answerKey, k), getMaxContinueRegion('F', answerKey, k));
    }

    /**
     * 获取最长连续字符串的长度
     * @param ch: T 或者 F
     * @param k: 允许修改的次数
     * */
    public static int getMaxContinueRegion(char ch, String answerKey, int k) {
        // 获取需要修改的字符类型：如果ch=='F'，那么delCh就为'T'
        char delCh = ch == 'T' ? 'F' : 'T';
        // 目前已经遍历得到的delCh的个数
        int countDelCh = 0;
        int maxLen = 0;
        // delChPos[i]: 记录第i个delCh字符的位置
        int[] delChPos = new int[answerKey.length()];
        for(int right = 0; right < answerKey.length(); right++) {
            char curCh = answerKey.charAt(right);
            if(curCh == delCh){
                // 记录第 countDelCh 个 delCh 字符的位置
                delChPos[countDelCh] = right;
                countDelCh++;
            }

            if(countDelCh > k){
                // [0, right]不满足构成连续窗口的条件，移除(countDelCh-k)个delCh
                maxLen = Math.max(maxLen, right - delChPos[countDelCh - k -1]);
            }else {
                // [0, right]满足构成连续窗口的条件
                maxLen++;
            }
        }
        return maxLen;
    }

    /**
     * 滑动窗口法
     * */
    public static int maxConsecutiveAnswers(String answerKey, int k) {
        int left = 0, right = 0, maxLen = 0;
        int countF = 0, countT = 0;

        for(; right < answerKey.length(); right++){
            char ch = answerKey.charAt(right);
            if(ch == 'T') countT++;
            else countF++;

            if(countT <= k || countF <= k){
                // 满足构成连续窗口的条件
                maxLen = Math.max(maxLen, right - left + 1);
            }else{
                // 不满足构成连续窗口的条件，移动左边界
                do{
                    char delCh = answerKey.charAt(left);
                    if(delCh == 'T') countT--;
                    else countF--;
                    left++;
                }while(countT > k && countF > k);
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }
}
