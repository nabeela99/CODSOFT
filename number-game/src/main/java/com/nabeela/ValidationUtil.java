package com.nabeela;

public class ValidationUtil {
    public static void check(String number,int n) throws IllegalAccessException {
        switch (n) {
            case 1 -> {
                if(Integer.parseInt(number) < 0 || Integer.parseInt(number) > 10) {
                    throw new IllegalAccessException("Input entered is out of range");
                }
            }
            case 2 -> {
                if(Integer.parseInt(number) < 0 || Integer.parseInt(number) > 100) {
                    throw new IllegalAccessException("Input entered is out of range");
                }
            }
            case 3 -> {
                if(Integer.parseInt(number) < 0 || Integer.parseInt(number) > 500) {
                    throw new IllegalAccessException("Input entered is out of range");
                }
            }
        }
    }
    public static void checkForReturn(String playOption) throws IllegalAccessException {
        if(!"YES".equals(playOption) && !"NO".equals(playOption)){
            throw new IllegalAccessException("Invalid input Entered");
        }
    }
}
