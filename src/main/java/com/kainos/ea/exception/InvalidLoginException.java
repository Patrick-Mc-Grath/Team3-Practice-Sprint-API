package com.kainos.ea.exception;

public class InvalidLoginException extends Throwable {
    public InvalidLoginException(String error)
    {
        super(error);
    }
}
