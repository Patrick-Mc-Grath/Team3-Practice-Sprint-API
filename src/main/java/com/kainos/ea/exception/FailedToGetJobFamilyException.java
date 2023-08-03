package com.kainos.ea.exception;

public class FailedToGetJobFamilyException extends Throwable {

    @Override
    public String getMessage()
    {
        return "Failed To Get Job Families from the database";
    }
}