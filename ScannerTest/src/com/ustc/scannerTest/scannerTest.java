package com.ustc.scannerTest;

import java.util.Scanner;

public class scannerTest {
    public static void main(String[] args) {
        // 创建Scanner对象
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your number");
        // 接受键盘输入数据
        int input = sc.nextInt();

        System.out.println(input);
    }
}
