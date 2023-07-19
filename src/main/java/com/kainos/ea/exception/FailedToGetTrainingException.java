package com.kainos.ea.exception;

public class FailedToGetTrainingException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get training info";
    }
}
