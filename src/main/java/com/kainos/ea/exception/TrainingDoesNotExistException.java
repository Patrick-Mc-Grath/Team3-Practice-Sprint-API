package com.kainos.ea.exception;

public class TrainingDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "This training info does not exist";
    }
}
