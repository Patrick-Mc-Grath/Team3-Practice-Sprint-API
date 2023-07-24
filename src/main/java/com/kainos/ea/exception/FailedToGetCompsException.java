package com.kainos.ea.exception;

public class FailedToGetCompsException extends Throwable {

    @Override
    public String getMessage() {
        return "Failed to get comps from Database";
    }

}
