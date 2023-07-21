package com.kainos.ea.exception;

public class DatabaseConnectionException extends Throwable{
    public String getMessage(){
        return "Failed to connect to database";
    }
}
