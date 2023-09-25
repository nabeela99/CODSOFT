package com.codesoft;

public class GameFeedback {

    private final int randNumber;
    Menu menu1 = new Menu();

    public GameFeedback(int randNumber) {
        this.randNumber = randNumber;
    }

    public String guessResult(int randNumber,int input){
        if (randNumber > input) {
            return "low";
        } else if (randNumber < input) {
            return "high";
        }
        return "correct";
    }
    public void score(int n1) throws IllegalAccessException {
        int scores = 5;
        int i = 0;
        while(i < 5) {
            String input = Util.inputNumber();
            ValidationUtil.check(input,n1);
            String result = guessResult(this.randNumber, Integer.parseInt(input));
            System.out.println(result);
            i++;
            if (result.equals("correct")){
                break;
            }else{
                scores--;
            }
        }
        System.out.println("Your Score is" + " " + scores + "/5");
        if(scores == 0){
            System.out.println("Better luck next time");
            System.out.println("The correct answer is : " + randNumber);
        }
    }

}
