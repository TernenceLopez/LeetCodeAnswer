package com.ustc.difficult;

import java.util.Stack;

public class LeeCode1944 {
    public static void main(String[] args) {
        int[] result = canSeePersonsCount(new int[]{10,6,8,5,11,9});
        for(int i: result){
            System.out.print(i+" ");
        }
    }
    public static int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int[] result = new int[n];

        for(int i=n-1; i>=0; i--){ // 从右向左
            // 维护单调递减栈
            while(!st.isEmpty() && heights[st.peek()] < heights[i]){
                st.pop(); // 弹出的这个值对i可见
                result[i]++;
            }
            if(!st.isEmpty()) result[i]++;
            st.push(i);
        }

        // 处理栈中的剩余元素
        // if(!st.isEmpty()) st.pop();
        // while(!st.isEmpty()){
        //     result[st.pop()]++;
        // }

        return result;
    }
}
