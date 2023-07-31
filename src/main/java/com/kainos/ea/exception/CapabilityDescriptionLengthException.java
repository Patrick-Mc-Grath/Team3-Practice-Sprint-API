package com.kainos.ea.exception;

public class CapabilityDescriptionLengthException extends Throwable {
    @Override
    public String getMessage() {
        return "Description must not be empty or over 100 characters";
    }
}
