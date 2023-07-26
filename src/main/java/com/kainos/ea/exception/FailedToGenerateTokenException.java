package com.kainos.ea.exception;

public class FailedToGenerateTokenException extends Exception
{
    @Override
    public String getMessage()
    {
        return "failed to generate token";
    }
}
