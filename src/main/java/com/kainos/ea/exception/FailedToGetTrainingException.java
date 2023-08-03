package com.kainos.ea.exception;

/**
 * Exception thrown when the database fails to return a training.
 */
public class FailedToGetTrainingException extends Throwable {
  @Override
  public String getMessage() {
    return "Failed to get training info";
  }
}
