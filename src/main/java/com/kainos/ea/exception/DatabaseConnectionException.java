package com.kainos.ea.exception;

public class DatabaseConnectionException extends Throwable{
    public DatabaseConnectionException(Exception error){
        super(error);
    }
}
