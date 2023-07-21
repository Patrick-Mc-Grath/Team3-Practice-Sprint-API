package com.kainos.ea.exception;

public class DatabaseConnectionException extends Throwable {

    @Override
    public String getMessage()
    {
        return "Failed To Connect To Database";
    }
}