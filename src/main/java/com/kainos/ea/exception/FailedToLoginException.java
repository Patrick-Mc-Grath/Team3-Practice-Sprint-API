package com.kainos.ea.exception;

public class FailedToLoginException extends Throwable
{
    public FailedToLoginException(String error)
    {
        super(error);
    }
}
