package controller;

public class InvalidInputDataException extends Exception {

    public InvalidInputDataException(String message) {
        super(message);
        System.out.printf(message);
    }
}
