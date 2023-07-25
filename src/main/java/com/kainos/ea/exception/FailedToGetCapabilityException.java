package com.kainos.ea.exception;

public class FailedToGetCapabilityException extends Throwable {

    @Override
    public String getMessage()
    {
        return "Failed To Get Capabilities from the database";
    }
}