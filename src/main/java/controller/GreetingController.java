package controller;

import greeting.GreetingInEnglish;
import greeting.GreetingInPolish;

import java.util.Random;

public class GreetingController{

    public static void randomlySelectClassToUse(){
        Random rand = new Random();
        boolean randValue = rand.nextBoolean();
        if (randValue){
            GreetingInEnglish greeting = new GreetingInEnglish();
            greeting.printGreetingMessage();
        }
        else {
            GreetingInPolish greeting = new GreetingInPolish();
            greeting.printGreetingMessage();
        }
    }



}
