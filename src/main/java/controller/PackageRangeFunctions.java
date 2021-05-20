package controller;

import java.util.Scanner;

public class PackageRangeFunctions {
    public static String scannString() {
        Scanner scann = new Scanner(System.in);
        return scann.nextLine();
    }


    //trzeba sprawdzic czy wprowadzona wartość jest typu int
    public static Integer scannInt() {
        Scanner scann = new Scanner(System.in);
        return scann.nextInt();
    }
}
