package controller;

import greeting.Greeting;
import greeting.GreetingInEnglish;
import greeting.GreetingInPolish;

import java.util.Random;

public class GreetingController {

    public static void randomlySelectClassToUse() {
        Random rand = new Random();
        boolean randValue = rand.nextBoolean();
        Greeting greeting = randValue ? new GreetingInPolish() : new GreetingInEnglish();
        greeting.printGreetingMessage();

    }

}
