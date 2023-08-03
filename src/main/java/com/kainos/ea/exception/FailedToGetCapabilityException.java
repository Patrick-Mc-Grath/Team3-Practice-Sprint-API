package com.kainos.ea.exception;

/**
 * Exception thrown when the database fails to return a capability.
 */
public class FailedToGetCapabilityException extends Throwable {
  @Override
  public String getMessage() {
    return "Failed To Get Capabilities from the database";
  }
}