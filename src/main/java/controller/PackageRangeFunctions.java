package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PackageRangeFunctions {
    public static String scannString() {
        Scanner scann = new Scanner(System.in);
        return scann.nextLine();
    }

    public static Integer scannInt() {
        Scanner scann = new Scanner(System.in);
        Integer tmp = 0;
        boolean valid = true;
        do {
            try {
                tmp = scann.nextInt();
                valid = false;
            } catch (InputMismatchException exception) {
                System.out.println("Wrong number! Type again \n\t");
                scann.nextLine();
            }
        } while (valid);

        return tmp;
    }
}
