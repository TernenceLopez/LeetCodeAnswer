package com.ustc.normal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LeeCode2766 {
//    给你一个下标从 0 开始的整数数组 nums ，表示一些石块的初始位置。再给你两个长度 相等 下标从 0 开始的整数数组 moveFrom 和 moveTo 。
//
//    在 moveFrom.length 次操作内，你可以改变石块的位置。在第 i 次操作中，你将位置在 moveFrom[i] 的所有石块移到位置 moveTo[i] 。
//
//    完成这些操作后，请你按升序返回所有 有 石块的位置。
//
//    注意：
//
//    如果一个位置至少有一个石块，我们称这个位置 有 石块。
//    一个位置可能会有多个石块。
//
//
//    示例 1：
//
//    输入：nums = [1,6,7,8], moveFrom = [1,7,2], moveTo = [2,9,5]
//    输出：[5,6,8,9]
//    解释：一开始，石块在位置 1,6,7,8 。
//    第 i = 0 步操作中，我们将位置 1 处的石块移到位置 2 处，位置 2,6,7,8 有石块。
//    第 i = 1 步操作中，我们将位置 7 处的石块移到位置 9 处，位置 2,6,8,9 有石块。
//    第 i = 2 步操作中，我们将位置 2 处的石块移到位置 5 处，位置 5,6,8,9 有石块。
//    最后，至少有一个石块的位置为 [5,6,8,9] 。

    public static void main(String[] args) {
        int[] nums = {1,6,7,8};
        int[] moveFrom = {1,7,2};
        int[] moveTo = {2,9,5};
        List<Integer> result = relocateMarbles(nums, moveFrom, moveTo);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums) {
            set.add(num);
        }
        for(int i = 0; i < moveFrom.length; i++) {
            set.remove(moveFrom[i]);
            set.add(moveTo[i]);
        }
        List<Integer> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }
}
