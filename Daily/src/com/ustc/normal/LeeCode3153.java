package com.ustc.normal;

import java.math.BigInteger;
import java.util.Arrays;

public class LeeCode3153 {
    public static void main(String[] args) {
        System.out.println(sumDigitDifferences(new int[]{50,28,48}));
    }

    public static long sumDigitDifferences(int[] nums) {
        BigInteger res = BigInteger.ZERO;
        int[] count = new int[10];

        while (true) {
            Arrays.fill(count, 0);
            int diff = 0;
            BigInteger bitRes = BigInteger.ZERO;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) return res.longValue();
                if (count[nums[i] % 10] == 0) diff++;
                count[nums[i] % 10]++;
                nums[i] = nums[i] / 10;
            }

            if (diff == 1) continue;

            for (int i = 0; i < 10; i++) {
                for (int j = i + 1; j < 10; j++) {
                    if (i != j) {
                        bitRes = bitRes.add(BigInteger.valueOf((long) count[i] * count[j]));
                    }
                }
            }
            res = res.add(bitRes);
        }
    }
}
