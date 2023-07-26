package com.kainos.ea.exception;

public class FailedToLoginException extends Exception
{
    @Override
    public String getMessage()
    {
        return "failed to login";
    }
}
