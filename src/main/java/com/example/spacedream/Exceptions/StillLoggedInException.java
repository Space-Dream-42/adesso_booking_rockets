package com.example.spacedream.Exceptions;

public class StillLoggedInException extends Exception {
    public StillLoggedInException() {
    }


    public StillLoggedInException(String message) {
        super(message);
    }
}
