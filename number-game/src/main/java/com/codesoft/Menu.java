package com.codesoft;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    int input = 0;
    int n = 0;
    public void start() throws InterruptedException, IllegalAccessException {
        boolean play = true;
        do {
            System.out.println("Please select difficulty level : ");
            System.out.println("For Easy select 1" + "\n" + "For Medium select 2" + "\n" + "For Difficult select 3");
            Menu menu = new Menu();
            n = sc.nextInt();
            menu.difficultyLevel(n);
            GameFeedback feedback = new GameFeedback(Util.randomNumber(n));
            feedback.score(n);
            play = menu.gameOptions();
        }
        while(play);
    }
    public void difficultyLevel(int n) throws InterruptedException, IllegalAccessException {
        switch (n) {
            case 1 -> {
                System.out.println("Enter the Number between 0 and 10");
            }
            case 2 -> {
                System.out.println("Enter the Number between 0 and 100");
            }
            case 3 -> {
                System.out.println("Enter the Number between 0 and 500");
            }
            default -> {
                System.out.println("Please select any number between 1, 2 and 3 only");
                Thread.sleep(2000);
                Menu menu1 = new Menu();
                menu1.start();
            }
        }
    }
    public boolean gameOptions() throws IllegalAccessException {
        System.out.println("Want to play again ? Type YES or NO");
        String playAgain = sc.nextLine();
        ValidationUtil.checkForReturn(playAgain);
        if("YES".equalsIgnoreCase(playAgain)){
            return true;
        }else{
            System.out.println("Thank You For Playing");
            return false;
        }

    }
}
