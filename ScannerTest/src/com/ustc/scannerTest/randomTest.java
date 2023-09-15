package com.ustc.scannerTest;

import java.util.Random;
public class randomTest {
    public static void main(String[] args) {
        Random rd = new Random();
        int number  = rd.nextInt(9)+1;

        System.out.println(number);
    }
}
