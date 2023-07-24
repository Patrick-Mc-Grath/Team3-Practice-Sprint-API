package com.kainos.ea.execption;

public class DatabaseConnectionException extends Throwable {
    @Override
    public String getMessage()
    {
        return "Failed To Connect To Database";
    }
}
