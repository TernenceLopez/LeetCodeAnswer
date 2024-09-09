package XiaoHongShu;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
    题目描述：
        小苯有一个长度为n的数组a，他定义了一个函数f：f(I,r)=数组a的[l，r]区间元素之和。
        现在小苯有一个重新任意排列数组a的机会，他想要最小化数组a所有子区间对应f的值之和，请你帮他算算最小的这个值吧。
        输入描述
        第一行输入一个正整数n（1≤n≤2×10＾5）代表数组中元素的个数。
        第二行输入n个整数 a1，a2……an(1≤ai≤ 10＾6）代表数组中的元素。
    输出描述
        在一行上输出一个整数，表示题中所求答案。
    样例输入
        3
        1 2 3
    样例输出
        19
*/

/**
 * 解题思路：我们希望将较大的值尽可能放在靠两边的位置，这样它在子区间中出现的次数就会减少。
 * */
public class Exam002 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // 对数组进行排序
        Arrays.sort(a);

        // 创建一个新的数组用于保存锯齿形排列
        int[] res = new int[n];
        int left = 0, right = n - 1;

        // 按照锯齿形规则重排数组(先遍历最大的元素，将其放在两边，最小的元素放在最中间)
        for (int i =n-1; i >= 0; i--) {
            if (i % 2 == 0) {
                res[left] = a[i];
                left++;
            } else {
                res[right] = a[i];
                right--;
            }
        }

        // 计算所有子数组之和
        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += (long) res[i] * (i + 1) * (n - i);
        }

        // 输出结果
        System.out.println(totalSum);

        scanner.close();
    }
}
