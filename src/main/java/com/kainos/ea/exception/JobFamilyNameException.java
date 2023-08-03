package com.kainos.ea.exception;

public class JobFamilyNameException extends Throwable {
    public String getMessage() {
        return "Name must not be empty or over 100 characters";
    }

}
