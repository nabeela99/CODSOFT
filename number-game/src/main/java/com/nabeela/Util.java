package com.nabeela;

import java.util.Random;
import java.util.Scanner;

public class Util {
    public static int randomNumber(int n) {
        Random num = new Random();
        int ranNum = 0;
        switch (n) {
            case 1 -> {
                ranNum = num.nextInt(10);
            }
            case 2 -> {
                ranNum = num.nextInt(100);
            }
            case 3 -> {
                ranNum = num.nextInt(500);
            }
        }
        return ranNum;
    }
    public static String inputNumber() {
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        return num;
    }
}
