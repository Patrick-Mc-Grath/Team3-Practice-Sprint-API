package com.kainos.ea.exception;

/**
 * Exception thrown when the database fails to return a training.
 */
public class TrainingDoesNotExistException extends Throwable {
  @Override
  public String getMessage() {
    return "This training info does not exist";
  }
}
