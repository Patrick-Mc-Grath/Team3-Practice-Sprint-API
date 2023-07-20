package com.kainos.ea.execption;

public class DatabaseConnectionException extends Throwable {
    public DatabaseConnectionException(Exception e) {
        super(e);
    }
}
