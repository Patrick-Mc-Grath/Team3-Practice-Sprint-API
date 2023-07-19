package com.kainos.ea.client;

public class FailedToGetCompsException extends Throwable {

    @Override
    public String getMessage() {
        return "Failed to get orders from Database";
    }

}
