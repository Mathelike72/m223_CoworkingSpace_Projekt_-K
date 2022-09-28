package ch.zli.m223.exceptions;

public class InvalidLoginException extends Exception {
    public InvalidLoginException(String explanation) {
        super(explanation);
    }
}