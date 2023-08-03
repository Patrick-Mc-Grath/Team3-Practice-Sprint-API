package com.kainos.ea.exception;

public class FailedToGetJobFamiliesException extends Throwable {
    public String getMessage() {
        return "Failed to get job families";
    }
}
