package com.ustc.simple;

public class LCP61 {
    public static void main(String[] args) {
        temperatureTrend(new int[]{21,18,18,18,31},
                         new int[]{34,32,16,16,17});
    }

    public static int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int n = temperatureA.length;
        int ans = 0, cur = 0;
        for (int i = 1; i < n; ++i) {
            int ta = getTrend(temperatureA[i - 1], temperatureA[i]);
            int tb = getTrend(temperatureB[i - 1], temperatureB[i]);
            if (ta == tb) {
                ++cur;
                ans = Math.max(ans, cur);
            } else {
                cur = 0;
            }
        }
        return ans;
    }

    public static int getTrend(int x, int y) {
        if (x == y) {
            return 0;
        }
        return x < y ? -1 : 1;
    }
}
