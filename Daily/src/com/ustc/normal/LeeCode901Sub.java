package com.ustc.normal;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeeCode901Sub {
    Deque<int[]> stack;
    int count;

    public LeeCode901Sub() {
        stack = new ArrayDeque<>(); // 单调栈
        count = 0;
    }

    public int next(int price) {
        count = 1;

        // [0]元素记录price大小
        // [1]元素记录nums前面有几个小于price的值
        while(!stack.isEmpty() && stack.peekFirst()[0] <= price){ // 当前元素大于等于栈顶元素时，则弹出
//            stack.pollFirst();
            count = count + stack.pollFirst()[1];
        }

//        if(!stack.isEmpty())
//            System.out.println(" "+ price + "    "+ stack.peekFirst()[1]);

        stack.push(new int[]{price, count});

        return count;
    }
}
