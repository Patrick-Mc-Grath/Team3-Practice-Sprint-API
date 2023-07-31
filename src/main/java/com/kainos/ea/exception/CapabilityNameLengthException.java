package com.kainos.ea.exception;

public class CapabilityNameLengthException extends Throwable {
    @Override
    public String getMessage() {
        return "Name must not be empty or over 100 characters";
    }
}
