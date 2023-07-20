package com.kainos.ea.execption;

public class FailedToGetCompsException extends Throwable {

    @Override
    public String getMessage() {
        return "Failed to get comps from Database";
    }

}
